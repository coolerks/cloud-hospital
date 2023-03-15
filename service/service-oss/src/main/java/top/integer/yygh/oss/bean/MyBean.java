package top.integer.yygh.oss.bean;

import lombok.Data;

@Data
public class MyBean {
    Integer id;

    public MyBean() {
        System.out.println("正常启动");
    }
}
