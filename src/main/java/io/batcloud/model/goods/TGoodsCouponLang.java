package io.batcloud.model.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_goods_coupon_lang")
public class TGoodsCouponLang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 优惠券id,对应t_goods_coupon表的id
     */
    @Column(name = "goods_coupon_id")
    private Integer goodsCouponId;

    /**
     * 语言
     */
    private String language;

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
     * 最后更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 优惠券描述
     */
    @Column(name = "coupon_desc")
    private String couponDesc;

    /**
     * 优惠券规则
     */
    @Column(name = "coupon_rule")
    private String couponRule;

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
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
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
     * 获取最后更新时间
     *
     * @return update_time - 最后更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param updateTime 最后更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取优惠券描述
     *
     * @return coupon_desc - 优惠券描述
     */
    public String getCouponDesc() {
        return couponDesc;
    }

    /**
     * 设置优惠券描述
     *
     * @param couponDesc 优惠券描述
     */
    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    /**
     * 获取优惠券规则
     *
     * @return coupon_rule - 优惠券规则
     */
    public String getCouponRule() {
        return couponRule;
    }

    /**
     * 设置优惠券规则
     *
     * @param couponRule 优惠券规则
     */
    public void setCouponRule(String couponRule) {
        this.couponRule = couponRule;
    }
}