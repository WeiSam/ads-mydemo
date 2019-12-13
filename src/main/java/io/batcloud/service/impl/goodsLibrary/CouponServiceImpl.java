package io.batcloud.service.impl.goodsLibrary;

import com.zz.common.log.LogService;
import io.batcloud.mapper.adsGoodsLibrary.TGoodsCouponMapper;
import io.batcloud.model.goods.TGoodsCoupon;
import io.batcloud.service.CouponService;
import io.batcloud.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service("couponService")
public class CouponServiceImpl extends BaseServiceImpl<TGoodsCouponMapper,TGoodsCoupon> implements CouponService {

    @Override
    public List<TGoodsCoupon> queryAllGoodsCoupon(){
        List<TGoodsCoupon> coupons = this.mapper.queryCouponList();
        if(null == coupons) {
            coupons = Collections.emptyList();
            LogService.debug("goods coupon list is empty!");
        }
        return coupons;
    }
}
