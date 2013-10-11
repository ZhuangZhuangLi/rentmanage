<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/jsp/error.jsp"%>


<spring:url value="/resources/images/banner-graphic.png" var="banner"/>
<img src="${banner}"/>

<%
	String type=(String)request.getSession().getAttribute("loginType");
	String id= (String)request.getSession().getAttribute("loginId");
%>

<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
           
            <% if("1".equals(type)){
            %>
             <li style="width: 100px;"><a href="<spring:url value="/owners" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
             <li style="width: 130px;"><a href="<spring:url value="/owners/find.html" htmlEscape="true" />"><i
                    class="icon-search"></i> Find owners</a></li>
            <%} %>	
            
            <% if("2".equals(type)){
            %>
             <li style="width: 100px;"><a href="/rent/owners/<%=id %>"><i class="icon-home"></i>
                Home</a></li>
             <li style="width: 130px;"><a href="<spring:url value="/houses/find.html" htmlEscape="true" />"><i
                    class="icon-search"></i> Find house</a></li>
            <%} %>	
            <li style="width: 140px;"><i
                    class="icon-th-list"></i>Hello,<%= request.getSession().getAttribute("loginUser")%></li>
            <li style="width: 90px;"><a href="<spring:url value="/logout.html" htmlEscape="true" />"
                                        title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-warning-sign"></i>Logout</a></li>
            <li style="width: 80px;"><a href="#" title="not available yet. Work in progress!!"><i
                    class=" icon-question-sign"></i>Help</a></li>
        </ul>
    </div>
</div>
	
