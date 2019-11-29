<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${quotes != null }">
	<div class="col-md-12">
		<div id="quote-result" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Quotes - <span>  ${fn:length(quotes)}</span> result(s)</h3>
			</div>
			<div class="panel-body">
			<table id="list-of-quotes" class="table table-striped table-bordered table-condensed">
				
					<tr>
						<th>Company name</th>
						<th>Symbol</th>
						<th>Price</th>
						<th>Change</th>
						<th>Change YTD</th>
						<th>High</th>
						<th>Low</th>
						<th>Quantity</th>
					</tr>
				
				<c:forEach items="${quotes}"  var="quote" varStatus="status">
					<tr class="caps">
						<td >${quote.name}</td>
						<td >${quote.symbol}</td>
						<td >${quote.lastPrice}</td>
						<td >${quote.change}</td>
						<td >${quote.changeYTD}</td>
						<td >${quote.high}</td>
						<td >${quote.low}</td>
						<td><form id="form${status.index}" action="/order" method="post" class="form-inline">
						<input type="hidden" value="${quote.symbol}" id="symbol" name="symbol" />
						<input type="hidden" value="${quote.lastPrice}" id="price" name="price" />
						<input type="hidden" value="BUY" id="orderType" name="orderType" />
						<input type="number" id="quantity-input" class="input-mini" min="1" max="9999999999" name="quantity" /> 
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a id="buyBtn" onclick="document.getElementById('form${status.index}').submit()" class="btn-sm btn-success">Buy</a>
							</form></td>
					</tr>
				
				</c:forEach>
				
					
				
			</table>
			</div>
		</div>
	</div>
</c:if>