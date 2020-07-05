package com.zhiyou100.mapper;

import java.util.List;

import com.zhiyou100.model.PayItems;

public interface PayItemsMapper {

	List<PayItems> findAll();

	List<String> findAllName();
}