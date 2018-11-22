package com.javacan.mvc.configuration;

import com.javacan.mvc.command.Command;
import java.util.List;
import java.util.Map;

/**
 * ���� ������ �����ϰ� �ִ� ��ü
 * @author �ֹ���
 */
public class Configuration implements java.io.Serializable {
    /**
     * <templateName, templateInfo>: ���ø� ���� ����
     */
    private Map templateMap = new java.util.HashMap();
    
    /**
     * <name, ViewInfoConfig>: gloval-view�� ���ԵǾ� �ִ� �۷ι� �並 �����ϰ� �ִ�.
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
     * AAManager�� ���� Ŭ���� �̸�
     */
    private String aaManagerClassName;
    /**
     * AAManager�� ������ �ʿ��� �������� ������ ��
     * ������ ���� ���� ��� ������ ���� �̸�
     */
    private String noAuthenticationView;
    /**
     * ������� ���ҷδ� ������ �� ���� ����(URI)�� ��û�� ��
     * ������ ���� �̸�
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
