package com.zw.requestDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zw.beans.Repertory;
import com.zw.tool.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class RepertoryDTO extends PageDTO implements Serializable {



    private static final long serialVersionUID = 2590091554922657835L;

    private String repertoryId;

    private String categoryId;

    private BigDecimal sum;

    private String specificationId;

    private LocalDateTime createtime;

    private LocalDateTime modifytime;

    private Boolean dr;

    private String categoryName;

    private String specificationName;

    private String shopId;


    /**
     * 前端传来的id数组(批量操作)
     */
    private List<String> ids;

    /**
     * 列表
     */

    private List<Repertory> repertories;

}


