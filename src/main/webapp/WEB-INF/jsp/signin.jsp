<%@include file="includes/header.jsp"%>
	
<div class="panel panel-primary">

	<div class="panel-heading">
		<h3 class="panel-title">Please sign in</h3>
	</div>
	
	<div class="panel-body">
	
		<c:if test="${param.error != null}">
		    <div class="alert alert-danger">
		        Invalid username and password.
		    </div>
	    </c:if>
	    
	    <c:if test="${param.logout != null}">
		    <div class="alert alert-danger">
		        You have been logged out.
		    </div>
	    </c:if>
	
		<form:form role="form" method="post">
		
			<div class="form-group">
				<label for="username">Email address</label>
				<input id="username" name="username" type="email" class="form-control" placeholder="Enter email" />
				<p class="help-block">Enter your email address.</p>
			</div>
			
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
				<form:errors cssClass="error" path="password" />
			</div>
			
			<button type="submit" class="btn btn-primary">Sign In</button>
			<a class="btn btn-default" href="/forgot-password">Forgot Password</a>
			<a class="btn btn-default" href="/auth/facebook?scope=${facebookScope}">Facebook Sign In</a>
		</form:form>
	</div>
</div>

<%@include file="includes/footer.jsp"%>