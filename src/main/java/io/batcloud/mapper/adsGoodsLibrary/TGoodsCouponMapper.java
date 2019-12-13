package io.batcloud.mapper.adsGoodsLibrary;

import io.batcloud.model.goods.TGoodsCoupon;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TGoodsCouponMapper extends Mapper<TGoodsCoupon> {

    public List<TGoodsCoupon> queryCouponList();
}