package com.zw.service;

import com.zw.beans.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.requestDTO.CategoryRequestDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
public interface ICategoryService extends IService<Category> {
    int AddCategory(Category model);

    List<Category> GetCategoryList(CategoryRequestDTO model);

    int UpdateCategory(Category model);

    int DeleteCategory(CategoryRequestDTO model);

}
