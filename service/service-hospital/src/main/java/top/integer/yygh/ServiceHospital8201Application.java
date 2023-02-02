package top.integer.yygh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "top.integer")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceHospital8201Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospital8201Application.class, args);
    }
}
