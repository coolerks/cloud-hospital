package top.integer.yygh.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.model.hosp.Hospital;
import top.integer.yygh.service.DepartmentService;
import top.integer.yygh.service.HospitalService;
import top.integer.yygh.vo.hosp.HospitalQueryVo;

import java.util.List;

@RestController
@RequestMapping("/api/hosp/hospital")
public class HospApiController {
    @Autowired
    private HospitalService service;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable Integer page, @PathVariable Integer limit, HospitalQueryVo hospitalQueryVo) {
        return Result.ok(service.getHospitalList(page, limit, hospitalQueryVo));
    }

    @RequestMapping("/findByHosName/{hosname}")
    public Result findByHosName(@PathVariable String hosname) {
        List<Hospital> list = service.findByHosName(hosname);
        return Result.ok(list);
    }

    @RequestMapping("/department/{hoscode}")
    public Result getDepartmentByHoscode(@PathVariable String hoscode) {
        return Result.ok(departmentService.findDepartmentByHoscode(hoscode));
    }

    @RequestMapping("/detail/{hoscode}")
    public Result getHospitalDetailByHoscode(@PathVariable String hoscode) {
        return Result.ok(service.getHospital(hoscode));
    }
}
