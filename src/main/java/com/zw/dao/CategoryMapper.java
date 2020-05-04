package com.zw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.beans.Category;
import com.zw.requestDTO.CategoryRequestDTO;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> GetCategoryList(CategoryRequestDTO model);

    int UpdateCategory(Category model);

    int DeleteCategory(List<String> ids);

}
