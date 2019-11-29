	<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<div>
		<h3><span class="label label-primary" >${title}</span></h3>
		<table class="table">
			<tr>
				<th>Symbol</th>
				<th>Price</th>
				<th>Change</th>
				<th>High</th>
				<th>Low</th>
			</tr>
			
			<c:forEach items="${quoteList}" var="quote">
			
				<c:if test="${quote.change >0 }">
					<tr class="text-success" >
				</c:if>
				<c:if test="${quote.change <= 0 }">
					<tr class="text-danger" >
				</c:if>
			
				<td><strong >${quote.symbol}</strong></td>
				<td >${quote.lastPrice}</td>
				
				<c:if test="${quote.change > 0 }">
					<td >${quote.change} &uarr; </td>
				</c:if>
				<c:if test="${quote.change <= 0 }">
					<td >${quote.change} &darr; </td>
				</c:if>
				
				<td>${quote.high}</td>
				<td >${quote.low}</td>
			</tr>
			<tr></tr>
			</c:forEach>
			
		</table>
	</div>