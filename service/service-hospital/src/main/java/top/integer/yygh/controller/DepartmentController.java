package top.integer.yygh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.service.DepartmentService;

@RestController
@RequestMapping("/admin/host/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;
    @RequestMapping("/getdeptList/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode) {
        return Result.ok(service.findDepartmentByHoscode(hoscode));
    }
}
