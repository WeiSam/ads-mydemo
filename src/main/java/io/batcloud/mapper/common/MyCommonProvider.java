package io.batcloud.mapper.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MyCommonProvider extends MapperTemplate {

    public MyCommonProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectAllAndAll(MappedStatement ms){
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = super.getEntityClass(ms);
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass,super.tableName(entityClass)));
        return sql.toString();
    }
}
