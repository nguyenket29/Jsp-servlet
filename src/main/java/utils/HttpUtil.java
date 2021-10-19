package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
    private String value;

    public HttpUtil(String value) {
        this.value = value;
    }

    //json -> string json
    //Sử dụng readValue() để convert Json String sang Java Object.
    public static HttpUtil of(BufferedReader reader){
        //reader : json
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return new HttpUtil(sb.toString());
    }

    //mapping vào model
    //đã convert từ string json vaf lấy chuỗi String mapp vào model
    public <T> T toModel(Class<T> tClass){
        try {
            return new ObjectMapper().readValue(value,tClass);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
