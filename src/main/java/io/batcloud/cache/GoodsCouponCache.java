package io.batcloud.cache;

import io.batcloud.model.goods.TGoodsCoupon;
import io.batcloud.model.goods.TGoodsCouponLang;
import io.batcloud.model.goods.TGoodsCouponMaterial;
import io.batcloud.service.CouponLangService;
import io.batcloud.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class GoodsCouponCache extends AbstractCache{

    /**
     * 存放优惠券id与优惠券详细信息的Map,key为优惠券id,value为优惠券详细信息对象
     */
    private Map<Integer,TGoodsCoupon> idToGoodsCouponMap = new HashMap<Integer,TGoodsCoupon>();

    /**
     * 存放优惠券id与多语言对象列表的Map,key为优惠券id,value为多语言对象列表
     */
    private Map<Integer,List<TGoodsCouponLang>> goodsCouponIdToLangsMap = new HashMap<Integer,List<TGoodsCouponLang>>();

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponLangService couponLangService;

//    @Scheduled(cron = "0 */1 * * * ?")
    public void flushCache(){
        List<TGoodsCoupon> goodsCouponList = couponService.queryAllGoodsCoupon();
        TGoodsCouponLang goodsCouponLang = new TGoodsCouponLang();
        goodsCouponLang.setStatus(1);
        List<TGoodsCouponLang> goodsCouponLangList = couponLangService.selectList(goodsCouponLang);
        log.debug("goods coupon list data size is "+goodsCouponList.size());
        Map<Integer,TGoodsCoupon> idToGoodsCouponMap = new HashMap<Integer,TGoodsCoupon>();
        Map<Integer,List<TGoodsCouponLang>> goodsCouponIdToLangsMap = new HashMap<Integer,List<TGoodsCouponLang>>();
        /**
         * 放入优惠券缓存
         */
        for (TGoodsCoupon goodsCoupon : goodsCouponList){
            Integer couponId = goodsCoupon.getId();
            Collections.sort(goodsCoupon.getMaterialList(), new Comparator<TGoodsCouponMaterial>() {
                @Override
                public int compare(TGoodsCouponMaterial o1, TGoodsCouponMaterial o2) {
                    int sort1 = o1.getSort();
                    int sort2 = o2.getSort();
                    if(sort1 > sort2){
                        return 1;
                    }else if (sort1 < sort2){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            });
            idToGoodsCouponMap.put(couponId,goodsCoupon);
        }
        for(TGoodsCouponLang lang : goodsCouponLangList){
            Integer couponId = lang.getGoodsCouponId();
            if(!goodsCouponIdToLangsMap.containsKey(couponId)){
                goodsCouponIdToLangsMap.put(couponId,new ArrayList<TGoodsCouponLang>());
            }
            goodsCouponIdToLangsMap.get(couponId).add(lang);
        }
        this.idToGoodsCouponMap = idToGoodsCouponMap;
        this.goodsCouponIdToLangsMap = goodsCouponIdToLangsMap;
        this.lastFlushTime = new Date();
    }


    /**
     * 根据优惠券id获取详情信息
     * @param couponId
     * @return
     */
    public TGoodsCoupon getGoodsCouponById(Integer couponId){
        TGoodsCoupon goodsCoupon = this.idToGoodsCouponMap.get(couponId);
        if(null == goodsCoupon){
            return null;
        }
        goodsCoupon.setLangs(this.goodsCouponIdToLangsMap.get(couponId));
        return goodsCoupon;
    }

    /**
     * 取得优惠券列表
     * @return
     */
    public List<TGoodsCoupon> getGoodsCouponList(){
        int goodsCouponSize = this.idToGoodsCouponMap.size();
        List<TGoodsCoupon> goodsCouponList = new ArrayList<TGoodsCoupon>(goodsCouponSize);
        Set<Integer> ids = this.idToGoodsCouponMap.keySet();
        for (Integer id : ids){
            TGoodsCoupon goodsCoupon = getGoodsCouponById(id);
            if(null != goodsCoupon){
                goodsCouponList.add(goodsCoupon);
            }
        }
        return goodsCouponList;
    }

}
