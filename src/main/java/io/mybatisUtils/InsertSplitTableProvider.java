package io.mybatisUtils;

import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * BaseInsertProvider实现类，基础方法实现类
 *
 * @author liuzh
 */
public class InsertSplitTableProvider extends MapperTemplate {

    public InsertSplitTableProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insertWithTable(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
      
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
     
        EntityColumn logicDeleteColumn = SqlHelper.getLogicDeleteColumn(entityClass);
        sql.append("INSERT INTO ${tableName}");
        
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            if (!column.isInsertable()) {
                continue;
            }
            
            if (logicDeleteColumn != null && logicDeleteColumn == column) {
                sql.append(SqlHelper.getLogicDeletedValue(column, false)).append(",");
                continue;
            }
            
            if (logicDeleteColumn != null && logicDeleteColumn == column) {
                sql.append(SqlHelper.getLogicDeletedValue(column, false)).append(",");
                continue;
            }
            

            //其他情况值仍然存在原property中
            sql.append(SqlHelper.getIfNotNull("record",column, column.getColumnHolder("record", null, ","), isNotEmpty()));
            
            
            //当null的时候，如果不指定jdbcType，oracle可能会报异常，指定VARCHAR不影响其他
            sql.append(SqlHelper.getIfIsNull("record",column, column.getColumnHolder("record", null, ","), isNotEmpty()));
            
        }
        sql.append("</trim>");
        return sql.toString();
    }

    /**
     * 批量插入
     *
     * @param ms
     */
    public String insertListWithTable(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append("<bind name=\"listNotEmptyCheck\" value=\"@tk.mybatis.mapper.util.OGNL@notEmptyCollectionCheck(list, '" + ms.getId() + " 方法参数为空')\"/>");
       
        sql.append("INSERT INTO ${tableName}");
        
        sql.append(SqlHelper.insertColumns(entityClass, true, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
            if (!column.isId() && column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }
        sql.append("</trim>");
        sql.append("</foreach>");

        // 反射把MappedStatement中的设置主键名
        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), ms);

        return sql.toString();
    }
    
    
    public String insertWithTableSelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
       
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        EntityColumn logicDeleteColumn = SqlHelper.getLogicDeleteColumn(entityClass);
        sql.append("INSERT INTO ${tableName}");
        
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            if (!column.isInsertable()) {
                continue;
            }
        
            if (logicDeleteColumn != null && logicDeleteColumn == column) {
                sql.append(column.getColumn()).append(",");
                continue;
            }
            sql.append(SqlHelper.getIfNotNull("record",column, column.getColumn() + ",", isNotEmpty()));
            
        }
        sql.append("</trim>");
        sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            if (!column.isInsertable()) {
                continue;
            }
            
            if (logicDeleteColumn != null && logicDeleteColumn == column) {
                sql.append(SqlHelper.getLogicDeletedValue(column, false)).append(",");
                continue;
            }
         
            //其他情况值仍然存在原property中
            sql.append(SqlHelper.getIfNotNull("record",column, column.getColumnHolder("record", null, ","), isNotEmpty()));
            
        }
        sql.append("</trim>");
        
        return sql.toString();
    }

  
}
