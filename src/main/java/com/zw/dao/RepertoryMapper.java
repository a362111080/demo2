package com.zw.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zw.beans.Repertory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.responseDTO.RepertoryResponse;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-15
 */
public interface RepertoryMapper extends BaseMapper<Repertory> {

    IPage<Repertory> listRepertoryByCondition(IPage<Repertory> page, @Param(Constants.WRAPPER) QueryWrapper<RepertoryDTO> wrapper);

}
