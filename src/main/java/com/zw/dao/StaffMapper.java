package com.zw.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zw.beans.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.StaffRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-18
 */
public interface StaffMapper extends BaseMapper<Staff> {

    int UpdateStaff(Staff model);

    int DeleteStaff(List<String> ids);

    IPage<Staff> listRepertoryByCondition(IPage<Staff> page, @Param(Constants.WRAPPER) QueryWrapper<StaffRequestDTO> wrapper);


}
