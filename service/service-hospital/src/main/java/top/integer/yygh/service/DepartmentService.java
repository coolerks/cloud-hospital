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
        //1 ?????????????????? ??? ???????????? ??????
        Criteria criteria = Criteria.where("hoscode").is(hoscode).and("depcode").is(depcode);

        //2 ???????????????workDate???????????????
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),//????????????
                Aggregation.group("workDate")//????????????
                        .first("workDate").as("workDate")
                        //3 ??????????????????
                        .count().as("docCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber"),
                //??????
                Aggregation.sort(Sort.Direction.DESC, "workDate"),
                //4 ????????????
                Aggregation.skip((page - 1) * limit),
                Aggregation.limit(limit)
        );
        //???????????????????????????
        AggregationResults<BookingScheduleRuleVo> aggResults =
                mongoTemplate.aggregate(agg, Schedule.class, BookingScheduleRuleVo.class);
        List<BookingScheduleRuleVo> bookingScheduleRuleVoList = aggResults.getMappedResults();

        //???????????????????????????
        Aggregation totalAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate")
        );
        AggregationResults<BookingScheduleRuleVo> totalAggResults =
                mongoTemplate.aggregate(totalAgg,
                        Schedule.class, BookingScheduleRuleVo.class);
        int total = totalAggResults.getMappedResults().size();

        //???????????????????????????
        for (BookingScheduleRuleVo bookingScheduleRuleVo : bookingScheduleRuleVoList) {
            Date workDate = bookingScheduleRuleVo.getWorkDate();
//            bookingScheduleRuleVo.setDayOfWeek(dayOfWeek);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(workDate);
            bookingScheduleRuleVo.setDayOfWeek(weekMap.get(calendar.get(Calendar.DAY_OF_WEEK)));
        }

        //?????????????????????????????????
        Map<String, Object> result = new HashMap<>();
        result.put("bookingScheduleRuleList", bookingScheduleRuleVoList);
        result.put("total", total);

        //??????????????????
        Map<String, String> baseMap = new HashMap<>();
        result.put("baseMap", baseMap);

        return result;
    }

    public DepartmentService() {
        weekMap = new HashMap<>(7);
        weekMap.put(1, "?????????");
        weekMap.put(2, "?????????");
        weekMap.put(3, "?????????");
        weekMap.put(4, "?????????");
        weekMap.put(5, "?????????");
        weekMap.put(6, "?????????");
        weekMap.put(7, "?????????");
    }

    public List<Schedule> getScheduleList(String hoscode, String depcode, Date date) {
        List<Schedule> scheduleList =
                scheduleRepository.findScheduleByHoscodeAndDepcodeAndWorkDate(hoscode, depcode, date);
        return scheduleList;
    }
}
