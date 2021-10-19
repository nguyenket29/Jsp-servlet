package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class FormUtil {
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request){
        T obj = null;
        try {
            obj = tClass.newInstance();
            //lấy ra cái map với kiểu key = value
            // rồi map và cái field của model
            //value ở đây có thể là 1 mảng String
            //lấy cái param trên url xuống thành key=value vào map
            BeanUtils.populate(obj,request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
