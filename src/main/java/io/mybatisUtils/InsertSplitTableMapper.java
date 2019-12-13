package io.mybatisUtils;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface InsertSplitTableMapper<T> extends ExampleMapper<T>{

	@InsertProvider(type = InsertSplitTableProvider.class, method = "dynamicSQL")
    int insertWithTable(@Param("record")T record,@Param("tableName")String tableName);

	
    @InsertProvider(type = InsertSplitTableProvider.class, method = "dynamicSQL")
    int insertWithTableSelective(@Param("record")T record,@Param("tableName")String tableName);

    @InsertProvider(type = InsertSplitTableProvider.class, method = "dynamicSQL")
    int insertListWithTable(@Param("list")List<T> record,@Param("tableName")String tableName);
}
