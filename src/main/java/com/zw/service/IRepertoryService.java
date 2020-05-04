package com.zw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.beans.Repertory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.beans.User;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.responseDTO.RepertoryResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-15
 */
public interface IRepertoryService extends IService<Repertory> {


    IPage<Repertory> listRepertoryByCondition(RepertoryDTO dto, User user);

    int addRepertory(Repertory dto);

    int UpdateRepertory(Repertory dto);

}
