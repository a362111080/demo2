package com.zw.service;

import com.zw.beans.Partner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.requestDTO.PartnerRequestDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface IPartnerService extends IService<Partner> {
    List<Partner> GetPartnerList(PartnerRequestDTO model);

    int AddPartner(Partner model);

    int UpdatePartner(Partner model);

    int DeletePartner(PartnerRequestDTO supplier);

}
