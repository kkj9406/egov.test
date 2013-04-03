<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><spring:message code="sample.loginform.title"/></title>
<link type="text/css" rel="stylesheet" href="css/egovframework/sample.css" />
</head>
<body>
로그인 페이지
<br/>
<br/>
<br/>
<br/>
<form:form commandName="account" action="/test/sample/loginProcess.do">
<!-- form 속성 설명 -->
<!-- commandName은 vo객체를 말한다. form tag를 사용한 부분과의 매핑을 위해 필요한 옵션. -->
<!-- vo에서 regUser라는 변수가 있다면 form tag안에서 regUser에 입력을 받는경우
알아서 setter를 사용하고 수정시 또한 알아서 getter를 사용한다. -->
<!-- action은 form에서 submit이 일어날 경우 이동할 경로이다. -->
아이디 : <input type=text name="regUser"> 패스워드: <input type=password name="password"> <input type=submit value="로그인">
</form:form>
</body>
</html>