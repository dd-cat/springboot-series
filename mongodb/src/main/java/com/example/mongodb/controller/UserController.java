package com.example.mongodb.controller;

import com.example.mongodb.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-08 22:19
 * @Description:
 */
@RestController
public class UserController {

    @Resource
    private MongoTemplate mongoTemplate;

    // 添加用户
    @GetMapping("save")
    public void insertUser(User user) {
        mongoTemplate.save(user);
    }

    // 根据id获取用户
    @GetMapping("select/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }


    // 更新用户
    @GetMapping("update")
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));

        Update update = new Update();
        update.set("name", user.getName());
        update.set("sex", user.getSex());

        mongoTemplate.updateFirst(query, update, User.class);
    }

    // 删除用户
    @GetMapping("delete")
    public void deleteUser(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }


    //多表关联查询
    /*private void twoTableQuery(String shopName, int pageNum, int pageSize) {
        // 构建 Aggregation：添加查询条件
        Aggregation aggregation = Aggregation.newAggregation(
                //关联表
                Aggregation.lookup(
                        "wish_products_shipping",// 从表表名
                        "_id",// 主表要关联的字段
                        "productId",   // 从表要关联的字段
                        "groups"//临时表名，可以用在后面条件查询用
                ),
                // 查询条件
                Aggregation.match(
                        Criteria.where("suffix").is(shopName)
                                //.and("groups.productId").is("5a5791cfa5419b40c660e303")
                                .and("groups.productId").exists(false)//结果为空的
                ),
                // 分页：页码
                Aggregation.skip(Long.valueOf(pageNum)),
                // 分页：条数
                Aggregation.limit(pageSize)
        );
        List<String> list = mongoTemplate.aggregate(aggregation, "products", String.class).getMappedResults();
        for (String str : list) {
            System.out.println(str);
        }
    }*/
}
