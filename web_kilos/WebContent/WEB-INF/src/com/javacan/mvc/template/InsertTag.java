package com.javacan.mvc.template;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * insert 커스텀 태그를 구현한 클래스로서 put 태그가 <name, uri> 쌍을
 * 저장할 Map을 생성해서 request의 attribute에 저장한다. 이때 사용되는
 * attribute의 이름은 TemplateConstant.REQUEST_ATTRIBUTE_NAME 이다.
 * @author 최범균
 */
public class InsertTag extends BodyTagSupport {
    
    /** 사용할 URI */
    private String template;
    
    public void setTemplate(String template) {
        this.template = template;
    }
    
    public int doStartTag() throws JspException {
        Object object = pageContext.getAttribute(
                TemplateConstant.REQUEST_ATTRIBUTE_NAME, PageContext.REQUEST_SCOPE);
        
        if (!(object instanceof Map)) {
            pageContext.setAttribute(TemplateConstant.REQUEST_ATTRIBUTE_NAME, 
                                     new java.util.HashMap(),
                                     PageContext.REQUEST_SCOPE);
        }
        
        return EVAL_BODY_BUFFERED;
    }
    
    public int doEndTag() throws JspException {
        try {
            pageContext.include(template);
            return EVAL_PAGE;
        } catch(IOException ex) {
            throw new JspException(ex);
        } catch(ServletException ex) {
            throw new JspException(ex);
        } finally {
            template = null;
        }
    }
}
