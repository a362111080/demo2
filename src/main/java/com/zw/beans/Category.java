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
@TableName("tbz_category")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = false)
    @TableId(type = IdType.UUID)
    private String categoryId;

    private String shopId;

    private String name;

    private Date createtime;

    private Date modifytime;

    private String creator;

    private Boolean dr;


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getDr() {
        return dr;
    }

    public void setDr(Boolean dr) {
        this.dr = dr;
    }

    @Override
    protected Serializable pkVal() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
        "categoryId=" + categoryId +
        ", shopId=" + shopId +
        ", name=" + name +
        ", createtime=" + createtime +
        ", modifytime=" + modifytime +
        ", creator=" + creator +
        ", dr=" + dr +
        "}";
    }
}
