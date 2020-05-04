package com.zw.requestDTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zw.tool.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
@Data
public class DeliverRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 2590091554922657835L;

    private String deliverId;

    private String staffId;

    private String categoryId;

    private String specificationId;

    private String status;

    private String creator;

    private String mender;

    private Date createtime;

    private Date modifytime;

    private String shopId;

    private BigDecimal sum;

    private Boolean dr;

    /**
     * 前端传来的id数组(批量操作)
     */
    private List<String> ids;

}
