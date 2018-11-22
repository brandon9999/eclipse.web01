package com.javacan.mvc.configuration;

import org.apache.commons.digester.Digester;
import java.io.File;

/**
 * XML 설정 문서로부터 Configuration 객체를 생성한다.
 * @author 최범균
 */
public class ConfigurationDigester {
    public static final String SYSTEM_PROPERTY_NAME = "com.javacan.mvc.configuration.file";
    
    public static Configuration digest()
    throws ConfigurationDigesterException {
        String filePath = System.getProperty(SYSTEM_PROPERTY_NAME);
        return ConfigurationDigester.digest(filePath);
    }
    
    public static Configuration digest(String filePath)
    throws ConfigurationDigesterException {
        Digester digester = new Digester();
        digester.setValidating(false);
        
        digester.addObjectCreate("configuration", Configuration.class);

        digester.addObjectCreate("configuration/template-list/template-info", TemplateInfoConfig.class);
        digester.addSetProperties("configuration/template-list/template-info", "name", "name");
        digester.addSetProperties("configuration/template-list/template-info", "uri", "uri");
        digester.addObjectCreate("configuration/template-list/template-info/template-map", TemplateMapConfig.class);
        digester.addSetProperties("configuration/template-list/template-info/template-map", "name", "name");
        digester.addSetProperties("configuration/template-list/template-info/template-map", "uri", "uri");
        digester.addSetNext("configuration/template-list/template-info/template-map", "addTemplateMapConfig");
        digester.addSetNext("configuration/template-list/template-info", "addTemplateInfoConfig");
        
        digester.addObjectCreate("configuration/global-view/view-info", ViewInfoConfig.class);
        digester.addSetProperties("configuration/global-view/view-info", "name", "name");
        digester.addSetProperties("configuration/global-view/view-info", "uri", "uri");
        digester.addSetProperties("configuration/global-view/view-info", "redirect", "redirectByString");
        digester.addSetProperties("configuration/global-view/view-info", "useTemplate", "useTemplate");
        digester.addSetNext("configuration/global-view/view-info", "addViewInfoConfig");
        
        digester.addObjectCreate("configuration/command", CommandConfig.class);
        digester.addSetProperties("configuration/command", "name", "name");
        digester.addSetProperties("configuration/command", "type", "type");
        digester.addObjectCreate("configuration/command/command-view/view-info", ViewInfoConfig.class);
        digester.addSetProperties("configuration/command/command-view/view-info", "name", "name");
        digester.addSetProperties("configuration/command/command-view/view-info", "uri", "uri");
        digester.addSetProperties("configuration/command/command-view/view-info", "redirect", "redirectByString");
        digester.addSetProperties("configuration/command/command-view/view-info", "useTemplate", "useTemplate");
        digester.addSetNext("configuration/command/command-view/view-info", "addViewInfoConfig");
        digester.addSetNext("configuration/command", "addCommandConfig");
        
        digester.addObjectCreate("configuration/mapping", MappingConfig.class);
        digester.addSetProperties("configuration/mapping", "uri", "uri");
        digester.addSetProperties("configuration/mapping", "name", "name");
        digester.addSetNext("configuration/mapping", "addMappingConfig");
        
        digester.addObjectCreate("configuration/work-flow-set/work-flow", WorkFlowConfig.class);
        digester.addSetProperties("configuration/work-flow-set/work-flow", "name", "name");
        digester.addSetProperties("configuration/work-flow-set/work-flow", "errorView", "errorView");
        
        digester.addObjectCreate("configuration/work-flow-set/work-flow/work-flow-step", WorkFlowStepConfig.class);
        digester.addSetProperties("configuration/work-flow-set/work-flow/work-flow-step", "uri", "uri");
        digester.addSetProperties("configuration/work-flow-set/work-flow/work-flow-step", "next", "next");
        digester.addSetProperties("configuration/work-flow-set/work-flow/work-flow-step", "previous", "previous");
        digester.addSetNext("configuration/work-flow-set/work-flow/work-flow-step", "addWorkFlowStepConfig");
        
        digester.addSetNext("configuration/work-flow-set/work-flow", "addWorkFlowConfig");
 
        digester.addSetProperties("configuration/aa-manager", "class", "aaManagerClassName");
        digester.addSetProperties("configuration/aa-manager", "noAuthenticationView", "noAuthenticationView");
        digester.addSetProperties("configuration/aa-manager", "noAuthorizationView", "noAuthorizationView");
       
        File configurationFile = new File(filePath);
        try {
            Configuration configuration = (Configuration) digester.parse(configurationFile);
            return configuration;
        } catch(Throwable ex) {
            throw new ConfigurationDigesterException(ex);
        }
    }
}
