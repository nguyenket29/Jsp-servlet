package dao;

import model.NewModel;
import service.Pageble;

import java.util.List;

public interface INewDAO extends GenericDAO<NewModel> {
    NewModel findOne(Long id);
    List<NewModel> findByCategoryId(Long categoryId);
    Long save(NewModel newModel);
    void update(NewModel newModel);
    void delete(long id);
    List<NewModel> findAll(Pageble pageble);
    int totalItem();
}
