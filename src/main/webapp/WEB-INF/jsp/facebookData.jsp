<%@include file="includes/header.jsp"%>	

		<h3>Hello, <c:out value="${facebookProfile.name}" />!</h3>

		<h4>Here is your home feed:</h4>
		
		<c:forEach var="post" items="${feed}">
			<div>
				<b><c:out value="${post.from.name}"></c:out></b> wrote:
				<p><c:out value="${post.message}"></c:out></p>
				<c:if test="${post.picture}">
					<img src="${post.picture}"></img>
				</c:if>
				<hr/>
			</div>
		</c:forEach>

<%@include file="includes/footer.jsp"%>
