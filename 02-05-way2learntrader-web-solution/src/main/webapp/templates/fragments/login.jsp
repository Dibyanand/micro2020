

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<div  class="row clearfix">
    <div class="row">
        <div class="panel panel-default col-md-11">
            <div class="panel-heading">
                <h3 class="panel-title">Login</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" action="/login"  method="post" >
                	<c:if test="${param.error}">
	                    <div  class="col-md-8 alert alert-blockalert-errorfadein">
	                        <div >Invalid username and password.</div>
	                    </div>
                    
                    </c:if>
                    
                    <c:if test="${param.logout}">
                    	 <div  class="col-md-8 alert alert-blockalert-errorfadein">
                        <div >You have been logged out.</div>
                    </div>
                    
                    </c:if>
                    
                   
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="username" class="control-label">Username:</label>
                        </div>
                        <div class="col-sm-9">
                            <input type="text" id="username" name="username" class="form-control" />
                        </div>
                    </div>
                    <div class="password form-group">
                        <div class="col-sm-2">
                            <label for="password" class="control-label">Password:</label>
                        </div>
                        <div class="col-sm-9">
                            <input type="password" id="password" name="password" class="form-control"/>
                            
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">Sign In</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-offset-2 col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Registration</h3>
            </div>
            <div class="panel-body">
                <p>Don't have a trader account yet?</p>
                <p><a id="showRegistrationBtn" href="/registration">Create One</a></p>
            </div>
        </div>
    </div>
</div>
</html>