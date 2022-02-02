package rest;

public enum RestContentType {
    JSON("application/json"),
    Text("text/plain"),
    FOMR_DATA("multipart/form-data"),
    FORM_URL_ENCODED("application/x-www-form-urlencoded");

    private String contentType;
    private RestContentType(String contentType){
        this.contentType=contentType;
    }

    public String getContentType(){
        return contentType;
    }
}
