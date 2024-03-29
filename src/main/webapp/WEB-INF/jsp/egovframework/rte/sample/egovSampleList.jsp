<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *  2013.03.30  김경진       검색조건, 레이아웃 변경, 버튼추가
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *  
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>게시판</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
<script type="text/javaScript" language="javascript" defer="defer">

/* 글 수정 화면 function */
function fn_egov_select(id) {
	document.listForm.selectedId.value = id;
   	document.listForm.action = "<c:url value='/sample/updateSampleView.do'/>";
   	document.listForm.submit();		
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/sample/addSampleView.do'/>";
   	document.listForm.submit();		
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/sample/egovSampleList.do'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sample/egovSampleList.do'/>";
   	document.listForm.submit();
}
/* 로그아웃 function */
function fn_egov_logout(){
	document.listForm.action = "<c:url value='/sample/logout.do'/>";
   	document.listForm.submit();
}

</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="searchVO" name="listForm" method="post">
<!-- commandName은 vo객체를 말한다. form tag를 사용한 부분과의 매핑을 위해 필요한 옵션.
     vo의 변수와 form에서 같은 name을 서로 매핑시켜 getter와 setter를 사용한다. -->
<input type="hidden" name="selectedId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li> 게시판 </li>
		</ul>		
	</div>
	<!-- // 타이틀 -->
	<div id="search">
	<ul>		
		<li><span class="btn_blue_l"><a href="javascript:fn_egov_logout();">로그아웃</a>
		<img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;">
		</span></li></ul>
		<ul>
		<li>
			<form:select path="searchCondition" cssClass="use">
				<form:option value="1" label="제목" />
				<form:option value="0" label="등록자" />
			</form:select>
		</li>
		<li><form:input path="searchKeyword" cssClass="txt"/></li>
		<li><span class="btn_blue_l"><a href="javascript:fn_egov_selectList();"><spring:message code="button.search" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;"></span></li></ul>		
	</div>
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>				
				<col width="100">
				<col width="150">
				<col width="80">
				<col width="100">
			</colgroup>		  
			<tr>
				<th align="center">글번호</th>
				<th align="center">제목</th>
				<th align="center">등록자</th>
				<th align="center">작성일</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${result.id}"/></a></td>
				<td align="left" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.id}"/>')"><c:out value="${result.name}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.regUser}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.date}"/>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
				   type="image"
				   jsFunction="fn_egov_link_page"
				   />
		<form:hidden path="pageIndex" />
	</div>
	<div id="sysbtn1">
		<ul>
		<div id="sysbtn"><ul>
		<li><span class="btn_blue_l"><a href="javascript:fn_egov_addView();">글등록</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;"></span></li></ul>
		</div>
		</ul>
	</div>
	<div id="sysbtn2">
		<ul>
		<div id="list"><ul>
		<li><span class="btn_blue_l"><a href="/test/sample/egovSampleList.do">게시판으로</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;"></span></li></ul>
		</div>
		</ul>
	</div>
</div>
</form:form>
</body>
</html>
