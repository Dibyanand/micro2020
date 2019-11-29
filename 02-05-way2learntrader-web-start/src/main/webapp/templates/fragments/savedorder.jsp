<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

	<c:if test="${savedOrder != null}">
	
	
	
	<div class="col-md-12">
		<div class="panel panel-default">
		<div class="title alert alert-success">
			<h3>Order Successful</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<tbody>
					<tr>
						<th>Type</th>
						<th>Symbol</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Fee</th>
						<th>TOTAL</th>
						
					</tr>
					<tr>
						<td>${savedOrder.orderType}</td>
						<td>${savedOrder.symbol}</td>
						<td >${savedOrder.price}</td>
						<td >${savedOrder.quantity}</td>
						<td >${savedOrder.orderFee}</td>
						<td >${savedOrder.price*savedOrder.quantity+savedOrder.orderFee}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	
	</c:if>
