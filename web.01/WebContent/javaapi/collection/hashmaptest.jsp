<%@ page contentType="text/html;charset=EUC-KR"  %>
<%@ page import="java.util.*" %>

<%

        // For java1.4
        //HashMap hm = new HashMap();

        // For java1.5 ~
        HashMap<String, String> hm = new HashMap<String, String>();
        //hm.put("aaa", "111");
        //hm.put("bbb", "222");
        //hm.put("ccc", "333");
        //hm.put("ddd", "444");    
        //hm.put("eee", "555");
        
        for(int k = 0; k < 100; k++)
        {
        	hm.put(Integer.toString(k), Integer.toString(k));
        }
        
        System.out.println("HashMap size : " + hm.size());
        out.println("HashMap size : " + hm.size() + "<br>");        
        
        Set set = hm.keySet();
        Object []hmKeys = set.toArray();
        for(int i = 0; i < hmKeys.length; i++)
        {
            String key = (String)hmKeys[i];   
            System.out.print(key + " / " + (String)hm.get(key));
            out.print(key + " / " + (String)hm.get(key) + "<br>");
        
        }
        
        System.out.print("TEST END");
        out.print("TEST <br>");
%>
