<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:if test="${search != null}">

	<div class="col-md-12">
		<div id="quote-control" class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="/trade" method="post" ">
                    <div class="form-group">
                        <input id="quote-input" type="text" maxlength="20" name="name"  style="width: 300px;" placeholder="Enter Company Name or Symbol"/>
                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                    &nbsp;<button type="submit" class="btn btn-success">Get Quotes</button>
                </form>
            </div>
		</div>
	</div>


</c:if>

