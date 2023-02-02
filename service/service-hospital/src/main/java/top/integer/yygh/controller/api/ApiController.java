package top.integer.yygh.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Hospital;
import top.integer.yygh.model.hosp.Schedule;
import top.integer.yygh.service.HospitalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService service;

    @PostMapping("/saveHospital")
    public Result saveHospital(Hospital hospital, @RequestParam String sign) {
        service.verify(hospital.getHoscode(), sign);
        service.save(hospital);
        return Result.ok();
    }

    @RequestMapping("/hospital/show")
    public Result getHospital(@RequestParam String hoscode, @RequestParam String sign) {
        service.verify(hoscode, sign);
        Hospital hospital = service.getHospital(hoscode);
        if (hospital == null) {
            return Result.fail();
        }
        return Result.ok(hospital);
    }

    @PostMapping("/saveDepartment")
    public Result saveDepartment(Department department, @RequestParam String hoscode, @RequestParam String sign) {
        service.verify(hoscode, sign);
        service.saveDepartment(department);
        return Result.ok();
    }

    @RequestMapping("/department/list")
    public Result getDepartmentList(@RequestParam String hoscode, @RequestParam Integer limit, @RequestParam Integer page, @RequestParam String sign) {
        service.verify(hoscode, sign);
        return Result.ok(service.getDepartmentList(hoscode, page, limit));
    }

    @RequestMapping("/department/remove")
    public Result remove(@RequestParam String hoscode, @RequestParam String depcode, @RequestParam String sign) {
        service.verify(hoscode, sign);
        if (service.removeDepartment(hoscode, depcode)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @RequestMapping("/saveSchedule")
    public Result saveSchedule(Schedule schedule, @RequestParam String hoscode, @RequestParam String sign) {
        service.verify(hoscode, sign);
        service.saveSchedule(schedule);
        return Result.ok();
    }

    @RequestMapping("/schedule/list")
    public Result scheduleList(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String hoscode, @RequestParam String sign) {
        service.verify(hoscode, sign);
        return Result.ok(service.getScheduleList(hoscode, page, limit));
    }

    @RequestMapping("/schedule/remove")
    public Result deleteSchedule(@RequestParam String hoscode, @RequestParam String sign, @RequestParam String hosScheduleId) {
        service.verify(hoscode, sign);
        if(service.removeSchedule(hoscode, hosScheduleId)) {
            return Result.ok();
        }
        return Result.fail();
    }
}
