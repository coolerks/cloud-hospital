//package top.integer.yygh;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import top.integer.yygh.cmn.mapper.DictMapper;
//import top.integer.yygh.model.cmn.Dict;
//
//import java.util.ArrayList;
//
//@SpringBootTest
//public class MyBatisTest {
//    @Autowired
//    DictMapper mapper;
//
//    @Test
//    void test() {
//        ArrayList<Dict> list = new ArrayList<>();
//        Dict e = new Dict();
//        e.setId(6666660L);
//        e.setName("性别");
//        e.setParentId(1L);
//
//        Dict e2 = new Dict();
//        e2.setParentId(1L);
//        e2.setName("人群");
//        e.setId(7777770L);
//
//        Dict dict = new Dict();
//        dict.setName("男");
//        dict.setId(6666661L);
//        dict.setParentId(6666660L);
//
//        Dict dict1 = new Dict();
//        dict1.setName("女");
//        dict1.setId(6666662L);
//        dict1.setParentId(6666660L);
//
//        list.add(e);
//        list.add(e2);
//        list.add(dict);
//        list.add(dict1);
//        System.out.println("mapper.insertBatch(list) = " + mapper.insertBatch(list));
//    }
//}
