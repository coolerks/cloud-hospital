package top.integer.yygh.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.integer.yygh.common.exception.YyghException;
import top.integer.yygh.common.result.ResultCodeEnum;
import top.integer.yygh.mapper.HospitalSetMapper;
import top.integer.yygh.model.hosp.HospitalSet;

@Service
public class HospitalSetService extends ServiceImpl<HospitalSetMapper, HospitalSet> {
    public String getSign(String hoscode) {
        LambdaUpdateWrapper<HospitalSet> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(HospitalSet::getHoscode, hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);
        if (hospitalSet == null) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        return hospitalSet.getSignKey();
    }
}
