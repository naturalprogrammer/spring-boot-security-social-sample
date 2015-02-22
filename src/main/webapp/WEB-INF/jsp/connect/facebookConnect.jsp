<%@include file="../includes/header.jsp"%>	

	<h3>Connect to Facebook</h3>
	
	<c:if test="${not empty social_addConnection_duplicate}">
		<div class="alert alert-danger" role="alert">
			Duplicate connection. Are you trying to access same facebook account from multiple users?
		</div>
	</c:if>

	<form:form action="/connect/facebook" method="POST">
		<input type="hidden" name="scope" value="${facebookScope}" />
		<div class="alert alert-info">
			<p>You aren't connected to Facebook. Click the button to connect this application with your Facebook account.</p>
		</div>
		<p><button type="submit" class="btn btn-primary">Connect to Facebook</button></p>
	</form:form>

<%@include file="../includes/footer.jsp"%>
