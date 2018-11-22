package com.javacan.mvc.configuration;

import com.javacan.mvc.command.Command;
import java.util.List;
import java.util.Map;

/**
 * 설정 정보를 저장하고 있는 객체
 * @author 최범균
 */
public class Configuration implements java.io.Serializable {
    /**
     * <templateName, templateInfo>: 템플릿 정보 저장
     */
    private Map templateMap = new java.util.HashMap();
    
    /**
     * <name, ViewInfoConfig>: gloval-view에 포함되어 있는 글로벌 뷰를 저장하고 있다.
     */
    private Map globalViewMap = new java.util.HashMap();
    
    /**
     * <name, CommandMapping>
     */
    private Map commandMapping = new java.util.HashMap();
    /**
     * <uri, MappingConfig>
     */
    private Map uriMapping = new java.util.HashMap();
    /**
     * <uri, WorkFlowConfig>
     */
    private Map workFlowMapByUri = new java.util.HashMap();

    /**
     * AAManager로 사용될 클래스 이름
     */
    private String aaManagerClassName;
    /**
     * AAManager의 권한이 필요한 페이지에 접근할 때
     * 인증을 받지 않은 경우 보여줄 뷰의 이름
     */
    private String noAuthenticationView;
    /**
     * 사용자의 역할로는 접근할 수 없는 서비스(URI)를 요청할 때
     * 보여줄 뷰의 이름
     */
    private String noAuthorizationView;
    
    public Configuration() {
    }
    
    public void addTemplateInfoConfig(TemplateInfoConfig templateInfo) {
        templateMap.put(templateInfo.getName(), templateInfo);
    }
    
    public TemplateInfoConfig getTemplateInfoConfig(String templateName) {
        return (TemplateInfoConfig)templateMap.get(templateName);
    }
    
    public void addViewInfoConfig(ViewInfoConfig viewInfo) {
        globalViewMap.put(viewInfo.getName(), viewInfo);
    }
    
    public Map getGlobalViewMap() {
        return globalViewMap;
    }
    
    public void addCommandConfig(CommandConfig commandConf) {
        commandMapping.put(commandConf.getName(), commandConf);
    }
    
    public void addMappingConfig(MappingConfig mappingConfig) {
        uriMapping.put(mappingConfig.getUri(), mappingConfig);
    }
    
    public MappingConfig getMappingConfig(String uri) {
        return (MappingConfig)uriMapping.get(uri);
    }
    public CommandConfig getCommandConfig(String name) {
        return (CommandConfig)commandMapping.get(name);
    }
    public CommandConfig getCommandConfigByUri(String uri) {
        MappingConfig mapping = getMappingConfig(uri);
        
        if (mapping == null) {
            return null;
        }
        return getCommandConfig(mapping.getName());
    }
    public Command createCommand(String uri) {
        CommandConfig commandConfig = getCommandConfigByUri(uri);
        if (commandConfig == null) {
            return null;
        }
        String className = commandConfig.getType();
        try {
            Class commandClass = Class.forName(className);
            return (Command)commandClass.newInstance();
        } catch(ClassNotFoundException ex) {
            return null;
        } catch(InstantiationException ex) {
            return null;
        } catch(IllegalAccessException ex) {
            return null;
        }
    }
    
    public void addWorkFlowConfig(WorkFlowConfig workFlowConfig) {
        List list = workFlowConfig.getWorkFlowStepConfigList();
        if (list != null) {
            for (int i = 0 ; i < list.size() ; i++) {
                WorkFlowStepConfig workFlowStep = (WorkFlowStepConfig)list.get(i);
                workFlowMapByUri.put(workFlowStep.getUri(), workFlowConfig);
            }
        }
    }
    
    public WorkFlowConfig getWorkFlowConfig(String uri) {
        return (WorkFlowConfig)workFlowMapByUri.get(uri);
    }
    
    public void setAaManagerClassName(String className) {
        this.aaManagerClassName = className;
    }
    
    public String getAaManagerClassName() {
        return aaManagerClassName;
    }
    
    public void setNoAuthenticationView(String noAuthenticationView) {
        this.noAuthenticationView = noAuthenticationView;
    }
    
    public String getNoAuthenticationView() {
        return noAuthenticationView;
    }
    
    public void setNoAuthorizationView(String noAuthorizationView) {
        this.noAuthorizationView = noAuthorizationView;
    }
    
    public String getNoAuthorizationView() {
        return noAuthorizationView;
    }

}
