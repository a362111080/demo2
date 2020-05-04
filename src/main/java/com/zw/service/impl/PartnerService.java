package com.zw.service.impl;

import com.zw.beans.Partner;
import com.zw.dao.PartnerMapper;
import com.zw.requestDTO.PartnerRequestDTO;
import com.zw.service.IPartnerService;
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
 * @since 2019-11-12
 */
@Service
public class PartnerService extends ServiceImpl<PartnerMapper, Partner> implements IPartnerService {

    @Autowired
    private PartnerMapper mapper;

    @Override
    public List<Partner> GetPartnerList(PartnerRequestDTO model) {
        return mapper.GetPartnerList(model);
    }

    @Override
    public int AddPartner(Partner model) {
        return mapper.insert(model);
    }

    @Override
    public int UpdatePartner(Partner model) {
        return mapper.UpdatePartner(model);
    }

    @Override
    public int DeletePartner(PartnerRequestDTO partner) {
        List<String> ids = partner.getIds();
        return mapper.DeletePartner(ids);
    }

}
