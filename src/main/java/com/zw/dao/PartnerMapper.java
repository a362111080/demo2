package com.zw.dao;

import com.zw.beans.Partner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.PartnerRequestDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface PartnerMapper extends BaseMapper<Partner> {
    List<Partner> GetPartnerList(PartnerRequestDTO model);

    int UpdatePartner(Partner model);

    int DeletePartner(List<String> ids);

}
