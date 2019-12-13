package io.batcloud.service.impl.goodsLibrary;

import io.batcloud.model.goods.Goods;
import io.batcloud.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Mapper<Goods>,Goods> {

}
