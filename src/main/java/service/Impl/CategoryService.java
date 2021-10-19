package service.Impl;

import dao.ICategoryDAO;
import model.CategoryModel;
import service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
