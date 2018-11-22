package com.javacan.mvc.configuration;

import java.util.StringTokenizer;

public class WorkFlowStepConfig {
    private String uri;
    private int step;
    private int[] fromStep;
    
    public String getUri() {
        return uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public void setStep(int step) {
        this.step = step;
    }
    
    public int getStep() {
        return step;
    }
    
    public void setFrom(String from) {
        StringTokenizer st = new StringTokenizer(from, ",");
        fromStep = new int[st.countTokens()];
        for (int i = 0 ; i < fromStep.length ; i++) {
            try {
                fromStep[i] = Integer.parseInt( (String)st.nextToken());
            } catch(Exception ex) {
                fromStep[i] = -1;
            }
        }
    }
    
    public boolean validFromStep(int requestFromStep) {
        if (fromStep == null) {
            return false;
        }
        for (int i = 0 ; i < fromStep.length ; i++) {
            if (fromStep[i] == requestFromStep) {
                return true;
            }
        }
        return false;
    }
}
