package com.zw.service.impl;

import com.zw.beans.Category;
import com.zw.dao.CategoryMapper;
import com.zw.requestDTO.CategoryRequestDTO;
import com.zw.service.ICategoryService;
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
public class CategoryService extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper mapper;
    @Override
    public int AddCategory(Category model) {
        return mapper.insert(model);
    }
    @Override
    public List<Category> GetCategoryList(CategoryRequestDTO model) {
        return mapper.GetCategoryList(model);
    }

    @Override
    public int UpdateCategory(Category model) {
        return mapper.UpdateCategory(model);
    }

    @Override
    public int DeleteCategory(CategoryRequestDTO model) {
        return mapper.DeleteCategory(model.getIds());
    }
}
