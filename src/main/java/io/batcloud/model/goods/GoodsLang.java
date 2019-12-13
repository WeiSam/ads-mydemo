package io.batcloud.model.goods;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_goods_lang")
@Accessors(chain = true)
public class GoodsLang implements Serializable {
    /**
     * 唯一id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 状态 0：不使用，1：使用
     */
    private Integer status;

    /**
     * 语言
     */
    private String lan;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品简介
     */
    private String introduction;

    /**
     * 详细描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

}