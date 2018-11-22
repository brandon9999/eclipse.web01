package com.javacan.mvc;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;
import java.util.List;

import com.javacan.mvc.command.Command;
import com.javacan.mvc.command.CommandFactory;
import com.javacan.mvc.configuration.Configuration;
import com.javacan.mvc.configuration.ConfigurationDigester;
import com.javacan.mvc.configuration.ConfigurationDigesterException;
import com.javacan.mvc.configuration.ViewInfoConfig;
import com.javacan.mvc.configuration.ViewInfoMap;
import com.javacan.mvc.configuration.TemplateInfoConfig;
import com.javacan.mvc.configuration.TemplateMapConfig;
import com.javacan.mvc.configuration.WorkFlowConfig;
import com.javacan.mvc.configuration.WorkFlowStepConfig;
import com.javacan.mvc.ana.AAManagerFactory;
import com.javacan.mvc.ana.AAManager;
import com.javacan.mvc.ana.AAManagerException;
import com.javacan.mvc.ana.Service;
import com.javacan.mvc.ana.Role;

import com.javacan.mvc.template.TemplateConstant;

/**
 * 클라이언트의 요청 URI에 따라서 알맞은 처리를 해주는 컨트롤러.
 *
 * @author 최범균
 */
public class ControllerServlet extends HttpServlet 
{
    
    private Configuration configuration;
    
