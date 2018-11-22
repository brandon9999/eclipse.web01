package com.javacan.mvc.configuration;

import java.util.Map;

/**
 * Command 객체에 전달되는 URI 맵 정보
 * @author 최범균
 */
public class ViewInfoMap {
    
    private Map globalViewMap;
    private Map commandViewMap;
    
    public ViewInfoMap(Map globalViewMap, Map commandViewMap) {
        this.globalViewMap = globalViewMap;
        this.commandViewMap = commandViewMap;
    }
    
    /**
     * 이름이 viewName인 ViewInfoConfig를 리턴한다.
     * command-view에 포함된 ViewInfoConfig 목록을 먼저 찾고
     * 존재하지 않을 경우 global-view와 관련된 ViewInfoConfig 중에서 찾는다.
     */
    public ViewInfoConfig getViewInfoConfig(String viewName) {
        ViewInfoConfig view = (ViewInfoConfig)commandViewMap.get(viewName);
        if (view == null) {
            view = (ViewInfoConfig)globalViewMap.get(viewName);
        }
        return view;
    }
}