<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> -->
<script src="<c:url value="/resources/js/jquery.js"/>"></script>

<style type="text/css" xml:space="preserve">
BODY, P, TD {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 10pt
}

A {
	font-family: Arial, Verdana, Helvetica, sans-serif;
}

B {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
}

.control-group {
	display: inline-block;
	width: 200px;
	height: 210px;
	margin: 10px;
	padding: 30px;
	text-align: left;
	vertical-align: top;
	background: #fff;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
}

.control {
	font-size: 18px;
	position: relative;
	display: block;
	margin-bottom: 15px;
	padding-left: 30px;
	cursor: pointer;
}

.control input {
	position: absolute;
	z-index: -1;
	opacity: 0;
}

.control__indicator {
	position: absolute;
	top: 2px;
	left: 0;
	width: 20px;
	height: 20px;
	background: #e6e6e6;
}

.control--radio .control__indicator {
	border-radius: 50%;
}

/* Hover and focus states */
.control:hover input ~ .control__indicator, .control input:focus ~
	.control__indicator {
	background: #ccc;
}

/* Checked state */
.control input:checked ~ .control__indicator {
	background: #2aa1c0;
}

/* Hover state whilst checked */
.control:hover input:not ([disabled] ):checked ~ .control__indicator,
	.control input:checked:focus ~ .control__indicator {
	background: #0e647d;
}

/* Disabled state */
.control input:disabled ~ .control__indicator {
	pointer-events: none;
	opacity: .6;
	background: #e6e6e6;
}

/* Check mark */
.control__indicator:after {
	position: absolute;
	display: none;
	content: '';
}

/* Show check mark */
.control input:checked ~ .control__indicator:after {
	display: block;
}

/* Checkbox tick */
.control--checkbox .control__indicator:after {
	top: 4px;
	left: 8px;
	width: 3px;
	height: 8px;
	transform: rotate(45deg);
	border: solid #fff;
	border-width: 0 2px 2px 0;
}

/* Disabled tick colour */
.control--checkbox input:disabled ~ .control__indicator:after {
	border-color: #7b7b7b;
}

/* Radio button inner circle */
.control--radio .control__indicator:after {
	top: 7px;
	left: 7px;
	width: 6px;
	height: 6px;
	border-radius: 50%;
	background: #fff;
}

/* Disabled circle colour */
.control--radio input:disabled ~ .control__indicator:after {
	background: #7b7b7b;
}
</style>

<script language="JavaScript"
	src="<c:url value="/resources/js/gen_validatorv4.js"/>"
	type="text/javascript" xml:space="preserve"></script>
<style>
body {
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}

h3, h4 {
	margin: 10px 0 30px 0;
	letter-spacing: 10px;
	font-size: 20px;
	color: #111;
}

.container {
	padding: 80px 120px;
}

.person {
	border: 10px solid transparent;
	margin-bottom: 25px;
	width: 80%;
	height: 80%;
	opacity: 0.7;
}

.person:hover {
	border-color: #f1f1f1;
}

.carousel-inner img {
	-webkit-filter: grayscale(90%);
	filter: grayscale(90%); /* make all photos black and white */
	width: 100%; /* Set width to 100% */
	margin: auto;
}

.carousel-caption h3 {
	color: #fff !important;
}

@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
		/* Hide the carousel text when the screen is less than 600 pixels wide */
	}
}

.bg-1 {
	background: #2d2d30;
	color: #bdbdbd;
}

.bg-1 h3 {
	color: #fff;
}

.bg-1 p {
	font-style: italic;
}

.list-group-item:first-child {
	border-top-right-radius: 0;
	border-top-left-radius: 0;
}

.list-group-item:last-child {
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.thumbnail {
	padding: 0 0 15px 0;
	border: none;
	border-radius: 0;
}

.thumbnail p {
	margin-top: 15px;
	color: #555;
}

.btn {
	padding: 10px 20px;
	background-color: #333;
	color: #f1f1f1;
	border-radius: 0;
	transition: .2s;
}

.btn:hover, .btn:focus {
	border: 1px solid #333;
	background-color: #fff;
	color: #000;
}

.modal-header, h4, .close {
	background-color: #333;
	color: #fff !important;
	text-align: center;
	font-size: 30px;
}

.modal-header, .modal-body {
	padding: 40px 50px;
}

.nav-tabs li a {
	color: #777;
}

#googleMap {
	width: 100%;
	height: 400px;
	-webkit-filter: grayscale(100%);
	filter: grayscale(100%);
}

.navbar {
	font-family: Montserrat, sans-serif;
	margin-bottom: 0;
	background-color: #2d2d30;
	border: 0;
	font-size: 11px !important;
	letter-spacing: 4px;
	opacity: 0.9;
}

.navbar li a, .navbar .navbar-brand {
	color: #d5d5d5 !important;
}

.navbar-nav li a:hover {
	color: #fff !important;
}

.navbar-nav li.active a {
	color: #fff !important;
	background-color: #29292c !important;
}

.navbar-default .navbar-toggle {
	border-color: transparent;
}

.open .dropdown-toggle {
	color: #fff;
	background-color: #555 !important;
}

.dropdown-menu li a {
	color: #000 !important;
}

