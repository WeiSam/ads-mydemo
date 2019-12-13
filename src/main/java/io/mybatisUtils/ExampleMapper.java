package io.mybatisUtils;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@tk.mybatis.mapper.annotation.RegisterMapper
public interface ExampleMapper<T>  {


    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    List<T> selectByExample(Object example);
    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    T selectOneByExample(Object example);
    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int selectCountByExample(Object example);
    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    @DeleteProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int deleteByExample(Object example);
    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int updateByExample(@Param("record") T record, @Param("example") Object example);
    

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

}