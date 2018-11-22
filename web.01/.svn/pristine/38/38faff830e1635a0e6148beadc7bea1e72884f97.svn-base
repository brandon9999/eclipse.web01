<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!--
   jsp:forward page=”url”
     이 액션을 만나면 현재 jsp 파일 처리를 그만 두고, 지금까지 열심히 쓰고 있던 응답 버퍼를 싹 지우고
  request를 타겟 리소스(“url”)로 보냅니다.
   타겟 리소스를 부른 jsp 파일은  jsp:forward 액션 전에 화면에 아무것도 쓰지 않습니다. 
    만약 forward를 만나기 전에 응답에 쓰여진 것이 있으면, forward가 일어난 다음에 모두 사라지게 되죠.
      그래서, 만약 forward 전에 응답을 보냈다면(out.flush()를 호출했다면) 클라이언트에서는 out.flush() 
      당시에 응답 버퍼에 있던 내용만 볼 수 있게 됩니다. 그 이후에 forward 되거나 출력 버퍼에 쓰인 내용은 볼 수 없게 됩니다.
-->

<jsp:forward page="forward_result.jsp" > 
   <jsp:param name="param1" value="value1" /> 
   <jsp:param name="param2" value="value2" /> 
</jsp:forward>

</body>
</html>