package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.beans.Repertory;
import com.zw.beans.User;
import com.zw.dao.RepertoryMapper;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.responseDTO.RepertoryResponse;
import com.zw.service.IRepertoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-15
 */
@Service
public class RepertoryService extends ServiceImpl<RepertoryMapper, Repertory> implements IRepertoryService {


    @Autowired
    private RepertoryMapper mapper;
    @Override
    public IPage<Repertory> listRepertoryByCondition(RepertoryDTO dto, User user) {
        Page<Repertory> page = new Page<>();
        dto.setShopId(user.getShopId());
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        QueryWrapper<RepertoryDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("re.dr", false);//查询未删除信息
        if (dto.getShopId() != null) {
            queryWrapper.eq(StringUtils.isNotBlank(dto.getShopId()),"re.shop_id", dto.getShopId());
        }
        if (dto.getCategoryId() != null) {
            queryWrapper.eq(StringUtils.isNotBlank(dto.getCategoryId()),"re.category_id", dto.getCategoryId());
        }
        if (dto.getRepertoryId() != null) {
            queryWrapper.eq(StringUtils.isNotBlank(dto.getRepertoryId()),"re.repertory_id", dto.getRepertoryId());
        }
        if (dto.getSpecificationId() != null) {
            queryWrapper.eq(StringUtils.isNotBlank(dto.getSpecificationId()),"re.specification_id", dto.getSpecificationId());
        }
        return mapper.listRepertoryByCondition(page, queryWrapper);

    }

    @Override
    public int addRepertory(Repertory model) {
        return mapper.insert(model);
    }

    @Override
    public int UpdateRepertory(Repertory dto) {
        QueryWrapper<Repertory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", dto.getCategoryId())
                .eq("specification_id", dto.getSpecificationId())
                .eq("shop_id", dto.getShopId());
        return mapper.update(dto, queryWrapper);
    }

}
