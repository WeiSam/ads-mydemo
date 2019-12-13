package io.batcloud.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zz.common.log.LogService;
import io.batcloud.cache.GoodsCache;
import io.batcloud.cache.GoodsCouponCache;
import io.batcloud.service.CouponService;
import io.batcloud.service.impl.goodsLibrary.GoodsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public  class CheckServiceController {

	@Autowired
	private GoodsCouponCache goodsCouponCache;

	@Autowired
	private GoodsCache goodsCache;

	@Autowired
	private GoodsServiceImpl goodsService;

	protected Logger log = LoggerFactory.getLogger(this.getClass());
 

	private static volatile Long startTime = System.currentTimeMillis(); 
	
	@ResponseBody
	@RequestMapping(value="/check",method = {RequestMethod.GET,RequestMethod.POST} )
	public String check( HttpServletResponse response){
		long start = System.currentTimeMillis();
		try{
			goodsService.selectById(1);
		}catch (Exception e){
			LogService.error("CheckServiceController check db error : "+e);
			throw new RuntimeException("check db error!");
		}
		Date cacheLastFlushTime = goodsCache.getLastFlushTime();
		if((System.currentTimeMillis() - cacheLastFlushTime.getTime()) > 15 * 60 * 1000){
			LogService.error("CheckServiceController check goods cache error,cache data is not new!");
			throw new RuntimeException("check goods cache error!");
		}
		cacheLastFlushTime = goodsCouponCache.getLastFlushTime();
		if((System.currentTimeMillis() - cacheLastFlushTime.getTime()) > 15 * 60 * 1000){
			LogService.error("CheckServiceController check goods coupon cache error,cache data is not new!");
			throw new RuntimeException("check goods coupon cache error!");
		}
		long end = System.currentTimeMillis();
		LogService.info("CheckServiceController check cost time : "+( end - start ));
		return "success";
	}
}