package io.batcloud.service;

import io.batcloud.model.goods.TGoodsCoupon;
import java.util.List;

public interface CouponService extends BaseService<TGoodsCoupon>{

    public List<TGoodsCoupon> queryAllGoodsCoupon();
}
