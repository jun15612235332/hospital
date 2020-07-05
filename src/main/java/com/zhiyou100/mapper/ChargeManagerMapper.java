package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.ChargeManager;

public interface ChargeManagerMapper {

	List<ChargeManager> findAll(Map<String, String> map);

	void add(ChargeManager ch);

	ChargeManager findChargeById(int id);

	void update(ChargeManager ch);

	void delete(int id);
}