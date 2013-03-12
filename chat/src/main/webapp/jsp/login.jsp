<jsp:include page="/jsp/header.jsp" />
</head>
<%@ page import="com.seventysevenagency.chat.mvc.models.LoginModel"%>
<body>
	<div class="container">
		<legend><h1 class="login-header">Login</h1></legend>
		<%
			LoginModel model = (LoginModel) request.getAttribute("model");
		%>
		<form class="form-horizontal" name="login" method="post">
			<div class="control-group">
				<label class="control-label" for="inputUsername">Username</label>
				<div class="controls">
					<input type="text" id="inputUsername"
						value="<%=(model.getUsername() != null) ? model.getUsername() : ""%>"
						name="username" placeholder="Email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPassword">Password</label>
				<div class="controls">
					<input type="password" id="inputPassword" name="password"
						placeholder="Password">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<p>
						<small>Don't have account?<a href="register"> Register</a></small>
					</p>
					<button type="submit" class="btn">Sign in</button>
				</div>
			</div>	
			<%
				String error = model.getWarning("error");
				if (error != null) {
			%>
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Warning!</strong> <%= error %>
			</div>

			<%
				}
			%>		
		</form>
	</div>
</body>
</html>