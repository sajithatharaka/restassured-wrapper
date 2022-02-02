package rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import java.util.Map;


public class Rest {
    public static void setBaseURL(String baseURL){
        Assertions.assertThat(baseURL).isNotEmpty();
        RestAssured.baseURI=baseURL;
    }

    private static RequestSpecification authorization(IAuth context){
        if(context==null){
            return RestAssured.given();
        }
        return context.auth();
    }

    private static RequestSpecification init(HttpRestContext context){
        Assertions.assertThat(context).isNotNull();
        context.URI=URLManager.reformatURI(context.URI);
        setBaseURL(context.baseURL);

        RequestSpecification requestSpecification=authorization(context.auth);

        if(!context.requestHeaderParams.isEmpty()){
            requestSpecification.headers(context.responseHeaderParams);
        }

        if(!context.pathParams.isEmpty()){
            requestSpecification.pathParams(context.pathParams);
        }

        if(!context.formParams.isEmpty()){
            requestSpecification.formParams(context.formParams);
        }

        if(!context.queryParams.isEmpty()){
            requestSpecification.queryParams(context.queryParams);
        }

        if(context.requestContentType!=null && !context.requestContentType.equals("")){
            requestSpecification.contentType(context.requestContentType.getContentType());
        }
        return requestSpecification;
    }

    public static HttpRestContext GET(HttpRestContext context){
        Response response = null;
        RequestSpecification requestSpecification=init(context);
        if(context.httpRestMethod==null || context.httpRestMethod.equals("")){
            context.httpRestMethod=HttpRestMethod.GET;
        }
        if(context.URI!=null && !context.URI.equals("")){
            response=requestSpecification.get(context.URI);
        }
        context.statusCode= response.statusCode();
        context.responseContentType=response.getContentType();
        for (Header header:response.headers()){
            context.responseHeaderParams.put(header.getName(),header.getValue());
        }
        context.responseBody=response.getBody().asString();
        context.timeTakenInSeconds=response.getTime();
        return context;
    }

    public static HttpRestContext POST(HttpRestContext context){
        Response response=null;
        RequestSpecification requestSpecification=init(context);
        if(context.httpRestMethod==null || context.httpRestMethod.equals("")){
            context.httpRestMethod=HttpRestMethod.POST;
        }
        if(context.requestBody!=null && !context.requestBody.equals("")){
            requestSpecification.body(context.requestBody);
        }
        if(!context.multiParts.isEmpty()){
            for(Map.Entry<String,Object> entry:context.multiParts.entrySet()){
                requestSpecification.multiPart(entry.getKey(),entry.getValue());
            }
        }
        if(context.URI!=null && !context.URI.equals("")){
            response=requestSpecification.post(context.URI);
        }else {
            response=requestSpecification.post();
        }
        context.statusCode=response.getStatusCode();
        context.responseContentType=response.contentType();

        for(Header header:response.headers()){
            context.responseHeaderParams.put(header.getName(),header.getValue());
        }
        context.responseBody=response.getBody().asString();
        context.timeTakenInSeconds=response.getTime();
        return context;
    }
}
