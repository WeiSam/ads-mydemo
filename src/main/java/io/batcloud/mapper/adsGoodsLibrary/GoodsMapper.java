package io.batcloud.mapper.adsGoodsLibrary;

import io.batcloud.model.goods.Goods;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsMapper extends Mapper<Goods>,MySelectMapper<Goods> {

    /**
     * 多表查询获取商品信息
     * @return
     */
    public List<Goods> queryGoodsList();

}