package top.integer.yygh;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test() {
//        System.out.println("mongoTemplate = " + mongoTemplate);
//        插入
//        System.out.println(mongoTemplate.insert(new User(null, "user", "user")));
//        查询所有
//        System.out.println("mongoTemplate.findAll(User.class) = " + mongoTemplate.findAll(User.class));
//        根据id查询
//        System.out.println("mongoTemplate.findById(\"63d387d8f6c04a066ec45680\", User.class) = " + mongoTemplate.findById("63d387d8f6c04a066ec45680", User.class));
//        条件查询
//        Query query = new Query(Criteria.where("username").is("admin").and("password").is("admin"));
//        System.out.println("mongoTemplate.find(query, User.class) = " + mongoTemplate.find(query, User.class));
//        分页查询
//        int pageNo = 1, size = 10;
//        Query query = new Query();
//        query.skip((pageNo - 1) * size).limit(size);
//        System.out.println("mongoTemplate.find(query, User.class) = " + mongoTemplate.find(query, User.class));
    }

//    @Test
//    void update() {
//        User user = mongoTemplate.findById("63d3887b362a3439998f89fb", User.class);
//        System.out.println("user = " + user);
//        Query query = new Query(Criteria.where("id").is("63d3887b362a3439998f89fb"));
//        Update update = new Update();
//        update.set("password", "888888");
//        // 方式一
////        UpdateResult upsert = mongoTemplate.update(User.class).matching(query).apply(update).upsert();
//        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
//        // 方式二
//        System.out.println("upsert.getUpsertedId() = " + upsert.getUpsertedId());
//        System.out.println("upsert.getMatchedCount() = " + upsert.getMatchedCount());
//        System.out.println("upsert.getModifiedCount() = " + upsert.getModifiedCount());
//        System.out.println("upsert.wasAcknowledged() = " + upsert.wasAcknowledged());
//        System.out.println("upsert = " + upsert);
//    }

//    @Test
//    void delete() {
////        方式1
//        Query query = new Query(Criteria.where("id").is("63d3887b362a3439998f89fb"));
//        DeleteResult one = mongoTemplate.remove(User.class).matching(query).one();
//        System.out.println("one.getDeletedCount() = " + one.getDeletedCount());
//        System.out.println("one.wasAcknowledged() = " + one.wasAcknowledged());
//        System.out.println("one = " + one);
////        方式2
//        System.out.println("mongoTemplate.remove(new Query(Criteria.where(\"id\").is(\"63d387d8f6c04a066ec45680\"))) = " + mongoTemplate.remove(new Query(Criteria.where("id").is("63d387d8f6c04a066ec45680")), User.class));
////        方式3
//        User user = mongoTemplate.findById("63d3c1dc72128e376baa4798", User.class);
//        System.out.println("mongoTemplate.remove(user) = " + mongoTemplate.remove(user));
//    }
}
