<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Up and running with Spring Framework quickly</title>

	<!-- Bootstrap -->
	<link href="/public/lib/bootstrap-3.1.1/css/bootstrap.min.css" rel="stylesheet">

	<!-- Our CSS -->
	<link href="/public/css/styles.css" rel="stylesheet">

</head>
<body>
	<div class="container">
	
	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">Brand</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Link</a></li>
	        <li><a href="#">Link</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="navbar-form navbar-left" role="search">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">Submit</button>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
	      <sec:authorize access="isAnonymous()">
	      
	        <li><a href="<c:url value='/signup' />"><span class="glyphicon glyphicon-list-alt"></span> Sign up</a></li>
			<li>
			   	<a href="/auth/facebook?scope=${facebookScope}">
			    	<span class="glyphicon glyphicon-globe"></span> Facebook Sign Up
			    </a>
			</li>
	        <li>
                  <a href="/signin">Sign in <span class="glyphicon glyphicon-log-in"></span></a>
            </li>
         </sec:authorize>
         
          <sec:authorize access="isAuthenticated()">
              <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="glyphicon glyphicon-user"></span>
                      <sec:authentication property="principal.user.name" /> <b class="caret"></b>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="/users/<sec:authentication property='principal.user.id' />"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                     <li>
	                    <c:url var="logoutUrl" value="/logout" />
		               	<form:form	id="logoutForm" action="${logoutUrl}" method="post">
					    </form:form>
					    <a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
                     </li>
                  </ul>
              </li>
            </sec:authorize>
   </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

	<c:if test="${not empty flashMessage}">
		<div class="alert alert-${flashKind} alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  ${flashMessage}
		</div>
	</c:if>
	