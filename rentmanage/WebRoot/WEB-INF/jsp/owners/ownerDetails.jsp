<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page pageEncoding="UTF-8"%> 
  

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Owner Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>LoginName</th>
            <td><b><c:out value="${owner.loginname}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
         <tr>
            <td> 
            	<spring:url value="/owners/{ownerId}/edit.html" var="editUrl">
                    <spring:param name="ownerId" value="${owner.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Owner</a></td>
            <td>
            	<spring:url value="/owners/{ownerId}/houses/new.html" var="addUrl">
                    <spring:param name="ownerId" value="${owner.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New house</a></td>
        </tr>
    </table>

    <h2>Houses Info</h2>

    <c:forEach var="house" items="${owner.houses}">
          <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${house.name}"/></b></td>
        </tr>
        <tr>
            <th>CreateDate</th>
            <td><joda:format value="${house.createdate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><c:out value="${house.price}"/></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${house.address}"/></td>
        </tr>
        <tr>
              <td> 
               <spring:url value="/owners/{ownerId}/houses/{houseId}/edit" var="houseUrl">
			       <spring:param name="ownerId" value="${owner.id}"/>
			       <spring:param name="houseId" value="${house.id}"/>
			    </spring:url>
			   <a href="${fn:escapeXml(houseUrl)}" class="btn btn-info">Edit house</a>             	
			  </td>
			  <td> 
               <spring:url value="/owners/{ownerId}/houses/{houseId}/delete" var="houseUrl">
			       <spring:param name="ownerId" value="${owner.id}"/>
			       <spring:param name="houseId" value="${house.id}"/>
			    </spring:url>
			   <a href="${fn:escapeXml(houseUrl)}" class="btn btn-info">Remove house</a>             	
			  </td>
        </tr>
        </table>
    </c:forEach>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
