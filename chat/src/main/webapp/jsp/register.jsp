<jsp:include page="/jsp/header.jsp" />
</head>
<%@ page import="com.seventysevenagency.chat.mvc.models.RegisterModel"%>
<body>
	<div class="container">
		<legend>
			<h1 class="register-header">Register</h1>
		</legend>
		<%
			RegisterModel model = (RegisterModel) request.getAttribute("model");
		%>
		<form class="form-horizontal" name="register" method="post">
			<div class="control-group">
				<label class="control-label" for="inputUsername">Username</label>
				<div class="controls">
					<input type="text" id="inputUsername"
						value="<%=(model.user.getUsername() != null) ? model.user
					.getUsername() : ""%>"
						name="username" placeholder="Username">
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
				<label class="control-label" for="inputPassword2">Repeat
					password</label>
				<div class="controls">
					<input type="password" id="inputPassword2" name="repassword"
						placeholder="Password">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="inputName">Name:</label>
				<div class="controls">
					<input type="text" name="name" id="inputName"
						value="<%=(model.user.getName() != null) ? model.user
					.getName() : ""%>" placeholder="Name"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputSurname">Surname:</label>
				<div class="controls">
					<input type="text" name="surname" id="inputSurname"
						value="<%=(model.user.getSurname() != null) ? model.user
					.getSurname() : ""%>" placeholder="Surname"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEmail">Email:</label>
				<div class="controls">
					<input type="text" name="email" id="inputEmail"
						value="<%=(model.user.getEmail() != null) ? model.user
					.getEmail() : ""%>" placeholder="email"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" name="submit" class="btn">Register</button>
				</div>
			</div>

			<%
				String error = model.getWarning("error");
				if (error != null) {
			%>
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Warning!</strong>
				<%=error%>
			</div>

			<%
				}
			%>
		</form>
		<a href="login">Login</a>
	</div>
</body>
</html>