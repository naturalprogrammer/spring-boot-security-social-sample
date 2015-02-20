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

    </div>

</div>

<%@include file="includes/footer.jsp"%>