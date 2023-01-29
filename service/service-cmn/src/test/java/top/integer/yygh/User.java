package top.integer.yygh;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {
    @Id
    private String id;
    private String username, password;
}
