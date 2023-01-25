package top.integer.yygh;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ExcelProperty("用户编号")
    Integer id;
    @ExcelProperty("姓名")
    String name;
    @ExcelProperty("日期")
    String date;

    String another;
}
