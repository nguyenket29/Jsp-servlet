package utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    //xử lý công việc quản lý thông tin người dùng

    private static SessionUtil sessionUtil = null;

    public static SessionUtil getInstance() {
        if(sessionUtil == null){
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public void pushValue(HttpServletRequest request, String key, Object value){
        //có request để khởi tạo session
        //session ở đây để get,put,delete giá trị
        request.getSession().setAttribute(key,value);
    }

    public Object getValue(HttpServletRequest request, String key){
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key){
        request.getSession().removeAttribute(key);
    }

}
