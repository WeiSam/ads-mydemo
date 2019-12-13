package io.batcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.mybatisUtils.InsertSplitTableMapper;
import tk.mybatis.mapper.entity.Example;

public abstract class BaseCreativeServiceImpl<M extends InsertSplitTableMapper<T>, T> {
		
	@Autowired
	protected M mapper;

	public List<T> selectByExample(Example e){
		return mapper.selectByExample(e);
	}

	public abstract String getSplitTablePrefix();

	/**
	 * 添加
	 *
	 * @param entity
	 */
	public void insertWithTable(String tableName, T entity) {
		 
		 
		mapper.insertWithTable(entity, tableName);
	}
	
	public void insertWithTableSelective(String tableName,T entity) {
		mapper.insertWithTableSelective(entity, tableName);
	}

	public void insertListWithTable(String tableName,List<T> entityList) {
		mapper.insertListWithTable(entityList, tableName);
	}
}
