

<div class="row clearfix well well-lg">
	<div class="col-md-8 panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                Registration&nbsp;&nbsp;&nbsp;<small>Please enter test data for creating the account</small>
            </h3>
        </div>
        <div class="panel-body">
		<form action="/registration" method="post" class="form-horizontal" >
            <div id="registration-error" class="hide col-md-8 alert alert-block alert-error fade in">
				<a data-dismiss="alert" class="close">x</a>
				<h4 class="alert-heading"></h4>
				<p></p>
			</div>
			<div class="col-md-6">
				<div id="fullname-control" class="form-group">
                    <div class="col-sm-3">
					    <label for="fullname-input" class="control-label">Full Name:</label>
                    </div>
					<div class="col-sm-9">
						<input type="text" value="" id="fullname-input" class="form-control" name="fullname" />
						<br/>
						<span id="fullnameError" class="help-inline hide">Full Name error</span>
					</div>
				</div>
				<div id="email-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="email-input" class="control-label">Email:</label>
                    </div>
					<div class="col-sm-9">
						<input type="text" value="" id="email-input" class="form-control focused" name="email" />
                        <br/> <span id="emailError" class="help-inline hide">Email Error</span>
					</div>
				</div>
				<div id="password-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="reg-password-input" class="control-label">Password:</label>
                    </div>
					<div class="col-sm-9">
						<input type="password" value="" id="reg-password-input" class="form-control focused"  name="passwd" />
                        <br/> <span id="passwdError" class="help-inline hide">Password Error</span>
					</div>
				</div>
				<div id="matchpasswd-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="matchpasswd-input" class="control-label">Password Confirm:</label>
                    </div>
					<div class="col-sm-9">
						<input type="password" id="matchpasswd-input" class="form-control focused"/>
                        <br/> <span id="matchpasswd-error" class="help-inline hide">Password Confirmation error</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="username-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="reg-username-input" class="control-label">Username:</label>
                    </div>
					<div class="col-sm-9">
						<input type="text" value="" id="reg-username-input" class="form-control focused" name="userid"/>
                        <br/> <span id="usernameError" class="help-inline hide">UsernameError</span>
					</div>
				</div>
				<div id="openbalance-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="openbalance-input" class="control-label">Opening Balance:</label>
                    </div>
					<div class="col-sm-9">
						<input type="number" value="100000" id="openbalance-input" class="form-control focused"  name="balance"/>
                        <br/> <span id="openbalanceError" class="help-inline hide">Open Balance Error</span>
					</div>
				</div>
				<div id="creditcard-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="creditcard-input" class="control-label">Credit Card #:</label>
                    </div>
					<div class="col-sm-9">
						<input type="text" readonly="" value="1234123412341234" id="creditcard-input" class="form-control focused" maxlength="16" name="creditcard"/>
                        <br/> <span id="creditcardError" class="help-inline hide">CreditCardError</span>
					</div>
				</div>
				<div id="address-control" class="form-group">
                    <div class="col-sm-3">
                        <label for="address-input" class="control-label">Address:</label>
                    </div>
					<div class="col-sm-9">
						<input type="text" value="" id="address-input" class="form-control focused" name="address"/>
						
                        <br/> <span id="addressError" class="help-inline hide">AddressError</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions col-md-offset-4 col-md-4">
					<button id="registrationBtn" class="btn btn-success">Register</button>
				</div>
			</div>
			
			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
        </div>
	</div>
	<div class="col-md-offset-1 col-md-2 panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Login</h3>
        </div>
        <div class="panel-body">
            <p>If you have already registered, please enter your login and password on our login page.</p>
            <p>
                <a id="showLoginBtn" href="/">login</a>
            </p>
        </div>
	</div>
</div>

</html>
