package io.batcloud.controller;

import io.batcloud.cache.GoodsCouponCache;
import io.batcloud.dto.common.BaseResponse;
import io.batcloud.model.goods.TGoodsCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("coupon")
public class GoodsCouponController {

    @Autowired
    private GoodsCouponCache goodsCouponCache;

    @GetMapping("/get/{id}")
    @ResponseBody
    public BaseResponse<TGoodsCoupon> getGoodsCoupon(@PathVariable Integer id){
        TGoodsCoupon goodsCoupon = goodsCouponCache.getGoodsCouponById(id);
        return BaseResponse.success(goodsCoupon);
    }

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse<List<TGoodsCoupon>> listGoodsCoupon(){
        List<TGoodsCoupon> goodsCouponList = goodsCouponCache.getGoodsCouponList();
        return BaseResponse.success(goodsCouponList);
    }
}
