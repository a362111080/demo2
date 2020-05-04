package com.zw.service;

import com.zw.beans.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.requestDTO.SupplierRequestDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface ISupplierService extends IService<Supplier> {

    List<Supplier> GetSupplierList(SupplierRequestDTO model);

    int AddSupplier(Supplier model);

    int UpdateSupplier(Supplier model);

    int DeleteSupplier(SupplierRequestDTO supplier);
}
