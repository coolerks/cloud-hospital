package top.integer.yygh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.integer.yygh.model.hosp.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {
}
