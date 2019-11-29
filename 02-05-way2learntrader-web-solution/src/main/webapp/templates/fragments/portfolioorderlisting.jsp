<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>


<div  class="row clearfix">
	<div class="col-md-12">
		<div id="orders-control" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Transactions</h3>
			</div>
			<div class="panel-body"></div>
			<table id="list-of-orders" class="table table-striped table-bordered table-condensed text-center">
				<thead>
					<tr>
						<th class="text-center">Order ID</th>
						<th class="text-center">Status</th>
						<th class="text-center">Order Date</th>
						<th class="text-center">Order Fee</th>
						<th class="text-center">Order Type</th>
						<th class="text-center">Symbol</th>
						<th class="text-center">Price</th>
						<th class="text-center">Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${portfolio.holdings}"  var="holding">
						<tr  class="info">
						<td colspan="8" class="text-left" >Holding for:   ${holding.value.symbol}  - Current Quantity:  ${holding.value.quantity}</td>
					
						<c:forEach items="${holding.value.orders}" var="txn">
								<tr >
									<td >${txn.orderId}</td>
									<td ><span class="completed">completed </span></td>
									<td >${txn.completionDate}</td>
			
									<td >${txn.orderFee}</td>
									<td >${txn.orderType}</td>
									<td >${txn.symbol}</td>
									<td >${txn.price}</td>
									<td >${txn.quantity}</td>
								</tr>
						</c:forEach>
				
					</tr>
					</c:forEach>
				
					
				</tbody>
			</table>
			<div id="no-orders"></div>
		</div>
	</div>
</div>



