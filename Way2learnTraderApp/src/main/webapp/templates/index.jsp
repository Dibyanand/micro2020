<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
<html xmlns="http://www.w3.org/1999/xhtml" >

<head>
	<meta name="viewport" content="width=device-width,initial-scale=1"/>
	<title>Spring Trader</title>

  
    	<jsp:include page="fragments/header.jsp" />
    
    
</head>
<body>
     
 
     
     <jsp:include page="fragments/navbar_fragment.jsp"></jsp:include>
     
     <div class="container well well-lg">
     	 <security:authorize access="!isAuthenticated()">  
        <div class="col-md-6" >
        	<jsp:include page="fragments/login.jsp" />
           
        </div>
        
        </security:authorize>
        
        <security:authorize access="hasAnyAuthority('USER')">  
        	 <div  class="col-md-6">
        	 	<c:if test="${portfolio != null}">
        	 		<jsp:include page="fragments/portfoliosummary.jsp"></jsp:include>
        	 	</c:if>
        	 
        	 	<c:if test="${portfolioRetrievalError != null}">
        	 		<jsp:include page="fragments/portfolioerror.jsp"></jsp:include>
        	 	
        	 	</c:if>
	           
	            <jsp:include page="fragments/accountuserstats.jsp"></jsp:include>
	            
	        </div>
        	
        </security:authorize> 
       
		<div class="col-md-6" >
			<jsp:include page="fragments/marketsummary.jsp"></jsp:include>
		</div>

    </div>
     
     
    
	
	
</body>
</html>