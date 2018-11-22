package com.javacan.mvc.configuration;

/**
 * @author ÃÖ¹ü±Õ
 */
public class ViewInfoConfig {
    
    private String name;
    private String uri;
    private boolean redirect;
    private String useTemplate;
    
    public ViewInfoConfig() {
        redirect = false;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUri() {
        return uri;
    }
    
    public void setRedirectByString(String redirect) {
        if (redirect != null && redirect.equals("true")) {
            this.redirect = true;
        } else {
            this.redirect = false;
        }
    }
    
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
    
    public boolean isRedirect() {
        return redirect;
    }
    
    public void setUseTemplate(String useTemplate) {
        this.useTemplate = useTemplate;
    }
    public String getUseTemplate() {
        return useTemplate;
    }
}
