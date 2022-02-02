package rest;

public class URLManager {
    public static String reformatURI(String URI){
        if(URI!=null && !URI.startsWith("/")){
            URI="/"+URI;
            return URI;
        }
        return URI;
    }
}
