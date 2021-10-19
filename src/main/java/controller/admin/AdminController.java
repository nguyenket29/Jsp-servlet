package controller.admin;

import model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminController", urlPatterns = {"/admin-home"})
public class AdminController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        userModel.setFullName("Hello world !");
        userModel.setUserName("Kết");
        request.setAttribute("model",userModel);
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/home_admin.jsp");
        rd.forward(request,response);
    }
}
