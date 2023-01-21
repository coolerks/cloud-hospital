package top.integer.yygh.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.common.utils.MD5;
import top.integer.yygh.model.hosp.HospitalSet;
import top.integer.yygh.service.HospitalSetService;
import top.integer.yygh.vo.hosp.HospitalSetQueryVo;

import java.util.List;
import java.util.Random;

@RestController
@Controller
@RequestMapping("/admin/hosp/hospitalSet")
@Api(tags = "医院设置管理")
@Slf4j
@CrossOrigin
public class HospitalSetController {
    @Autowired
    private HospitalSetService service;

    @ApiOperation(value = "查询所有的医院")
    @GetMapping("findAll")
    public Result findAll() {
        List<HospitalSet> list = service.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "删除某个医院")
    @DeleteMapping("/{id}")
    public Result removeHospital(@PathVariable Long id) {
        if (service.removeById(id)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/findPageHospSet/{now}/{size}")
    public Result findPage(@PathVariable Integer now, @PathVariable Integer size, @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        LambdaUpdateWrapper<HospitalSet> queryWrapper = new LambdaUpdateWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();
        String hosname = hospitalSetQueryVo.getHosname();
        Page<HospitalSet> page = new Page<>(now, size);
        queryWrapper.eq(!ObjectUtils.isEmpty(hoscode), HospitalSet::getHoscode, hoscode)
                .like(!ObjectUtils.isEmpty(hosname), HospitalSet::getHosname, hosname);
        service.page(page, queryWrapper);
        return Result.ok(page);
    }

    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(1000)));
        hospitalSet.setStatus(1);
        boolean save = service.save(hospitalSet);
        if (save) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/updateHospitalSet")
    public Result update(@RequestBody HospitalSet hospitalSet) {
        if (service.updateById(hospitalSet)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @GetMapping("/getHospSet/{id}")
    public Result getById(@PathVariable Long id) {
        HospitalSet hospitalSet = service.getById(id);
        if (hospitalSet != null) {
            return Result.ok(hospitalSet);
        }
        return Result.fail();
    }

    @DeleteMapping("/batchRemove")
    public Result deleteBatch(@RequestBody List<Long> list) {
        if (service.removeByIds(list)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PutMapping("/lockHospital/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id, @PathVariable Long status) {
        LambdaUpdateWrapper<HospitalSet> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(HospitalSet::getStatus, status)
                .eq(HospitalSet::getId, id);
        if (service.update(updateWrapper)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id) {
        HospitalSet hospitalSet = service.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        return Result.ok();
    }

}
