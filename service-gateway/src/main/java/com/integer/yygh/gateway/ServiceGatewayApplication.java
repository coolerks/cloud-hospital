package com.integer.yygh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }
}


class Test {
    public static void main(String[] args) {
        String token = "807778e186c3a2754d9ffe21bfb1b27853ca21b5";
        String timestamp = "1677594662";
        String nonce = "793580214";

        char[] tokenArr = token.toCharArray();
        Arrays.sort(tokenArr);
        String t1 = String.valueOf(tokenArr);

        char[] timestampArr = timestamp.toCharArray();
        Arrays.sort(timestampArr);
        String t2 = String.valueOf(timestampArr);

        char[] nonceArr = nonce.toCharArray();
        Arrays.sort(nonceArr);
        String n1 = String.valueOf(nonceArr);

        String result = t1 + t2 + n1;
        System.out.println(result);

    }
}
