package com.zw.dao;

import com.zw.beans.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.SupplierRequestDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface SupplierMapper extends BaseMapper<Supplier> {

    List<Supplier> GetSupplierList(SupplierRequestDTO model);

    int UpdateSupplier(Supplier model);

    int DeleteSupplier(List<String> ids);

}
