

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>

<c:if test="${portfolio != null}">


<div class="row clearfix">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">List of Holdings</h3>
			</div>
			<div class="panel-body">
				<table id="list-of-holdings"
					class="table table-striped table-bordered table-condensed text-center">
					<thead>
						<tr>
							<th class="text-center">Symbol</th>
							<th class="text-center">Held quantity</th>
							<th class="text-center">Total purchase price</th>
							<th class="text-center">Total sold value</th>
							<th class="text-center">Current unit value</th>
							<th class="text-center">Current holding value</th>
							<th class="text-center">Total Gain/Loss</th>
							<th class="text-center" colspan="2">Sell</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${portfolio.holdings}"  var="entry" varStatus="istatus">
							<c:if test="${entry.value.quantity > 0}">
							
							<tr >
								<td >${entry.value.symbol}</td>
								<td >${entry.value.quantity}</td>
								<td >${entry.value.purchaseValue}</td>
								<td >${entry.value.sellValue }</td>
								<td >${entry.value.currentValue }</td>
								<td >${entry.value.currentValue * entry.value.quantity }</td>
								
								<c:if test="${entry.value.sellValue - entry.value.purchaseValue + entry.value.currentValue * entry.value.quantity >0 }">
									<td class="text-success gray">
								</c:if>
								
								<c:if test="${entry.value.sellValue - entry.value.purchaseValue + entry.value.currentValue * entry.value.quantity <= 0 }">
									<td class="text-danger gray">
								</c:if>
										<span>
									      ${entry.value.sellValue - entry.value.purchaseValue + entry.value.currentValue * entry.value.quantity}
									     <c:if test="${entry.value.sellValue - entry.value.purchaseValue + entry.value.currentValue * entry.value.quantity >0}">
									      &uarr;  
									     
									     </c:if>
									     
									      <c:if test="${entry.value.sellValue - entry.value.purchaseValue + entry.value.currentValue * entry.value.quantity <=0}">
									       &darr;						     
									     
									     </c:if>
									     
									     
									     </span>
								</td>
	
								<form id="sellform${istatus.count}" action="/order" 	method="post" class="form-inline">
									<td class="gray"><input type="hidden" value="${entry.value.symbol}"	id="symbol" name="symbol" /> 
										
										<input type="hidden" value="${entry.value.currentValue}" id="price" name="price" /> 
										
										<input type="hidden" value="SELL" id="orderType"	name="orderType" /> 
										
										<input type="number" id="quantity" name="quantity" class="form-control" min="1" max="${entry.value.quantity}"  />
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									</td>
									<td><a id="buyBtn" 	onclick="document.getElementById('sellform${istatus.count}').submit()"
										class=" sell btn-sm btn-success">Sell</a></td>
								</form> 
							</tr>
							
						</c:if>
						</c:forEach>
					
						
					</tbody>
					<tfoot>
						<c:if test="${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue <0 }">
							<tr class="summary bold alert alert-block danger">
						
						</c:if>
						
						<c:if test="${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue >= 0 }">
							<tr class="summary bold alert alert-block success">
						
						</c:if>
						<tr>
							<td colspan="5">Total</td>
							<td >${portfolio.currentTotalValue}</td>
							<td >${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue }</td>
							<td colspan="2">&nbsp;</td>
						</tr>
					</tfoot>
				</table>
				<div id="no-holdings"></div>
			</div>
		</div>
	</div>
</div>

</c:if>
