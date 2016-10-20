<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
</head>
<body>
	<h2>Login</h2>

	<!-- Trigger/Open The Modal -->
	<button id="myBtn">Login</button>
	<input type="hidden" id="status" value="${requestScope.status }"/>
	<div>
		<!-- <a href="booking" name="serviceCategoryName">Book a Plumber service</a>
		<a href="booking" name="serviceCategoryName">Book a Carpenter service</a> -->
		<form action="booking">
			<input type="submit" value="Plumber" name="serviceCategory">
			<input type="submit" value="Carpenter" name="serviceCategory">
		</form>
	</div>
		
	<!-- The Modal -->
	<div id="myModal" class="modal"> 
	  <!-- Modal content -->
		<div class="modal-content" >
			<span class="close">×</span>
			<table class="modaltable">
				<tr>
					<!-- <td>
						<div class="guest">
							<form>
								<table>
									<tr>
										<td>Email</td>
										<td><input type="text" name="email"/></td>
									</tr>
									<tr>
										<td>Contact</td>
										<td><input type="text" name="phonenumber"/></td>
									</tr>
									<tr>
										<td colspan="2"><input type="submit" value="Continue as a Guest" name="submitguest"/></td>
									</tr>
								</table>
							</form>
						</div>
					</td> -->
					<td style="border-left: solid #000000;">
					</td>
					<td>
						<div class="login">
						<p>Login Form</p>
							<form action="login" method="post" modelAttribute="loginUser">
								<table align="center">
									<tr>
										<td class="labels">Email</td>
										<td><input type="text" name="email"/></td>
									</tr>
									<tr>
										<td class="labels">Password</td>
										<td><input type="password" name="password"/></td>
									</tr>
									<tr>
										<td colspan="2"><input type="submit" value="Sign In" name="btn"/></td>
									<tr>
									<tr>
										<td><a href="forgot">Forgot Password</a></td>
										<td><a href="register">New User? Register Me</a></td>
									</tr>
								</table>
							</form>
						</div>	
					</td>
				</tr>
			</table>	
		</div>
	</div>
	<script src="<c:url value="/resources/js/login.js" />"></script>
</body>
</html>