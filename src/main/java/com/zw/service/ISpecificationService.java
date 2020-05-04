package com.zw.service;

import com.zw.beans.Specification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.requestDTO.SpecificationRequestDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface ISpecificationService extends IService<Specification> {

    List<Specification> GetSpecificationList(SpecificationRequestDTO model);

    int AddSpecification(Specification model);

    int UpdateSpecification(Specification model);

    int DeleteSpecification(SpecificationRequestDTO specificationRequestDTO);

}
