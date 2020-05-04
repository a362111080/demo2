package com.zw.dao;

import com.zw.beans.Specification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.SpecificationRequestDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface SpecificationMapper extends BaseMapper<Specification> {

    List<Specification> getSpecificationList(SpecificationRequestDTO model);

    int	AddSpecification(Specification model);

    int UpdateSpecification(Specification model);

    int DeleteSpecification(List<String> ids);


}
