package com.javacan.mvc.template;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import java.util.Map;

/**
 * put 커스텀 태그를 구현한 클래스로서 get 커스텀 태그가 사용할
 * <name, uri> 정보를 request의 저장된 Map에 저장한다.
 * Map을 저장한 request의 attribute는 TemplateConstant.REQUEST_ATTRIBUTE_NAME이다.
 * 
 * @author 최범균
 */
public class PutTag extends TagSupport {
    
    private String name;
    private String uri;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public int doStartTag() throws JspException {
        Tag parent = getParent();
        if (parent == null || !(parent instanceof InsertTag)) {
            throw new JspException("The parent of PutTag must be InsertTag!");
        }
        Map map = (Map)pageContext.getAttribute(
                        TemplateConstant.REQUEST_ATTRIBUTE_NAME, 
                        PageContext.REQUEST_SCOPE);
        map.put(name, uri);
        
        return SKIP_BODY;
    }
    
    public int doEndTag() {
        name = null;
        uri = null;
        
        return EVAL_PAGE;
    }
}
