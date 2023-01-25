//package top.integer.yygh;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.read.metadata.ReadSheet;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import top.integer.yygh.cmn.service.DictService;
//
//import java.util.*;
//
//@SpringBootTest
//public class ExcelTest {
//    @Autowired
//    DictService service;
//
//    @Test
//    void test() {
//        Random random = new Random();
//        String path = "D:\\temp\\" + System.currentTimeMillis() + ".xlsx";
//        try(ExcelWriter excelWriter = EasyExcel.write(path, User.class).build();) {
//            WriteSheet writeSheet = EasyExcel.writerSheet("用户表").build();
//            for (int i = 0; i < 1000; i++) {
//                String[] split = UUID.randomUUID().toString().split("-");
//                List<User> list = Arrays.asList(new User(random.nextInt(10000), split[0], random.nextInt(1990, 2024) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 32), split[4]),
//                        new User(random.nextInt(10000), split[0], random.nextInt(1990, 2024) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 32), split[3]),
//                        new User(random.nextInt(10000), split[1], random.nextInt(1990, 2024) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 32), split[2]),
//                        new User(random.nextInt(10000), split[2], random.nextInt(1990, 2024) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 32), split[1]),
//                        new User(random.nextInt(10000), split[3], random.nextInt(1990, 2024) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 32), split[0]));
//                excelWriter.write(list, writeSheet);
//            }
//        }
//    }
//    @Test
//    void read() {
//        String path = "D:\\temp\\" + "user" + ".xlsx";
//        ReadListener<User> userReadListener = new ReadListener<>() {
//            List<User> list = new ArrayList<>(100);
//            @Override
//            public void invoke(User user, AnalysisContext analysisContext) {
//                list.add(user);
//                if (list.size() == 100) {
//                    System.out.println("list = " + list);
//                    list.clear();
//                }
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                System.out.println("list = " + list);
//                list.clear();
//            }
//        };
//        ExcelReader excelReader = EasyExcel.read(path, User.class, userReadListener).build();
//        ReadSheet readSheet = EasyExcel.readSheet("用户表").build();
//        excelReader.read(readSheet);
//    }
//}
