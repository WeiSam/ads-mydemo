package io.batcloud.service;

import io.batcloud.model.goods.Goods;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuweimu
 * @date 2019/11/2 11:21
 */
public class CommonTest {

    @Test
    public void testExclude(){

        Goods goods = new Goods();
        goods.setId(1);
        goods.setGoodsName("hjhh");
        Goods goods1 = new Goods();
        goods1.setId(1);
        goods1.setGoodsName("hjhh");
        Goods goods2 = new Goods();
        goods2.setId(2);
        goods2.setGoodsName("hjhh");
        goods2.setSource(12);

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        goodsList.add(goods1);
        goodsList.add(goods2);

        List<String> strings = goodsList.stream().map(Goods::getId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList());
        Map<Integer, Long> countMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getId, Collectors.counting()));

        Optional.ofNullable(goodsList).ifPresent(list -> {
            list.stream().forEach(dto -> System.out.println(dto.getId()+":"+dto.getGoodsName()));
        });
    }
}
