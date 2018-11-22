package com.javacan.mvc.configuration;

import java.util.List;

/**
 * @author 최범균
 */
public class TemplateInfoConfig {
    /** 템플릿의 이름 */
    private String name;
    /** 템플릿으로 사용되는 JSP 페이지의 URI */
    private String uri;
    /** 템플릿 맵 목록을 저장 */
    private List mapList;
    
    public TemplateInfoConfig() {
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
    
    public void addTemplateMapConfig(TemplateMapConfig map) {
        if (mapList == null) {
             mapList = new java.util.ArrayList();
        }
        mapList.add(map);
    }
    public List getTemplateMapConfigList() {
        return mapList;
    }
}
