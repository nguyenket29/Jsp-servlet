package utils;

import constant.SystemConstant;

import javax.servlet.http.HttpServletRequest;

public class MessgaeUtil {
    public static void getMessage(HttpServletRequest request){
        if (request.getParameter("message") != null){
            String messageRespone ="",alert = "";
            String message = request.getParameter("message");
            if (message.equals(SystemConstant.INSERT_SUCCESS)){
                messageRespone = SystemConstant.INSERT_SUCCESS;
                alert = SystemConstant.SUCCESS;
            }else if(message.equals(SystemConstant.UPDATE_SUCCESS)){
                messageRespone = SystemConstant.UPDATE_SUCCESS;
                alert = SystemConstant.SUCCESS;
            }else if(message.equals(SystemConstant.DELETE_SUCCESS)){
                messageRespone = SystemConstant.DELETE_SUCCESS;
                alert = SystemConstant.SUCCESS;
            }else if(message.equals(SystemConstant.ERROR_SYSTEM)){
                messageRespone = SystemConstant.ERROR_SYSTEM;
                alert = SystemConstant.ALERT_DANGER;
            }
            request.setAttribute("messageRespone",messageRespone);
            request.setAttribute("alert",alert);
        }
    }
}
