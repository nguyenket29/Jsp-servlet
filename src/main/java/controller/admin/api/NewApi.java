package controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.NewModel;
import model.UserModel;
import service.INewService;
import utils.HttpUtil;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewApi", urlPatterns = {"/api-admin-new"})
public class NewApi extends HttpServlet {

    @Inject
    private INewService newService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //covert từ json truyền sang model
        //trả lời cho câu hỏi làm sao binding các key,value trong json vào textField trong model
        NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        //xử lý chỗ lưu thông tin người dùng rồi từ key lấy ra username
        newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        mapper.writeValue(response.getOutputStream(), newModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel updateNew =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updateNew = newService.update(updateNew);
        mapper.writeValue(response.getOutputStream(), updateNew);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //giao tiếp giữa client và api
        NewModel deleteNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newService.delete(deleteNew.getIds());
        mapper.writeValue(response.getOutputStream(),"{}");
    }
}
