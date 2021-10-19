package service.Impl;

import dao.ICategoryDAO;
import dao.INewDAO;
import model.CategoryModel;
import model.NewModel;
import service.INewService;
import service.Pageble;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewService implements INewService {

    @Inject
    private INewDAO newDAO;

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        return newDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewModel save(NewModel newModel) {
        newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel = categoryDAO.findOneByCode(newModel.getCategoryCode());
        newModel.setCategoryId(categoryModel.getId());
        Long newId = newDAO.save(newModel);
        return newDAO.findOne(newId);
    }

    @Override
    public NewModel update(NewModel updateNew) {
        NewModel oldNew = newDAO.findOne(updateNew.getId());
        updateNew.setCreatedDate(oldNew.getCreatedDate());
        updateNew.setCreatedBy(oldNew.getCreatedBy());
        updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel = categoryDAO.findOneByCode(updateNew.getCategoryCode());
        updateNew.setCategoryId(categoryModel.getId());
        newDAO.update(updateNew);
        return newDAO.findOne(updateNew.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id:ids) {
            //delete comment (có khóa ngoại news_id)
            //delete news
            newDAO.delete(id);
        }
    }

    @Override
    public List<NewModel> findAll(Pageble pageble) {
        return newDAO.findAll(pageble);
    }

    @Override
    public NewModel findOne(Long id) {
        NewModel newModel = newDAO.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
        newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
    }

    @Override
    public int totalItem() {
        return newDAO.totalItem();
    }
}
