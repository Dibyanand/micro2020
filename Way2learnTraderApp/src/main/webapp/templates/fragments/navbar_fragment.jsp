<%@taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>

 <div 
         class="navbar navbar-inverse navbar-fixed-top">
         <security:authorize access="!isAuthenticated()">  
     		
				<div class="container-fluid">
			        <div class="navbar-header">
			            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#empty-navbar-collapse" aria-expanded="false">
			                <span class="sr-only">Toggle navigation</span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			            </button>
			            <a class="navbar-brand" href="http://way2learnonline.com">
			                <img alt="Way2Learn Trader" src="images/logo.png" width="120px;"/>
			            </a>
			        </div>
			        <div class="collapse navbar-collapse" id="empty-navbar-collapse">
			            <ul class="nav navbar-nav navbar-right">
			                <li>
			                    <a class="navbar-brand" href="#">
			                        <img alt="Way2Learn Trader" width="220px;"/>
			                    </a>
			                </li>
			            </ul>
					</div>
				</div>
		

    	 </security:authorize>
    </div>
    
     <security:authorize access="isAuthenticated()">  
    <div 
         class="navbar navbar-inverse navbar-fixed-top">
         
        
         	<div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#trader-navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://way2learnonline.com">
			                <img alt="Way2Learn Trader" src="images/logo.png" width="120px;"/>
			            </a>
        </div>
        <div class="collapse navbar-collapse" id="trader-navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a id="nb-dashboard" href="/">
                    <i class="fa fa-home"></i>&nbsp;&nbsp;Home
                </a></li>
				<li><a id="nb-portfolio" href="/portfolio">
                    <i class="fa fa-book"></i>&nbsp;&nbsp;Portfolio
                </a></li>
				<li><a id="nb-trade" href="/trade">
                    <i class="fa fa-line-chart"></i>&nbsp;&nbsp;Trade
                </a></li>
			</ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="navbar-brand" href="#">
                        <img alt="Way2Learn Trader" width="220px;"/>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <i class="fa fa-user"></i>&nbsp;&nbsp;
                        <span id="nb-username" >
                        <security:authentication property="name"/> 
                        	
                        	
                        
                        </span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!--<li class="disabled"><a id="profile" href="/">Profile</a></li>-->
                        <!--<li class="disabled"><a id="help" href="/">Help</a></li>-->
                        <!--<li role="separator" class="divider"></li>-->
                        <li><a id="logout" href="#">Logout</a></li>
                    </ul>
                </li>
            </ul>
            <form id="logoutform" action="/logout" method="post">
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <script  type="text/javascript">
                /*<![CDATA[*/
                $('#logout').click( function() {
                    $('#logoutform').submit();
                });
                /*]]>*/
            </script>
		</div>
	</div>
        
    </div>
    
     </security:authorize>