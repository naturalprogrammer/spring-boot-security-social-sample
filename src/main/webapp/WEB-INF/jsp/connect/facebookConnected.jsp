<%@include file="../includes/header.jsp"%>
	
	<h3>Connected to Facebook</h3>

	<div class="alert alert-info">
		You are now connected to your Facebook account.
		Click <a href='${empty redirectAfterConnecting ? "/" : redirectAfterConnecting}'>here</a> to continue.
	</div>

<%@include file="../includes/footer.jsp"%>