    public void init() throws ServletException 
    {
        String configFilePath = getInitParameter("configFile");
        
        //System.out.println("**********************************v**********************");            
        //System.out.println("[TmaxDebug]");
        //System.out.println("[ControllerServlet/init]");
        //System.out.println("[VAR/configFilePath] : " + configFilePath);        
        //System.out.println("********************************************************");
        
        if (configFilePath == null || configFilePath.equals("")) 
        {
            // 기본 설정 파일(WEB-INF/javacan-mvc.xml) 사용
            configFilePath = getServletContext().getRealPath("/WEB-INF/javacan-mvc.xml");
            
            //System.out.println("**********************************v**********************");            
            //System.out.println("[TmaxDebug]");
            //System.out.println("[ControllerServlet/init]");
            //System.out.println("[VAR/configFilePath] : " + configFilePath);        
            //System.out.println("********************************************************");
        }
        System.setProperty(ConfigurationDigester.SYSTEM_PROPERTY_NAME, configFilePath);

        //System.out.println("********************************************************");            
        //System.out.println("[TmaxDebug]");
        //System.out.println("[ControllerServlet/init]");
        //System.out.println("********************************************************");
        
        try 
        {
            configuration = ConfigurationDigester.digest();
            CommandFactory factory = CommandFactory.getInstance();
            factory.setConfiguration(configuration);
            AAManagerFactory.setConfiguration(configuration);
        } 
        catch(ConfigurationDigesterException ex) 
        {
            throw new ServletException(ex.getMessage(), ex);
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException 
    {
        //System.out.println("********************************************************");            
        //System.out.println("[TmaxDebug]");
        //System.out.println("[ControllerServlet/doGet]");
        //System.out.println("********************************************************");
    	
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException 
    {
        //System.out.println("********************************************************");            
        //System.out.println("[TmaxDebug]");
        //System.out.println("[ControllerServlet/doPost]");
        //System.out.println("********************************************************");
    	
        processRequest(request, response);
    }
    
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException 
    {
       //System.out.println("********************************************************");            
       //System.out.println("[TmaxDebug]");
       //System.out.println("[ControllerServlet/processRequest]");
    	
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String contextURI = null;

        // Start Test Code 
        //RequestDispatcher dispatcherTest = request.getRequestDispatcher("test2.jsp");
        //dispatcherTest.forward(request, response);        
        // End Test Code              
        
        
        // requestURI에서 콘텍스트 부분을 제거한다.
        if (contextPath.compareTo("") != 0) 
        {
            contextURI = requestURI.substring(contextPath.length());
        } 
        else 
        {
            contextURI = requestURI;
        }
        
        ViewInfoConfig viewInfo = null;
        boolean granted = true; // 요청 Service를 사용자 Role이 사용할 수 있는지의 여부를 저장한다.
        
        try 
        {
            AAManager aaManager = AAManagerFactory.createAAManager();
            Service service = aaManager.getServiceByUri(contextURI);
            if (service != null) 
            {
                if (service.getGrantedType() != Service.GRANTED_ALL) 
                {
                    // 서비스가 권한 체크 서비스로 지정된 경우
                    Role userRole = aaManager.getUserRoleFromSession(request);
                    if (userRole == null) 
                    {
                        // 인증 절차가 필요한 URI(Service)를 요청했는데,
                        // 인증 절차를 거치지 않았으므로, 그와 관련된 뷰를 보여준다.,
                        // 관련 뷰가 없을 경우 예외를 발생한다.
                        viewInfo = (ViewInfoConfig)configuration.getGlobalViewMap()
                                      .get(configuration.getNoAuthenticationView());
                        if (viewInfo == null) throw new ServletException("No Authentication");
                        granted = false;
                    } 
                    else 
                    {
                        if (service.getGrantedType() == Service.GRANTED_LIMITED_ROLE) 
                        {
                            if (!aaManager.hasAuthority(userRole, service)) 
                            {
                                // 사용자의 Role이 이 Service를 사용할 권한이 없다.
                                viewInfo = (ViewInfoConfig)configuration.getGlobalViewMap()
                                              .get(configuration.getNoAuthorizationView());
                                if (viewInfo == null) throw new ServletException("Not Granted");
                                granted = false;
                            }
                        }
                    }
                }
            }
        } 
        catch(AAManagerException ex) 
        {
            // 예외가 발생할 경우 인증/권한 체크를 하지 않는다.
            // 예외가 발생한 이유는 설정 파일에서 AAManager의 클래스 이름을
            // 잘못 입력했기 때문이다.
        }
        
        if (granted) 
        {
            WorkFlowConfig workFlow = configuration.getWorkFlowConfig(contextURI);
            if (workFlow != null) 
            {
                // 업무 흐름이 지정되어 있는 경우
                // workFlowConfig로부터 contextURI에 해당하는 스텝정보를 읽어온다. 
                WorkFlowStepConfig workFlowStep = workFlow.getWorkFlowStepConfig(contextURI);
                boolean requestInvalidStep = false;
                // fromURI 파라미터로부터 이전 스텝 정보를 읽어온다.
                String fromURI = request.getParameter("fromURI");
                if (fromURI == null) 
                {
                    // 이전 스텝 정보가 존재하지 않는 경우 현재 요청한 스텝이 1인지 검사
                    if (workFlowStep.getStep() != 1) 
                    {
                        requestInvalidStep = true;
                    }
                } 
                else 
                {
                    WorkFlowStepConfig fromWorkFlowStep = workFlow.getWorkFlowStepConfig(fromURI);
                    
                    int fromStep = fromWorkFlowStep.getStep();
                    if (!workFlowStep.validFromStep(fromStep)) 
                    {
                        requestInvalidStep = true;
                    }
                }
                if (requestInvalidStep) 
                {
                    if (workFlow.getErrorView() != null) 
                    {
                        viewInfo = (ViewInfoConfig)configuration.getGlobalViewMap().get(workFlow.getErrorView());
                    }
                    if (viewInfo == null) 
                    {
                        throw new ServletException("Invalid Work Flow:"+fromURI+"->"+contextURI);
                    }
                }
            }
        }
        
        if (viewInfo == null) 
        { // 워크 플로우에서 잘못된 요청이 아닌 경우
            Command command = null;
            CommandFactory commandFactory = CommandFactory.getInstance();
            command = commandFactory.createCommand(contextURI);
            ViewInfoMap viewInfoMap = new ViewInfoMap(
                    configuration.getGlobalViewMap(), 
                    configuration.getCommandConfigByUri(contextURI).getCommandViewMap() );
            
            viewInfo = command.execute(viewInfoMap, request, response);
        }
        
        if (viewInfo.isRedirect()) 
        {
            response.sendRedirect(response.encodeRedirectURL(viewInfo.getUri()));
        } 
        else 
        {
            String viewPage = null;
            if (viewInfo.getUseTemplate() != null) 
            {
                TemplateInfoConfig templateInfo = configuration.getTemplateInfoConfig(viewInfo.getUseTemplate());
                if (templateInfo != null) 
                {
                    viewPage = templateInfo.getUri();
                    
                    List mapList = templateInfo.getTemplateMapConfigList();
                    
                    Map map = new java.util.HashMap();
                    if (mapList != null && mapList.size() > 0) {
                        for (int i = 0 ; i < mapList.size() ; i++) {
                            TemplateMapConfig mapConfig = (TemplateMapConfig)mapList.get(i);
                            map.put(mapConfig.getName(), mapConfig.getUri());
                        }
                    }
                    map.put(TemplateConstant.CONTENT_PART_NAME, viewInfo.getUri());
                    
                    request.setAttribute(TemplateConstant.REQUEST_ATTRIBUTE_NAME, map);
                }
            }
            
            if (viewPage == null) viewPage = viewInfo.getUri();
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }
}
