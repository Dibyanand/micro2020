
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	<div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Portfolio Summary</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<tbody>
						
						<tr>
							<td>Current Value of held shares-new</td>
							<td >${portfolio.currentTotalValue}</td>
						</tr>
						<tr>
							<td>Total Purchase Value</td>
							<td >${portfolio.purchaseValue}</td>
						</tr>
						<tr>
							<td>Total Sold Value</td>
							<td>${portfolio.sellValue }</td>
						</tr>
						
						<c:if test="${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue <0}">
							<tr class="summary alert alert-block warning" >
						</c:if>
						
						<c:if test="${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue >= 0}">
							<tr class="summary alert alert-block success" >
						</c:if>
						
								<td>Total Gain/Loss</td>
								<td class="average" >${portfolio.currentTotalValue + portfolio.sellValue - portfolio.purchaseValue }</td>
							</tr>
							
						<c:if test="${account != null}">
							<tr  class="alert alert-info">
								<td>Total Cash</td>
								<td class="average">${account.balance }</td>
							</tr>
						</c:if>
						
							
					</tbody>
				</table>
			</div>
		</div>
	</div>

