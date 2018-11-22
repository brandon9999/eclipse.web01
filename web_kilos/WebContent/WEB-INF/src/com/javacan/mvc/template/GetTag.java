package com.javacan.mvc.template;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * get 커스텀 태그를 구현한 클래스로서 put 커스텀 태그에서 지정한
 * <name, uri> 정보를 사용하여 uri에 해당하는 페이지를 include한다.
 * 
 * @author 최범균
 */
public class GetTag extends TagSupport {
    
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int doStartTag() throws JspException {
        Map map = (Map)pageContext.getAttribute(
                        TemplateConstant.REQUEST_ATTRIBUTE_NAME, 
                        PageContext.REQUEST_SCOPE);
        String uri = (String)map.get(name);
        if (uri != null) {
            try {
                pageContext.include(uri);
            } catch(IOException ex) {
                throw new JspException(ex);
            } catch(ServletException ex) {
                throw new JspException(ex);
            }
        }
        return SKIP_BODY;
    }
    
    public int doEndTag() {
        name = null;
        return EVAL_PAGE;
    }
}
