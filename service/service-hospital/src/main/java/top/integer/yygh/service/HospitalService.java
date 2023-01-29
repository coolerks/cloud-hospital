package top.integer.yygh.service;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import top.integer.yygh.common.exception.YyghException;
import top.integer.yygh.common.result.ResultCodeEnum;
import top.integer.yygh.common.utils.MD5;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Hospital;
import top.integer.yygh.repository.DepartmentRepository;


import java.util.Date;

@Service
public class HospitalService {
    @Autowired
    private MongoTemplate template;

    @Autowired
    HospitalSetService hospitalSetService;

    @Autowired
    DepartmentRepository departmentRepository;

    public void verify(String hoscode, String sign) {
        String sign1 = MD5.encrypt(hospitalSetService.getSign(hoscode));
//        System.out.println("sign1 = " + sign1);
//        System.out.println("sign = " + sign);
        if (!sign1.equals(sign)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }

    public void save(Hospital hospital) {
        Query query = new Query(Criteria.where("hoscode").is(hospital.getHoscode()));
        Hospital hos = template.findOne(query, Hospital.class);
        hospital.setLogoData(hospital.getLogoData().replace(' ', '+'));
        if (hos == null) {
            hospital.setStatus(0);
            hospital.setUpdateTime(new Date());
            hospital.setCreateTime(new Date());
            hospital.setIsDeleted(0);
            template.insert(hospital);
        } else {
            hospital.setId(hos.getId());
            hospital.setCreateTime(hos.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setStatus(hos.getStatus());
            hospital.setIsDeleted(hos.getIsDeleted());
            Update update = new Update();
            update.set("hoscode", hospital.getHoscode());
            update.set("hosname", hospital.getHosname());
            update.set("hostype", hospital.getHostype());
            update.set("provinceCode", hospital.getProvinceCode());
            update.set("cityCode", hospital.getCityCode());
            update.set("districtCode", hospital.getDistrictCode());
            update.set("address", hospital.getAddress());
            update.set("logoData", hospital.getLogoData());
            update.set("intro", hospital.getIntro());
            update.set("route", hospital.getRoute());
            update.set("status", hospital.getStatus());
            update.set("bookingRule", hospital.getBookingRule());
            update.set("updateTime", hospital.getUpdateTime());
            template.upsert(query, update, Hospital.class);
        }
    }

    public Hospital getHospital(String hoscode) {
        return template.findOne(new Query(Criteria.where("hoscode").is(hoscode)), Hospital.class);
    }

    public void saveDepartment(Department department) {
        Query query = new Query(Criteria.where("hoscode").is(department.getHoscode()).and("depcode").is(department.getDepcode()));
        Department department1 = template.findOne(query, Department.class);
        if (department1 == null) {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            template.insert(department);
        } else {
            department.setId(department1.getId());
            department.setCreateTime(department1.getCreateTime());
            department.setUpdateTime(new Date());
            department.setIsDeleted(department1.getIsDeleted());
            Update update = new Update();
            update.set("hoscode", department.getHoscode());
            update.set("depcode", department.getDepcode());
            update.set("depname", department.getDepname());
            update.set("intro", department.getIntro());
            update.set("bigcode", department.getBigcode());
            update.set("bigname", department.getBigname());
            update.set("updateTime", new Date());
            template.upsert(query, update, Department.class);
        }
    }

    public Page<Department> getDepartmentList(String hoscode, Integer pageNum, Integer pageSize) {
//        Query query = new Query(Criteria.where("hoscode").is(hoscode));
//        query.skip(pageSize * (pageNum - 1)).limit(pageSize);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreCase(true);
        Department department = new Department();
        department.setHoscode(hoscode);
        Example<Department> example = Example.of(department, matcher);
        return departmentRepository.findAll(example, pageable);
    }

    public boolean removeDepartment(String hoscode, String depcode) {
        DeleteResult remove = template.remove(new Query(Criteria.where("hoscode").is(hoscode).and("depcode").is(depcode)), Department.class);
        return remove.getDeletedCount() == 1;
    }
}
