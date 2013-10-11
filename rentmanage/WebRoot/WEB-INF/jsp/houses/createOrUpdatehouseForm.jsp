<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="houseclinic" tagdir="/WEB-INF/tags" %>

<html>

<jsp:include page="../fragments/headTag.jsp"/>
<body>

<script>
    $(function () {
        $("#birthDate").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${house['new']}">
            <c:set var="method" value="post"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
        </c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${house['new']}">New </c:if>
        house
    </h2>

    <form:form modelAttribute="house" method="${method}"
               class="form-horizontal">
        <div class="control-group" id="owner">
            <label class="control-label">Owner </label>
            <c:out value="${house.owner.firstName} ${house.owner.lastName}"/>
        </div>
        <houseclinic:inputField label="Name" name="name"/>
        <houseclinic:inputField label="CreateDate" name="createdate"/>
        <houseclinic:inputField label="Address" name="address"/>
        <houseclinic:inputField label="Price" name="price"/>

        
        <div class="form-actions">
            <c:choose>
                <c:when test="${house['new']}">
                    <button type="submit">Add house</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update house</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
    <c:if test="${!house['new']}">
    </c:if>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
