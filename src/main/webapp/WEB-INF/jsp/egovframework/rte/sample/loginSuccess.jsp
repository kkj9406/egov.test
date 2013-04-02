<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공</title>
<link type="text/css" rel="stylesheet" href="css/egovframework/sample.css" />
</head>
<body>
${sessionScope.UserAccount.regUser} 님은 로그인에 성공했습니다.
<br/>
<a href="/test/sample/egovSampleList.do">게시판으로 가기</a>
</body>
</html>