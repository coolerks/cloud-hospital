package top.integer.yygh;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.integer.yygh.service.DepartmentService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class ExcelTest {


    @Test
    public void test() {
        Date date = new Date();
        System.out.println("date = " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("calendar.getWeeksInWeekYear() = " + calendar.getWeeksInWeekYear());
        System.out.println("calendar.getWeekYear() = " + calendar.getWeekYear());
        System.out.println("calendar.getFirstDayOfWeek() = " + calendar.getFirstDayOfWeek());
        System.out.println("calendar.getMinimalDaysInFirstWeek() = " + calendar.getMinimalDaysInFirstWeek());
        System.out.println("calendar.get(Calendar.DAY_OF_WEEK) = " + calendar.get(Calendar.DAY_OF_WEEK));
    }
}
