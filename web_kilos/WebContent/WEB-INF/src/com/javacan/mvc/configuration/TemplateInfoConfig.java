package com.javacan.mvc.configuration;

import java.util.List;

/**
 * @author �ֹ���
 */
public class TemplateInfoConfig {
    /** ���ø��� �̸� */
    private String name;
    /** ���ø����� ���Ǵ� JSP �������� URI */
    private String uri;
    /** ���ø� �� ����� ���� */
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
