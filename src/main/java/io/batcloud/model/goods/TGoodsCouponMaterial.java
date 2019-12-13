package io.batcloud.model.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_goods_coupon_material")
public class TGoodsCouponMaterial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 优惠券id,对应t_goods_coupon表的id
     */
    @Column(name = "goods_coupon_id")
    private Integer goodsCouponId;

    /**
     * 优惠券素材
     */
    @Column(name = "material_url")
    private String materialUrl;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态 1=可用 0=不可用
     */
    private Integer status;

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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取优惠券id,对应t_goods_coupon表的id
     *
     * @return goods_coupon_id - 优惠券id,对应t_goods_coupon表的id
     */
    public Integer getGoodsCouponId() {
        return goodsCouponId;
    }

    /**
     * 设置优惠券id,对应t_goods_coupon表的id
     *
     * @param goodsCouponId 优惠券id,对应t_goods_coupon表的id
     */
    public void setGoodsCouponId(Integer goodsCouponId) {
        this.goodsCouponId = goodsCouponId;
    }

    /**
     * 获取优惠券素材
     *
     * @return material_url - 优惠券素材
     */
    public String getMaterialUrl() {
        return materialUrl;
    }

    /**
     * 获取排序
     * @return
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 设置优惠券素材
     *
     * @param materialUrl 优惠券素材
     */
    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    /**
     * 获取状态 1=可用 0=不可用
     *
     * @return status - 状态 1=可用 0=不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1=可用 0=不可用
     *
     * @param status 状态 1=可用 0=不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}