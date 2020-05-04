package com.zw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.beans.Deliver;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.beans.User;
import com.zw.requestDTO.DeliverRequestDTO;
import com.zw.tool.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
public interface IDeliverService extends IService<Deliver> {

    IPage<Deliver> listDeliverByCondition(DeliverRequestDTO dto, User user);

    Message addDeliver(Deliver deliver,User user);

}
