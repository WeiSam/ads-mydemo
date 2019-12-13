package io.batcloud.dto.goods;

import io.batcloud.model.goods.GoodsMaterial;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class GoodsVo {

    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商家类型id
     */
    @Column(name = "store_type_id")
    private Integer storeTypeId;

    /**
     * 商家id
     */
    @Column(name = "store_id")
    private Integer storeId;

    /**
     * 商家商品id
     */
    @Column(name = "store_goods_id")
    private String storeGoodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;


    /**
     * 素材列表
     */
    private List<GoodsMaterial> materials;
}
