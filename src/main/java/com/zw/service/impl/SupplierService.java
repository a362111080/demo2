package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.zw.beans.Supplier;
import com.zw.dao.SupplierMapper;
import com.zw.requestDTO.SupplierRequestDTO;
import com.zw.service.ISupplierService;
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
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {


    @Autowired
    private SupplierMapper mapper;

    @Override
    public List<Supplier> GetSupplierList(SupplierRequestDTO model) {
        return mapper.GetSupplierList(model);
    }
    @Override
    public int AddSupplier(Supplier model) {
        return mapper.insert(model);
    }
    @Override
    public int UpdateSupplier(Supplier model) {
            return mapper.UpdateSupplier(model);
    }

    @Override
    public int DeleteSupplier(SupplierRequestDTO supplier) {
        List<String> ids = supplier.getIds();
        return mapper.DeleteSupplier(ids);
    }

}

