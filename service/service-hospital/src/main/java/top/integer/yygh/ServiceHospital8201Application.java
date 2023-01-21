package top.integer.yygh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "top.integer")
public class ServiceHospital8201Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospital8201Application.class, args);
    }
}
