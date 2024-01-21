package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ResponseUtil {

    private int code;
    private String message;
    private Object data;

    public ResponseUtil(int code ,String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    ResponseUtil(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public static void sendJsonResponse(HttpServletResponse response, int code, String message, Object data) throws IOException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonResponse = objectMapper.createObjectNode();
        response.setContentType("application/json");
        jsonResponse.put("code", code);
        jsonResponse.put("message", message);
        jsonResponse.set("data", objectMapper.valueToTree(data));

        String jsonData = objectMapper.writeValueAsString(jsonResponse);

        response.setStatus(code);
        response.getWriter().write(jsonData);
    }



}
