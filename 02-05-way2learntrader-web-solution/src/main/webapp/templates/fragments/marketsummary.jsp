<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>



    <div id="market-carousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#market-carousel" data-slide-to="0" class="active"></li>
            <li data-target="#market-carousel" data-slide-to="1"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
            	<c:set value="${marketSummary.topGainers}" var="quoteList" scope="request" />
            	<c:set value="Information Technology" var="title" scope="request"/>
            	<jsp:include page="marketsummaryquote.jsp" />   		
               
            </div>
            <div class="item">
            <c:set value="${marketSummary.topLosers}" var="quoteList" scope="request" />
            	<c:set value="Financial Services" var="title" scope="request"/>
            	<jsp:include page="marketsummaryquote.jsp" />
               
            </div>
        </div>
    </div>

    <script th:inline="javascript">
        /*<![CDATA[*/
        $('#market-carousel').carousel({
            pause: "none"
        });
        /*]]>*/
    </script>



