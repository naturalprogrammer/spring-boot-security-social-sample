<%@include file="includes/header.jsp"%>	

<div class="panel panel-primary">

    <div class="panel-heading">
        <h3 class="panel-title">Profile</h3>
    </div>
    
    <div class="panel-body">
        <dl class="dl-horizontal">
            <dt>Name</dt>
            <dd><c:out value="${user.name}" /></dd>
            <dt>Email</dt>
            <dd><c:out value="${user.email}" /></dd>
        </dl>
    </div>
    
    <div class="panel-footer">

        <a class="btn btn-link" href="/users/${user.id}/edit">Edit</a>
        <a class="btn btn-link" href="/users/${user.id}/change-password">Change password</a>
        <a class="btn btn-link" href="/users/${user.id}/change-email">Change email id</a>
        
        <c:choose>

	      <c:when test="${facebookAuthorized}">
	
	          <form:form style="display: none" id="facebookDisconnectForm" action="/connect/facebook" method="delete">
	          </form:form>
	          <a class="btn btn-link" href="#" onclick="document.getElementById('facebookDisconnectForm').submit()">Disconnect from Facebook</a>
	
	      </c:when>
	
	      <c:otherwise>
	
			<form:form id="facebookConnectForm" style="display: none" action="/users/${user.id}/connect/facebook" method="POST">
			</form:form>
	        <a class="btn btn-link" href="#" onclick="document.getElementById('facebookConnectForm').submit()">Connect to Facebook</a>
	
	      </c:otherwise>
	
	  </c:choose>
        
        
        
        <a class="btn btn-link" href="/users/current/facebook-data">Facebook data</a>

    </div>

</div>

<%@include file="includes/footer.jsp"%>