.dropdown-menu li a:hover {
	background-color: red !important;
}

footer {
	background-color: #2d2d30;
	color: #f5f5f5;
	padding: 32px;
}

footer a {
	color: #f5f5f5;
}

footer a:hover {
	color: #777;
	text-decoration: none;
}

.form-control {
	border-radius: 0;
}

textarea {
	resize: none;
}
</style>
</head>


<body>
	<script type="text/javascript">
		function doAjaxPost() {
			// get the form values
			var city = $('#city').val();
			//alert(city);
			$.ajax({
				type : "GET",
				url : "AddCity",
				data : "cityName=" + city,
				success : function(response) {
					$("#locationCell").html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	</script>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home.jsp">Door Step Services</a>
		</div>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="home.jsp">HOME</a></li>

			</ul>
		</div>
	</div>
	</nav>

	<div>
		<!-- <div class="container"> -->
		<!-- <h1>My Portfolio</h1>      
    <p>Some text that represents "Me"...</p> -->
		<img
			src="http://demo.ttsit.in/nayan_utsav/images/register_header_banner.jpg"
			alt="New York" width="100%" height="400">
		<!--  </div> -->
	</div>
	<br>
	<div class="container-fluid bg-3 text-center">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="row">
				<h3>USER REGISTRATION</h3>
				<br>
				<h4>${requestScope.register }</h4>


				<form action="registerUser" modelAttribute="user" method="post"
					name="myform" id="myform" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2" for="FirstName">First
							Name:</label>
						<div class="col-sm-10">
							<input type="text" name="firstName" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="lastName">Last
							Name:</label>
						<div class="col-sm-10">
							<input type="text" name="lastName" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">



						<label class="control-label col-sm-2" for="Gender">Gender:</label>


					
						<div class="col-sm-10">
							<label class="control control--radio">Male <input
								type="radio" name="gender" value="Male" checked="true" />
								<div class="control__indicator"></div>
							</label> <label class="control control--radio">Female <input
								type="radio" name="gender" value="Female" />
								<div class="control__indicator"></div>
							</label>
						</div>


					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Email">Email:</label>
						<div class="col-sm-10">
							<input type="text" name="email" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Password">Password:</label>
						<div class="col-sm-10">
							<input type="text" name="password" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Password">Mobile
							Number:</label>
						<div class="col-sm-10">
							<input type="text" name="mobileNumber" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Address">Address:</label>
						<div class="col-sm-10">
							<textarea cols="30" rows="4" name="address" class="form-control"></textarea>
							<br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="City">City:</label>
						<div class="col-sm-10">
							<select name="city" id="city" class="form-control"
								onchange="doAjaxPost()">
								<option value="000" selected="selected">Select</option>
								<c:forEach var="c" items="${requestScope.cities }">
									<option id=${c.cityId }>${c.cityName }</option>
								</c:forEach>
							</select><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Location">Location:</label>
						<div class="col-sm-10">
							<div id="locationCell">
								<select class="form-control">
									<option value="000" selected="selected">Select</option>
								</select>
							</div>
							<br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Pin">Pin Code:</label>
						<div class="col-sm-10">
							<input type="text" name="pinCode" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="SecurityQuestion">Security
							Question:</label>
						<div class="col-sm-10">
							<select name="securityQuestion" class="form-control"
								id="question">
								<c:forEach var="q" items="${requestScope.questions }">
									<option id=${q.questionId }>${q.question }</option>
								</c:forEach>
							</select><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="SecurityAnswer">Security
							Answer:</label>
						<div class="col-sm-10">
							<input type="text" name="securityAnswer" class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 ">
							<input type="hidden" name="role" value="user" /> <input
								type="submit" value="Register" class="btn btn-primary">
						</div>
					</div>

				</form>
				<script language="JavaScript" type="text/javascript"
					xml:space="preserve">
					//<![CDATA[
					//You should create the validator only after the definition of the HTML form
					var frmvalidator = new Validator("myform");
					frmvalidator.addValidation("firstName", "req",
							"Please enter your First Name");
					frmvalidator.addValidation("firstName", "maxlen=20",
							"Max length for FirstName is 20");
					frmvalidator.addValidation("firstName", "alpha",
							"Alphabetic chars only");

					frmvalidator.addValidation("lastName", "req",
							"Please enter your Last Name");
					frmvalidator.addValidation("lastName", "maxlen=20",
							"Max length is 20");

					frmvalidator.addValidation("email", "maxlen=50");
					frmvalidator.addValidation("email", "req");
					frmvalidator.addValidation("email", "email");

					frmvalidator.addValidation("mobileNumber", "req");
					frmvalidator.addValidation("mobileNumber", "numeric",
							"Numeric only");
					frmvalidator.addValidation("mobileNumber", "minlen=10");
					frmvalidator.addValidation("mobileNumber", "maxlen=10");

					frmvalidator.addValidation("address", "maxlen=50");
					frmvalidator.addValidation("city", "dontselect=000");

					frmvalidator.addValidation("pinCode", "req",
							"Please enter Pin Code");

					//]]>
				</script>
			</div>

		</div>
	</div>

	<br>

	<footer class="container-fluid text-center">
	<p>Footer Text</p>
	</footer>

</body>

</html>