package controller.web;

import model.UserModel;
import service.ICategoryService;
import service.IUserService;
import utils.FormUtil;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(name = "HomeController",urlPatterns = {"/home-web","/dang-nhap","/thoat","/dang-ky"})
public class HomeController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IUserService userService;

    ResourceBundle bundle = ResourceBundle.getBundle("mess");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")){
            UserModel model = FormUtil.toModel(UserModel.class,request);
            model = userService.findByUser(model.getUserName(),model.getPassword(), 1);
            if(model !=null){
                SessionUtil.getInstance().pushValue(request,"USERMODEL",model);
                if(model.getRole().getCode().equals("USER")){
                    response.sendRedirect(request.getContextPath()+"/home-web");
                }else if(model.getRole().getCode().equals("ADMIN")){
                    response.sendRedirect(request.getContextPath()+"/admin-home");
                }
            }else {
                response.sendRedirect(request.getContextPath()+
                        "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }else if(action != null && action.equals("resgister")){
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            UserModel model = FormUtil.toModel(UserModel.class,request);
            Long models = userService.saveUser(model.getUserName(),
                    model.getPassword(),
                    model.getStatus(),model.getRoleId());
            SessionUtil.getInstance().pushValue(request,"USER",
                    models);
            if(models != null){
                response.sendRedirect(request.getContextPath()+"/dang-nhap");
            }else {
                response.sendRedirect(request.getContextPath()+"/home-web");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")){
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if(message != null & alert != null){
                request.setAttribute("message",bundle.getString(message));
                request.setAttribute("alert",alert);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/view/login/login.jsp");
            rd.forward(request,response);
        }else if(action != null && action.equals("logout")){
            SessionUtil.getInstance().removeValue(request,"USERMODEL");
            response.sendRedirect(request.getContextPath()+"/home-web");
        }else if(action != null && action.equals("resgister")){
            RequestDispatcher rd = request.getRequestDispatcher("/view/resgister/resgister.jsp");
            rd.forward(request,response);
        }else {
            request.setAttribute("categories",categoryService.findAll());
            RequestDispatcher rd = request.getRequestDispatcher("/view/web/home_web.jsp");
            rd.forward(request,response);
        }
    }
}
