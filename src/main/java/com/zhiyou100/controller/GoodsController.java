package com.zhiyou100.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyou100.model.Goods;
import com.zhiyou100.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis的增删改查测试
 */
@Controller
public class GoodsController {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/showList")
    public List<Goods> showList() {
        //先从redis中查
        Jedis jedis = jedisPool.getResource();
        //声明要返回的list集合
        List<Goods> goodsList = null;
        Set<String> keys = jedis.keys("goods:*");
        //如果keys没有值,说明redis中没有数据
        if (keys.size()<=0){
            //去mysql中查询
            goodsList=goodsService.findAll();
            System.out.println("从数据库中查出的数据 : "+goodsList);
            //放入redis中
            //使用阿里的FastJson工具类-将对象转成json对象  list集合-->json数组
            for (Goods goods:goodsList) {
                //转化为json字符串
                String str = JSON.toJSONString(goods);
                System.out.println("list转化为json : "+str);
                //放入redis中
                String set = jedis.set("goods:" + goods.getGId(), str);
                System.out.println("向redis中加入数据 : " + set);


            }
        }else{
            //创建集合
            goodsList=new ArrayList<>();
            //取值
            for(String key:keys){
                String s = jedis.get(key);
                System.out.println("从redis中得到的json字符串:"+s);
                //json字符串转为对象
                Goods goods = JSONObject.parseObject(s, Goods.class);
                goodsList.add(goods);
            }
        }


        return goodsList;
    }
}
