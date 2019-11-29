<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>
<title>Spring Trader</title>
<jsp:include page="fragments/header.jsp" />
    
    
</head>
<body>

 <security:authorize access="hasAnyAuthority('USER')">  
 	<div >
	<jsp:include page="fragments/navbar_fragment.jsp" />
	
		
		<div class="container well well-lg">
			<jsp:include page="fragments/savedorder.jsp"/>
			<jsp:include page="fragments/companyinput.jsp"/>
			<jsp:include page="fragments/quotelist.jsp" />
			
			<c:if test="${portfolio != null}">
					<jsp:include page="fragments/portfoliolisting.jsp"></jsp:include>
				</c:if>
			
			<c:if test="${portfolioRetrievalError != null}">
        	 		<jsp:include page="fragments/portfolioerror.jsp"></jsp:include>
        	 	
        	 	</c:if>
			
		</div>
	</div>
 </security:authorize>
	
</body>
</html>
