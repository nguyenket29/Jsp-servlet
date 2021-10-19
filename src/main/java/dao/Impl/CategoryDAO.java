package dao.Impl;

import dao.ICategoryDAO;
import mapper.CategoryMapper;
import model.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "select * from category";
        return query(sql,new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(Long id) {
        String sql = "select * from category where id = ?";
        List<CategoryModel> categories = query(sql,new CategoryMapper(),id);
        return categories.isEmpty() ? null : categories.get(0);
    }

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "select * from category where code = ?";
        List<CategoryModel> categories = query(sql,new CategoryMapper(),code);
        return categories.isEmpty() ? null : categories.get(0);
    }
}
