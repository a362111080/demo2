package com.zw.service.impl;

import com.zw.beans.Specification;
import com.zw.dao.SpecificationMapper;
import com.zw.requestDTO.SpecificationRequestDTO;
import com.zw.service.ISpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
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
public class SpecificationService extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Override
    public List<Specification> GetSpecificationList(SpecificationRequestDTO model) {
        return specificationMapper.getSpecificationList(model);
    }

    @Override
    public int AddSpecification(Specification model) {
        return specificationMapper.AddSpecification(model);
    }

    @Override
    public int UpdateSpecification(Specification model) {
        return specificationMapper.UpdateSpecification(model);
    }

    @Override
    public int DeleteSpecification(SpecificationRequestDTO specificationRequestDTO) {
        return specificationMapper.DeleteSpecification(specificationRequestDTO.getIds());
    }

}
