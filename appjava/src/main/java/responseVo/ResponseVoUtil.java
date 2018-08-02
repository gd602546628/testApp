package responseVo;


public class ResponseVoUtil {
    public static String success(Object data, String message, String code) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(data);
        responseVo.setMessage(message);
        responseVo.setCode(code);
        return responseVo.toString();
    }

    public static String success( String message, String code) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setCode(code);
        return responseVo.toString();
    }

    public static String error( String message, String code) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setCode(code);
        return responseVo.toString();
    }
}
