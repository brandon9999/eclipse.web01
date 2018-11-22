package com.javacan.mvc.template;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * get Ŀ���� �±׸� ������ Ŭ�����μ� put Ŀ���� �±׿��� ������
 * <name, uri> ������ ����Ͽ� uri�� �ش��ϴ� �������� include�Ѵ�.
 * 
 * @author �ֹ���
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
