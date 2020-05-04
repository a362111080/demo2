package com.zw.requestDTO;

import com.zw.tool.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StaffRequestDTO extends PageDTO implements Serializable {



    private static final long serialVersionUID = 2590091554922657835L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifytime;


    /**
     * 所属店铺id
     */
    private String shopId;


    /**
     * 删除标识 0:停用 1:启用(默认)
     */
    private Integer dr = 0;

    /**
     * 前端传来的id数组(批量操作)
     */
    private List<String> ids;

    /**
     * 查询条件
     */
    private String type;

    /**
     * 查询条件
     */
    private String status;


    /**
     * 查询条件
     */
    private String shortname;


    /**
     * 查询条件
     */
    private String name;



}
