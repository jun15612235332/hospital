package com.zhiyou100.service;

import java.util.List;

import com.zhiyou100.model.PayItems;

public interface PayItemsService {

	List<PayItems> findAll();

	List<String> findAllName();

}
