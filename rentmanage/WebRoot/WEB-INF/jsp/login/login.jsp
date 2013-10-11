<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>用户登录</TITLE>
  <spring:url value="images/Default.css" var="jQueryUiCss"/>
   <link href="${jQueryUiCss}" rel="stylesheet"></link>
   <spring:url value="images/xtree.css" var="xtree"/>
   <link href="${xtree}" rel="stylesheet"></link>
   <spring:url value="images/User_Login.css" var="User_Login"/>
   <link href="${User_Login}" rel="stylesheet"></link>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8"%> 
<META content="MSHTML 6.00.6000.16674" name=GENERATOR></HEAD>
<BODY id=userlogin_body>
<DIV></DIV>

<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id=user_main>
  <form name="input" action="/rent/login" method="post">
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=TxtUserName 
      maxLength=20 name=loginName> </LI></UL>
    <UL>
      <LI class=user_main_text>密 码： </LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=TxtPassword 
      type=password name=password> </LI></UL>
   </DIV></LI>
    <LI class=user_main_r><INPUT class=IbtnEnterCssClass id=IbtnEnter 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("IbtnEnter", "", true, "", "", false, false))' 
    type=image src="images/user_botton.gif" name=IbtnEnter> </LI></UL></form>
</DL>
</DIV>
<SPAN id=ValrUserName 
style="DISPLAY: none; COLOR: red"></SPAN>
<SPAN id=ValrPassword 
style="DISPLAY: none; COLOR: red"></SPAN>
<SPAN id=ValrValidateCode 
style="DISPLAY: none; COLOR: red"></SPAN>
<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

<DIV></DIV>

</BODY></HTML>
