package io.batcloud.model.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_goods_coupon_type")
public class TGoodsCouponType implements Serializable {
    /**
     * 优惠券类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 优惠券类型名称
     */
    @Column(name = "coupon_type")
    private String couponType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后修改人
     */
    @Column(name = "update_by")
    private String updateBy;

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
     * 获取优惠券类型ID
     *
     * @return id - 优惠券类型ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置优惠券类型ID
     *
     * @param id 优惠券类型ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取优惠券类型名称
     *
     * @return coupon_type - 优惠券类型名称
     */
    public String getCouponType() {
        return couponType;
    }

    /**
     * 设置优惠券类型名称
     *
     * @param couponType 优惠券类型名称
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取最后修改人
     *
     * @return update_by - 最后修改人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置最后修改人
     *
     * @param updateBy 最后修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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