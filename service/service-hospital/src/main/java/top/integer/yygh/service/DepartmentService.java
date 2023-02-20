package top.integer.yygh.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Schedule;
import top.integer.yygh.repository.ScheduleRepository;
import top.integer.yygh.vo.hosp.BookingScheduleRuleVo;
import top.integer.yygh.vo.hosp.DepartmentVo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class DepartmentService {
    @Autowired
    private MongoTemplate template, mongoTemplate;
    private Map<Integer, String> weekMap;

    @Autowired
    private ScheduleRepository scheduleRepository;

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
                    }
                    DepartmentVo e = new DepartmentVo();
                    e.setDepcode(it.getDepcode());
                    e.setDepname(it.getDepname());
                    departmentVo.getChildren().add(e);
                });
        return map.values().stream().toList();
    }

    public Map<String, Object> getSchedule(String hoscode, String depcode, Integer page, Integer limit) {
        //1 根据医院编号 和 科室编号 查询
        Criteria criteria = Criteria.where("hoscode").is(hoscode).and("depcode").is(depcode);

        //2 根据工作日workDate期进行分组
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),//匹配条件
                Aggregation.group("workDate")//分组字段
                        .first("workDate").as("workDate")
                        //3 统计号源数量
                        .count().as("docCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber"),
                //排序
                Aggregation.sort(Sort.Direction.DESC, "workDate"),
                //4 实现分页
                Aggregation.skip((page - 1) * limit),
                Aggregation.limit(limit)
        );
        //调用方法，最终执行
        AggregationResults<BookingScheduleRuleVo> aggResults =
                mongoTemplate.aggregate(agg, Schedule.class, BookingScheduleRuleVo.class);
        List<BookingScheduleRuleVo> bookingScheduleRuleVoList = aggResults.getMappedResults();

        //分组查询的总记录数
        Aggregation totalAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate")
        );
        AggregationResults<BookingScheduleRuleVo> totalAggResults =
                mongoTemplate.aggregate(totalAgg,
                        Schedule.class, BookingScheduleRuleVo.class);
        int total = totalAggResults.getMappedResults().size();

        //把日期对应星期获取
        for (BookingScheduleRuleVo bookingScheduleRuleVo : bookingScheduleRuleVoList) {
            Date workDate = bookingScheduleRuleVo.getWorkDate();
//            bookingScheduleRuleVo.setDayOfWeek(dayOfWeek);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(workDate);
            bookingScheduleRuleVo.setDayOfWeek(weekMap.get(calendar.get(Calendar.DAY_OF_WEEK)));
        }

        //设置最终数据，进行返回
        Map<String, Object> result = new HashMap<>();
        result.put("bookingScheduleRuleList", bookingScheduleRuleVoList);
        result.put("total", total);

        //其他基础数据
        Map<String, String> baseMap = new HashMap<>();
        result.put("baseMap", baseMap);

        return result;
    }

    public DepartmentService() {
        weekMap = new HashMap<>(7);
        weekMap.put(1, "星期日");
        weekMap.put(2, "星期一");
        weekMap.put(3, "星期二");
        weekMap.put(4, "星期三");
        weekMap.put(5, "星期四");
        weekMap.put(6, "星期五");
        weekMap.put(7, "星期六");
    }

    public List<Schedule> getScheduleList(String hoscode, String depcode, Date date) {
        List<Schedule> scheduleList =
                scheduleRepository.findScheduleByHoscodeAndDepcodeAndWorkDate(hoscode, depcode, date);
        return scheduleList;
    }
}
