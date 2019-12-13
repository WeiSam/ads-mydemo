package io.batcloud.mapper.adsGoodsLibrary;

import io.batcloud.mapper.common.MyCommonProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface MySelectMapper<T> {

    /**
     *查询全部
     * @return
     */
    @SelectProvider(type = MyCommonProvider.class, method = "dynamicSQL")
    public List<T> selectAllAndAll();
}
