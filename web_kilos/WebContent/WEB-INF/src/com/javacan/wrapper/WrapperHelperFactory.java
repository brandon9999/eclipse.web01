package com.javacan.wrapper;

import java.util.Map;

/**
 * ���� ���۸� �������ִ� ���丮 Ŭ����
 * @author �ֹ���
 */
public class WrapperHelperFactory {
    private static WrapperHelperFactory instance = new WrapperHelperFactory();
    public static WrapperHelperFactory getInstance() {
        return instance;
    }
    
    private WrapperHelperFactory() {}
    
    /** WrapperHelper�� �����ϰ� �ִ�. */
    private Map repository = new java.util.HashMap();
    
    public WrapperHelper createWrapperHelper(Class beanClass)
    throws WrapperHelperException {
        WrapperHelper helper = (WrapperHelper)repository.get(beanClass.getName());
        if (helper == null) {
            synchronized(this) {
                helper = (WrapperHelper)repository.get(beanClass.getName());
                if (helper == null) {
                    try {
                        ORMapping mapping = 
                            WrapperHelperUtil.createORMapping(beanClass);
                        helper = new WrapperHelper(mapping);
                        repository.put(beanClass.getName(), helper);
                    } catch(WrapperHelperUtilException ex) {
                        throw new WrapperHelperException(ex.getMessage(), ex);
                    }
                }
            }
        }
        return helper;
    }
}
