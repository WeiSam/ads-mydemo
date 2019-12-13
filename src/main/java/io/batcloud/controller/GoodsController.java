package io.batcloud.controller;

import io.batcloud.cache.GoodsCache;
import io.batcloud.dto.common.BaseResponse;
import io.batcloud.dto.goods.GoodsVo;
import io.batcloud.mapper.adsGoodsLibrary.GoodsMapper;
import io.batcloud.mapper.adsGoodsLibrary.MySelectMapper;
import io.batcloud.model.goods.Goods;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "goods")
public class GoodsController {

    @Autowired
    private GoodsCache goodsCache;

    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping(value = "/list/{amount}")
    @ResponseBody
    public BaseResponse<List<Goods>> getGoods(@PathVariable Integer amount){
        long startTime = System.currentTimeMillis();
        List<Goods> goods = goodsCache.getGoodsList(amount);
        return BaseResponse.success(goods,startTime);
    }

    @GetMapping(value = "/getGoods/{id}")
    @ResponseBody
    public BaseResponse<Goods> getGoodsById(@PathVariable Integer id){
        long startTime = System.currentTimeMillis();
        Goods goods = goodsCache.getGoodsById(id);
        return BaseResponse.success(goods,startTime);
    }

    @GetMapping(value = "/getGoodsVo/{id}")
    @ResponseBody
    public BaseResponse<GoodsVo> getGoodsVoById(@PathVariable Integer id){
        long startTime = System.currentTimeMillis();
        Goods goods = goodsCache.getGoodsById(id);
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods,goodsVo);
        return BaseResponse.success(goodsVo,startTime);
    }

    @GetMapping(value = "/getGoodsByProvider")
    @ResponseBody
    public BaseResponse<List<Goods>> getGoodsByProvider(){
        long startTime = System.currentTimeMillis();
        List<Goods> goods = goodsMapper.selectAllAndAll();
        return BaseResponse.success(goods,startTime);
    }
}
