package io.batcloud.model.goods;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_goods")
@Accessors(chain = true)
public class Goods implements Serializable {
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
     * 状态
     */
    private Integer status;
    /**
     * 商品标题
     */
    @Transient
    private String title;

    /**
     * 币种
     */
    private String currency;

    /**
     * 原价
     */
    @Column(name = "original_price")
    private BigDecimal originalPrice;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 国家
     */
    private String country;

    /**
     * 商品链接
     */
    @Column(name = "trick_url")
    private String trickUrl;

    /**
     * 来源，0：手动录入，1：系统拉取
     */
    private Integer source;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商品详细描述
     */
    private String description;

    /**
     * 商品简介
     */
    private String introduction;

    /**
     * 多语言列表
     */
    private List<GoodsLang> langs;

    /**
     * 素材列表
     */
    private List<GoodsMaterial> materials;

    private static final long serialVersionUID = 1L;

}