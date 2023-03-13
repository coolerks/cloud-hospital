package top.integer.yygh.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication(scanBasePackages = "top.integer.yygh", exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class ServiceOss8205Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOss8205Application.class, args);
    }
}
