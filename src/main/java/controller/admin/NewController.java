package controller.admin;

import constant.SystemConstant;
import model.NewModel;
import service.ICategoryService;
import service.INewService;
import service.Impl.PageRequest;
import service.Pageble;
import sort.Sorter;
import utils.FormUtil;
import utils.MessgaeUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewController", urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {
    @Inject
    private INewService newService;

    @Inject
    private ICategoryService categoryService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewModel model = FormUtil.toModel(NewModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newService.findAll(pageble));
            model.setTotalItem(newService.totalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/view/admin/new/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = newService.findOne(model.getId());
            }
            request.setAttribute("categories", categoryService.findAll());
            view = "/view/admin/new/edit.jsp";
        }
        MessgaeUtil.getMessage(request);
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
