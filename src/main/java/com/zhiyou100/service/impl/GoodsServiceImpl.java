package com.zhiyou100.service.impl;

import com.zhiyou100.model.Goods;
import com.zhiyou100.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Override
    public List<Goods> findAll() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(1, "小米", "便宜", "/a/1.png"));
        goodsList.add(new Goods(2, "华为", "好用", "/a/2.jpg"));
        return goodsList;
    }
}
