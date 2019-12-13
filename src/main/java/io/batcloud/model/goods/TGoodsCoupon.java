package io.batcloud.model.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_goods_coupon")
public class TGoodsCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 优惠券名称
     */
    @Column(name = "coupon_name")
    private String couponName;

    /**
     * 优惠券平台：1=ios 2=android 3=web
     */
    @Column(name = "coupon_platform")
    private Integer couponPlatform;

    /**
     * 优惠券类型id,对应t_coupon_type表的id
     */
    @Column(name = "coupon_type")
    private Integer couponType;

    /**
     * 优惠券码
     */
    @Column(name = "coupon_code")
    private String couponCode;

    /**
     * 优惠券url
     */
    @Column(name = "coupon_url")
    private String couponUrl;

    /**
     * 分成比例，如30.5=30.5%
     */
    @Column(name = "divide_ratio")
    private Double divideRatio;

    /**
     * 商家类型id
     */
    @Column(name = "business_type")
    private Integer businessType;

    /**
     * 商家id
     */
    @Column(name = "business_id")
    private Integer businessId;

    /**
     * 商家平台名称
     */
    @Column(name = "business_platform")
    private String businessPlatform;

    /**
     * 状态：1=上线 0=下线
     */
    private Integer status;

    /**
     * 价值
     */
    private String values;

    /**
     * 有效开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 有效结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 可用数量
     */
    private Integer amount;

    /**
     * 锁定数量
     */
    @Column(name = "lock_amount")
    private Integer lockAmount;

    /**
     * 历史消耗数量
     */
    @Column(name = "history_amount")
    private Integer historyAmount;

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
     * 优惠券素材列表
     */
    @Transient
    private List<TGoodsCouponMaterial> materialList;

    /**
     * 多语言列表
     */
    @Transient
    private List<TGoodsCouponLang> langs;

    /**
     * 优惠券类型
     */
    @Transient
    private TGoodsCouponType goodsCouponType;

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
     * 获取优惠券名称
     *
     * @return coupon_name - 优惠券名称
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * 设置优惠券名称
     *
     * @param couponName 优惠券名称
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    /**
     * 获取优惠券平台：1=ios 2=android 3=web
     *
     * @return coupon_platform - 优惠券平台：1=ios 2=android 3=web
     */
    public Integer getCouponPlatform() {
        return couponPlatform;
    }

    /**
     * 设置优惠券平台：1=ios 2=android 3=web
     *
     * @param couponPlatform 优惠券平台：1=ios 2=android 3=web
     */
    public void setCouponPlatform(Integer couponPlatform) {
        this.couponPlatform = couponPlatform;
    }

    /**
     * 获取优惠券类型id,对应t_coupon_type表的id
     *
     * @return coupon_type - 优惠券类型id,对应t_coupon_type表的id
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     * 设置优惠券类型id,对应t_coupon_type表的id
     *
     * @param couponType 优惠券类型id,对应t_coupon_type表的id
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     * 获取优惠券码
     *
     * @return coupon_code - 优惠券码
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * 设置优惠券码
     *
     * @param couponCode 优惠券码
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * 获取优惠券url
     *
     * @return coupon_url - 优惠券url
     */
    public String getCouponUrl() {
        return couponUrl;
    }

    /**
     * 设置优惠券url
     *
     * @param couponUrl 优惠券url
     */
    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl;
    }

    /**
     * 获取分成比例，如30.5=30.5%
     *
     * @return divide_ratio - 分成比例，如30.5=30.5%
     */
    public Double getDivideRatio() {
        return divideRatio;
    }

    /**
     * 设置分成比例，如30.5=30.5%
     *
     * @param divideRatio 分成比例，如30.5=30.5%
     */
    public void setDivideRatio(Double divideRatio) {
        this.divideRatio = divideRatio;
    }

    /**
     * 获取商家类型id
     *
     * @return business_type - 商家类型id
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 设置商家类型id
     *
     * @param businessType 商家类型id
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取商家id
     *
     * @return business_id - 商家id
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * 设置商家id
     *
     * @param businessId 商家id
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取商家平台名称
     *
     * @return business_platform - 商家平台名称
     */
    public String getBusinessPlatform() {
        return businessPlatform;
    }

    /**
     * 设置商家平台名称
     *
     * @param businessPlatform 商家平台名称
     */
    public void setBusinessPlatform(String businessPlatform) {
        this.businessPlatform = businessPlatform;
    }

    /**
     * 获取状态：1=上线 0=下线
     *
     * @return status - 状态：1=上线 0=下线
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1=上线 0=下线
     *
     * @param status 状态：1=上线 0=下线
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取价值
     *
     * @return values - 价值
     */
    public String getValues() {
        return values;
    }

    /**
     * 设置价值
     *
     * @param values 价值
     */
    public void setValues(String values) {
        this.values = values;
    }

    /**
     * 获取有效开始时间
     *
     * @return start_time - 有效开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置有效开始时间
     *
     * @param startTime 有效开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取有效结束时间
     *
     * @return end_time - 有效结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效结束时间
     *
     * @param endTime 有效结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取可用数量
     *
     * @return amount - 可用数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置可用数量
     *
     * @param amount 可用数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取锁定数量
     *
     * @return lock_amount - 锁定数量
     */
    public Integer getLockAmount() {
        return lockAmount;
    }

    /**
     * 设置锁定数量
     *
     * @param lockAmount 锁定数量
     */
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }

    /**
     * 获取历史消耗数量
     *
     * @return history_amount - 历史消耗数量
     */
    public Integer getHistoryAmount() {
        return historyAmount;
    }

    /**
     * 设置历史消耗数量
     *
     * @param historyAmount 历史消耗数量
     */
    public void setHistoryAmount(Integer historyAmount) {
        this.historyAmount = historyAmount;
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
     * 获取优惠券素材列表
     * @return
     */
    public List<TGoodsCouponMaterial> getMaterialList() {
        return materialList;
    }

    /**
     * 设置优惠券素材列表
     * @param materialList
     */
    public void setMaterialList(List<TGoodsCouponMaterial> materialList) {
        this.materialList = materialList;
    }


    /**
     * 获取优惠券类型对象
     * @return
     */
    public TGoodsCouponType getGoodsCouponType() {
        return goodsCouponType;
    }

    /**
     * 设置优惠券类型
     * @param goodsCouponType
     */
    public void setGoodsCouponType(TGoodsCouponType goodsCouponType) {
        this.goodsCouponType = goodsCouponType;
    }

    /**
     * 获取多语言列表
     * @return
     */
    public List<TGoodsCouponLang> getLangs() {
        return langs;
    }

    /**
     * 设置多语言列表
     * @param langs
     */
    public void setLangs(List<TGoodsCouponLang> langs) {
        this.langs = langs;
    }
}