<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : egovSampleRegister.jsp
	 * @Description : Sample Register 화면
	 * @Modification Information
	 * 
	 *   수정일         수정자                   수정내용
	 *  -------    --------    ---------------------------
	 *  2009.02.01            최초 생성
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
<c:set var="registerFlag" value="${empty sampleVO.id ? '등록' : '수정'}" />
<c:set var="userId" value="${sampleVO.regUser}" />
<c:set var="idFlag"
	value="${userId==sessionScope.UserAccount.regUser ? 'true' : 'false'}" />
<title>Sample <c:out value="${registerFlag}" />
</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/sample.css'/>" />

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="sampleVO" staticJavascript="false"
	xhtml="true" cdata="false" />

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/sample/egovSampleList.do'/>";
   	document.detailForm.submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.detailForm.action = "<c:url value='/sample/deleteSample.do'/>";
   	document.detailForm.submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	frm = document.detailForm;
	if(!validateSampleVO(frm)){
        return;
    }else{
    	frm.action = "<c:url value="${registerFlag == '등록' ? '/sample/addSample.do' : '/sample/updateSample.do'}"/>";
        frm.submit();
    }
}

-->
</script>
</head>
<body
	style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">

	<form:form commandName="sampleVO" name="detailForm">
		<div id="content_pop">
			<!-- 타이틀 -->
			<div id="title">
				<ul>
					<li><img
						src="<c:url value='/images/egovframework/rte/title_dot.gif'/>">
						글<c:out value="${registerFlag}" /></li>
				</ul>
			</div>
			<!-- // 타이틀 -->
			<div id="table">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#D3E2EC" bordercolordark="#FFFFFF"
					style="BORDER-TOP: #C2D0DB 2px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-RIGHT: #ffffff 1px solid; BORDER-BOTTOM: #C2D0DB 1px solid; border-collapse: collapse;">
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>

					<!-- 수정 PART -->
					<c:if test="${registerFlag == '수정'}">
						<tr>
							<td class="tbtd_caption">글번호</td>
							<td class="tbtd_content"><form:input path="id"
									cssClass="essentiality" maxlength="10" readonly="true" /></td>
						</tr>
						<!-- 같은 id라면 수정가능 -->
						<c:if test="${idFlag=='true'}">
							<tr>
								<td class="tbtd_caption">제목</td>
								<td class="tbtd_content"><form:input path="name"
										maxlength="30" cssClass="txt" /> &nbsp;<form:errors
										path="name" /></td>
							</tr>
							<tr>
								<td class="tbtd_caption">내용</td>
								<td class="tbtd_content"><form:textarea path="description"
										rows="5" cols="58" /> &nbsp;<form:errors path="description" /></td>
							</tr>

						</c:if>

						<!-- 다른 id라면 읽기전용 -->
						<c:if test="${idFlag=='false'}">
							<tr>
								<td class="tbtd_caption">제목</td>
								<td class="tbtd_content"><form:input path="name"
										maxlength="30" cssClass="txt" readonly="true" /> &nbsp;<form:errors
										path="name" /></td>
							</tr>
							<tr>
								<td class="tbtd_caption">내용</td>
								<td class="tbtd_content"><form:textarea path="description"
										rows="5" cols="58" readonly="true" /> &nbsp;<form:errors
										path="description" /></td>
							</tr>

						</c:if>
						<tr>
							<td class="tbtd_caption">등록자</td>
							<td class="tbtd_content"><form:input path="regUser"
									cssClass="essentiality" maxlength="10" readonly="true" />
								&nbsp;<form:errors path="regUser" /></td>
							</td>
						</tr>
				</table>
				</c:if>

				<!-- 등록 PART -->
				<c:if test="${registerFlag == '등록'}">
					<tr>
						<td class="tbtd_caption">제목</td>
						<td class="tbtd_content"><form:input path="name"
								maxlength="30" cssClass="txt" /> &nbsp;<form:errors path="name" /></td>
					</tr>
					<tr>
						<td class="tbtd_caption">내용</td>
						<td class="tbtd_content"><form:textarea path="description"
								rows="5" cols="58" /> &nbsp;<form:errors path="description" /></td>
					</tr>
					<tr>
						<td class="tbtd_caption">등록자</td>
						<td class="tbtd_content"><form:input path="regUser"
								maxlength="10" cssClass="txt" readonly="true"
								value="${sessionScope.UserAccount.regUser}" /> &nbsp;<form:errors
								path="regUser" /></td>

					</tr>
					</table>
				</c:if>

			</div>
			<div id="sysbtn">
				<ul>
					<li><span class="btn_blue_l"><a
							href="javascript:fn_egov_selectList();">게시판으로</a><img
							src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
							style="margin-left: 6px;"></span></li>
					<c:if test="${registerFlag == '수정'}">
						<!-- 같은 id라면 수정 / 삭제가능 -->
						<c:if test="${idFlag=='true'}">
							<li><span class="btn_blue_l"><a
									href="javascript:fn_egov_save();"><c:out
											value='${registerFlag}' /></a><img
									src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
									style="margin-left: 6px;"></span></li>
							<li><span class="btn_blue_l"><a
									href="javascript:fn_egov_delete();">삭제</a><img
									src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
									style="margin-left: 6px;"></span></li>
							<li><span class="btn_blue_l"><a
									href="javascript:document.detailForm.reset();">원래대로</a><img
									src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
									style="margin-left: 6px;"></span></li>
						</c:if>
					</c:if>
					<c:if test="${registerFlag == '등록'}">
						<!-- 같은 id라면 수정 / 삭제가능 -->

						<li><span class="btn_blue_l"><a
								href="javascript:fn_egov_save();"><c:out
										value='${registerFlag}' /></a><img
								src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
								style="margin-left: 6px;"></span></li>
						<li><span class="btn_blue_l"><a
								href="javascript:document.detailForm.reset();">원래대로</a><img
								src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>"
								style="margin-left: 6px;"></span></li>

					</c:if>

				</ul>
			</div>
		</div>
		<!--글 수정/삭제 후 검색조건 유지 -->
		<input type="hidden" name="searchCondition"
			value="<c:out value='${searchVO.searchCondition}'/>" />
		<input type="hidden" name="searchKeyword"
			value="<c:out value='${searchVO.searchKeyword}'/>" />
		<input type="hidden" name="pageIndex"
			value="<c:out value='${searchVO.pageIndex}'/>" />
	</form:form>
</body>
</html>

