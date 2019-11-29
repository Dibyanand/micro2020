<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>
<title>Spring Trader - Portfolio</title>
    	<jsp:include page="fragments/header.jsp" />

</head>
<body>

<jsp:include page="fragments/navbar_fragment.jsp" />

	<div class="container well well-lg">
	
	 <security:authorize access="hasAnyAuthority('USER')">  
        	 <div  class="col-md-6">
        	 	<c:if test="${portfolio != null}">
        	 		<jsp:include page="fragments/portfoliosummary.jsp"></jsp:include>
        	 	</c:if>
        	 
        	 	<c:if test="${portfolioRetrievalError != null}">
        	 		<jsp:include page="fragments/portfolioerror.jsp"></jsp:include>
        	 	
        	 	</c:if>
	           </div>
	           
	           <div class="col-md-6" >
				<jsp:include page="fragments/marketsummary.jsp"></jsp:include>
			</div>
	          

			<div class="row">
				<c:if test="${portfolio != null}">
					<jsp:include page="fragments/portfoliolisting.jsp"></jsp:include>
				</c:if>
				
			</div>
			<div class="row">
			<c:if test="${portfolio != null}">
					<jsp:include page="fragments/portfolioorderlisting.jsp"></jsp:include>
				</c:if>
				
			</div>
	            
	       
        	
        </security:authorize> 
	
	</div>


</body>
</html>