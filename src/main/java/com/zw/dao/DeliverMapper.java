package com.zw.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zw.beans.Deliver;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.DeliverRequestDTO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
public interface DeliverMapper extends BaseMapper<Deliver> {

    IPage<Deliver> listDeliverByCondition(IPage<Deliver> page, @Param(Constants.WRAPPER) QueryWrapper<DeliverRequestDTO> wrapper);

}
