package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
@TableName("tbz_partner")
public class Partner extends Model<Partner> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = false)
    @TableId(type = IdType.UUID)
    private String partnerId;

    private String shopId;

    private String name;

    private String status;

    private String remark;

    private String cityId;

    private String phone;

    private Date createtime;

    private Date modifytime;

    private Boolean dr;


    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Boolean getDr() {
        return dr;
    }

    public void setDr(Boolean dr) {
        this.dr = dr;
    }

    @Override
    protected Serializable pkVal() {
        return this.partnerId;
    }

    @Override
    public String toString() {
        return "Partner{" +
        "partnerId=" + partnerId +
        ", shopId=" + shopId +
        ", name=" + name +
        ", status=" + status +
        ", remark=" + remark +
        ", cityId=" + cityId +
        ", phone=" + phone +
        ", createtime=" + createtime +
        ", modifytime=" + modifytime +
        ", dr=" + dr +
        "}";
    }
}
