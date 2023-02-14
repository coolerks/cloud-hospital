package top.integer.yygh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.vo.hosp.DepartmentVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private MongoTemplate template;
    public List<DepartmentVo> findDepartmentByHoscode(String hoscode) {
        Map<String, DepartmentVo> map = new HashMap<>();
        template.find(new Query(Criteria.where("hoscode").is(hoscode)), Department.class)
                .forEach(it -> {
                    DepartmentVo departmentVo = map.get(it.getBigcode());
                    if (departmentVo == null) {
                        departmentVo = new DepartmentVo();
                        departmentVo.setDepname(it.getBigname());
                        departmentVo.setDepcode(it.getBigcode());
                        departmentVo.setChildren(new ArrayList<>());
                        map.put(it.getBigcode(), departmentVo);
                    } else {
                        DepartmentVo e = new DepartmentVo();
                        e.setDepcode(it.getDepcode());
                        e.setDepname(it.getDepname());
                        departmentVo.getChildren().add(e);
                    }
                });
        return map.values().stream().toList();
    }
}
