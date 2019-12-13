package io.batcloud.cache;

import com.zz.common.log.LogService;
import io.batcloud.mapper.adsGoodsLibrary.GoodsMapper;
import io.batcloud.model.goods.Goods;
import io.batcloud.service.impl.goodsLibrary.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Guava缓存商品信息
 * key:id
 * value:pojo
 */
@Component
public class GoodsCache extends AbstractCache{

    private List<Goods> goodsList = new ArrayList<Goods>();
    private Map<Integer,Goods> goodsMap = new HashMap<Integer,Goods>();

    @Autowired
    private GoodsServiceImpl goodsService;

    @Autowired
    private GoodsMapper goodsMapper;

//    @Scheduled(cron = "0 */1 * * * ?")
    public void flushCache(){
        LogService.info("GoodsCache.loadGoodsList.start");
        Long startTime = System.currentTimeMillis();
        List<Goods> goodsListTem = goodsMapper.queryGoodsList();
        Map<Integer,Goods> goodsMapTem = new HashMap<Integer,Goods>();
        for (Goods goods:goodsListTem){
            goodsMapTem.put(goods.getId(),goods);
        }
        //随机打乱
        Collections.shuffle(goodsListTem);
        this.goodsList = goodsListTem;
        this.goodsMap = goodsMapTem;
        Long endTime = System.currentTimeMillis();
        this.lastFlushTime = new Date();
        LogService.info("GoodsCache.loadGoodsList.success,goodsList = "+goodsList.size()+",goodsMap = "+goodsMap.size()+",time = "+(endTime-startTime));
    }

    /**
     * 根据id查询数据库
     * @param id
     * @return
     */
    public Goods queryGoodsById(Integer id){
        Goods goods = goodsService.selectById(id);
        if (goods == null)
            return new Goods();
        return goods;
    }

    /**
     * 通过id获取商品信息
     * @param id
     * @return
     */
    public Goods getGoodsById(Integer id){
        return goodsMap.get(id);
    }

    public List<Goods> getGoodsList(Integer amount){
        if (goodsList.size()<=amount)
            return goodsList;
        return goodsList.subList(0,amount);
    }
}
