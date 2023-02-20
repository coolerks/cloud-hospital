package top.integer.yygh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}
