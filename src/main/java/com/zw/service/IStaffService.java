package com.zw.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zw.beans.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.beans.User;
import com.zw.requestDTO.StaffRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-18
 */
public interface IStaffService extends IService<Staff> {

    /**
     * 添加员工信息
     * @param
     * @return
     */
    int addStaff(Staff model);

    /**
     * 分页查询员工
     */
    IPage<Staff> listRepertoryByCondition(StaffRequestDTO dto, User user);

    /**
     * 更新员工信息
     */
    int updateStaff(Staff model);

    /**
     * 批量删除
     * @param
     * @return
     */
    int deteleStaff(StaffRequestDTO dto);


}
