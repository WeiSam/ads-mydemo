package io.batcloud.service.impl.goodsLibrary;

import io.batcloud.mapper.adsGoodsLibrary.TGoodsCouponLangMapper;
import io.batcloud.model.goods.TGoodsCouponLang;
import io.batcloud.service.CouponLangService;
import io.batcloud.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service("couponLangService")
public class CouponLangServiceImpl extends BaseServiceImpl<TGoodsCouponLangMapper,TGoodsCouponLang> implements CouponLangService {
}
