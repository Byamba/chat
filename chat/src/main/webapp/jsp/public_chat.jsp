<jsp:include page="/jsp/header.jsp" />
</head>
<%@ page import="com.seventysevenagency.chat.mvc.models.ChatroomModel"%>
<%@ page import="com.seventysevenagency.chat.domain.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date"%>
<%
	ChatroomModel model = (ChatroomModel) request.getAttribute("model");
%>
<body>
	<h1>Chatroom</h1>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6">
				<div class="span4">
					<%
						List<Message> messages = model.getMessageList();
						System.out.print(messages.size());
						for (Message message : messages) {
							User user = message.getUser();
							String name = null;
							if (user != null) {
								name = user.getName();
							}
							java.util.Date time = new java.util.Date(
									(long) message.getDate() * 1000);
					%>
					<span> <%=time%></span> <span> <%=name%> wrote:
					</span> <span> <%=message.getText()%></span><br />
					<%
						}
					%>
				</div>
				<div class="span2 userlist">
					<ul>
						<%
							List<User> users = model.getActiveUsersList();
							for (User user : users) {
						%>
						<li><%=user.getUsername()%></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<form method="POST" action="">
					<div class="span4">
						<input type="text" name="message" required />
					</div>
					<div class="span2">
						<button type="submit" class="btn">Submit</button>
					</div>
				</form>
			</div>
		</div>
		<div class="navbar">
			<div class="navbar-inner">
				<ul class="nav">
					<%
						int currentConversationId = model.getConversationId();
						List<Conversation> conversationList = model.getConversationList();
						for (Conversation conversation : conversationList) {
							int conversationId = conversation.getId();
					%>
					<li
						<%=(currentConversationId == conversationId) ? "class='active'"
						: ""%>><a
						href="?conversation=<%=conversationId%>"><%=conversation.getName()%></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>

		<a href="logout">Logout</a>
	</div>

</body>
</html>