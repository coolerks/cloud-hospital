package top.integer.yygh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.service.DepartmentService;

import java.util.Date;

@RestController
@RequestMapping("/admin/host/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;
    @RequestMapping("/getdeptList/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode) {
        return Result.ok(service.findDepartmentByHoscode(hoscode));
    }

    @RequestMapping("/getschedule/{hoscode}/{depcode}/{page}/{limit}")
    public Result getScheduleDays(@PathVariable String hoscode, @PathVariable String depcode,
                              @PathVariable Integer page, @PathVariable Integer limit) {
        return Result.ok(service.getSchedule(hoscode, depcode, page, limit));
    }

    @RequestMapping("/getschedule/{hoscode}/{depcode}/{date}")
    public Result getSchedule(@PathVariable String hoscode, @PathVariable String depcode,
                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return Result.ok(service.getScheduleList(hoscode, depcode, date));
    }
}
