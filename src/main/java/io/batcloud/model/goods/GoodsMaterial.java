package io.batcloud.model.goods;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_goods_material")
@Accessors(chain = true)
public class GoodsMaterial implements Serializable {
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
     * 素材地址
     */
    @Column(name = "icon_url")
    private String iconUrl;

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

    private static final long serialVersionUID = 1L;

}