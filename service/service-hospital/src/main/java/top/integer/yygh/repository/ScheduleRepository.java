package top.integer.yygh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
