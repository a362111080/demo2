package com.zw.responseDTO;

import com.zw.tool.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RepertoryResponse implements Serializable {



    private static final long serialVersionUID = 2590091554922657835L;

    private String repertoryId;

    private String categoryId;

    private Integer sum;

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



}


