package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.beans.Staff;
import com.zw.beans.User;
import com.zw.dao.StaffMapper;
import com.zw.requestDTO.StaffRequestDTO;
import com.zw.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-18
 */
@Service
public class  StaffService extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Autowired
    StaffMapper staffMapper;
    @Override
    public int addStaff(Staff model) {
        return staffMapper.insert(model);
    }

    @Override
    public IPage<Staff> listRepertoryByCondition(StaffRequestDTO dto, User user) {
        dto.setShopId(user.getShopId());
        Page<Staff> iPage = new Page<>();
        iPage.setCurrent(dto.getCurrent());
        iPage.setSize(dto.getSize());
        QueryWrapper<StaffRequestDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dr", false);
        if (dto.getName() != null) {
            queryWrapper.like("name", dto.getName());
        }
        if (dto.getStatus()!= null) {
            queryWrapper.eq("status", dto.getStatus());
        }
        return staffMapper.listRepertoryByCondition(iPage, queryWrapper);
    }

    @Override
    public int updateStaff(Staff model) {
        return staffMapper.UpdateStaff(model);
    }

    @Override
    public int deteleStaff(StaffRequestDTO dto) {
        return staffMapper.DeleteStaff(dto.getIds());
    }

}
