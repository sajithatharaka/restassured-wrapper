package rest;

import java.util.HashMap;
import java.util.Map;

public class HttpRestContext implements IHttpObject{
    public HttpRestMethod httpRestMethod;
    public IAuth auth;
    public String baseURL;
    public RestContentType requestContentType=RestContentType.Text;
    public String URI;
    public String requestBody;
    public Map<String,Object> queryParams=new HashMap<>();
    public Map<String,Object> pathParams=new HashMap<>();
    public Map<String,Object> requestHeaderParams=new HashMap<>();
    public Map<String,Object> formParams=new HashMap<>();
    public Map<String, Object> multiParts=new HashMap<>();
    public int statusCode;
    public String responseBody;
    public Long timeTakenInSeconds;
    public String responseContentType;
    public Map<String,Object> responseHeaderParams=new HashMap<>();
}
