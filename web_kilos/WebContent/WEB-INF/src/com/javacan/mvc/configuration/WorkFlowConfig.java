package com.javacan.mvc.configuration;

import java.util.List;
import java.util.Map;

/**
 * ���� ������ <work-flow>�� ������ �����ϴ� ��ü
 * @author �ֹ���
 */
public class WorkFlowConfig {
    
    private String name;
    private String errorView;
    private List stepList = new java.util.ArrayList();
    private Map stepMap = new java.util.HashMap();
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }
    public String getErrorView() {
        return errorView;
    }
    
    public void addWorkFlowStepConfig(WorkFlowStepConfig step) {
        stepList.add(step);
        stepMap.put(step.getUri(), step);
        step.setStep(stepList.size());
    }
    
    public List getWorkFlowStepConfigList() {
        return stepList;
    }
    
    public WorkFlowStepConfig getWorkFlowStepConfig(int step) {
        return (WorkFlowStepConfig)stepList.get(step);
    }
    
    public WorkFlowStepConfig getWorkFlowStepConfig(String uri) {
        if (uri == null) return null;
        return (WorkFlowStepConfig)stepMap.get(uri);
    }
    
    public int getLastWorkFlowStepNumber() {
        return stepList.size();
    }
}
