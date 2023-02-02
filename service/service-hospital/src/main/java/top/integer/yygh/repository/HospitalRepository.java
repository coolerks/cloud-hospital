package top.integer.yygh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.integer.yygh.model.hosp.Department;
import top.integer.yygh.model.hosp.Hospital;

public interface HospitalRepository extends MongoRepository<Hospital, String> {
}
