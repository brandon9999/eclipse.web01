package com.javacan.mvc.template;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * insert Ŀ���� �±׸� ������ Ŭ�����μ� put �±װ� <name, uri> ����
 * ������ Map�� �����ؼ� request�� attribute�� �����Ѵ�. �̶� ���Ǵ�
 * attribute�� �̸��� TemplateConstant.REQUEST_ATTRIBUTE_NAME �̴�.
 * @author �ֹ���
 */
public class InsertTag extends BodyTagSupport {
    
    /** ����� URI */
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
