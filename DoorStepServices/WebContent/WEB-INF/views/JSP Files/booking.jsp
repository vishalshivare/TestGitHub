<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Booking</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> -->
<%-- <script src="<c:url value="/resources/js/jquery.js"/>"></script> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

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

.ui-datepicker {
	width: 216px;
	height: auto;
	margin: 5px auto 0;
	font: 9pt Arial, sans-serif;
	-webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
	-moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
}

.ui-datepicker a {
	text-decoration: none;
}

.ui-datepicker table {
	width: 100%;
}

.ui-datepicker-header {
	background: #626262;
	color: #e0e0e0;
	font-weight: bold;
	-webkit-box-shadow: inset 0px 1px 1px 0px rgba(250, 250, 250, 2);
	-moz-box-shadow: inset 0px 1px 1px 0px rgba(250, 250, 250, .2);
	box-shadow: inset 0px 1px 1px 0px rgba(250, 250, 250, .2);
	text-shadow: 1px -1px 0px #000;
	filter: dropshadow(color = #000, offx = 1, offy = -1);
	line-height: 30px;
	border-width: 1px 0 0 0;
	border-style: solid;
	border-color: #111;
}

.ui-datepicker-title {
	text-align: center;
}

.ui-datepicker-prev, .ui-datepicker-next {
	display: inline-block;
	width: 30px;
	height: 30px;
	text-align: center;
	cursor: pointer;
	background-repeat: no-repeat;
	line-height: 600%;
	overflow: hidden;
}

.ui-datepicker-prev {
	float: left;
	background-position: center -30px;
}

.ui-datepicker-next {
	float: right;
	background-position: center 0px;
}

.ui-datepicker thead {
	background-color: #f7f7f7;
	background-image: -moz-linear-gradient(top, #f7f7f7 0%, #f1f1f1 100%);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f7f7f7),
		color-stop(100%, #f1f1f1));
	background-image: -webkit-linear-gradient(top, #f7f7f7 0%, #f1f1f1 100%);
	background-image: -o-linear-gradient(top, #f7f7f7 0%, #f1f1f1 100%);
	background-image: -ms-linear-gradient(top, #f7f7f7 0%, #f1f1f1 100%);
	background-image: linear-gradient(top, #f7f7f7 0%, #f1f1f1 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f7f7f7',
		endColorstr='#f1f1f1', GradientType=0);
	border-bottom: 1px solid #bbb;
}

.ui-datepicker th {
	text-transform: uppercase;
	font-size: 6pt;
	padding: 5px 0;
	color: #666666;
	text-shadow: 1px 0px 0px #ccc;
	filter: dropshadow(color = #ccc, offx = 1, offy = 0);
}

.ui-datepicker tbody td {
	padding: 0;
	border-right: 1px solid #bbb;
}

.ui-datepicker tbody td:last-child {
	border-right: 0px;
}

.ui-datepicker tbody tr {
	border-bottom: 1px solid #bbb;
}

.ui-datepicker tbody tr:last-child {
	border-bottom: 0px;
}

.ui-datepicker td span, .ui-datepicker td a {
	display: inline-block;
	font-weight: bold;
	text-align: center;
	width: 30px;
	height: 30px;
	line-height: 30px;
	color: #666666;
	text-shadow: 1px 1px 0px #fff;
	filter: dropshadow(color = #fff, offx = 1, offy = 1);
}

.ui-datepicker-calendar .ui-state-default {
	background: #ededed;
	background: -moz-linear-gradient(top, #ededed 0%, #dedede 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #ededed),
		color-stop(100%, #dedede));
	background: -webkit-linear-gradient(top, #ededed 0%, #dedede 100%);
	background: -o-linear-gradient(top, #ededed 0%, #dedede 100%);
	background: -ms-linear-gradient(top, #ededed 0%, #dedede 100%);
	background: linear-gradient(top, #ededed 0%, #dedede 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ededed',
		endColorstr='#dedede', GradientType=0);
	-webkit-box-shadow: inset 1px 1px 0px 0px rgba(250, 250, 250, .5);
	-moz-box-shadow: inset 1px 1px 0px 0px rgba(250, 250, 250, .5);
	box-shadow: inset 1px 1px 0px 0px rgba(250, 250, 250, .5);
}

.ui-datepicker-unselectable .ui-state-default {
	background: #7a7a7a;
	color: #b4b3b3;
}

.ui-datepicker-calendar .ui-state-hover {
	background: #626262;
}

.ui-datepicker-calendar .ui-state-active {
	background: #6eafbf;
	-webkit-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
	-moz-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
	box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
	color: #e0e0e0;
	text-shadow: 0px 1px 0px #4d7a85;
	filter: dropshadow(color = #4d7a85, offx = 0, offy = 1);
	border: 1px solid #55838f;
	position: relative;
	margin: -1px;
}

.ui-datepicker-calendar td:first-child .ui-state-active {
	width: 29px;
	margin-left: 0;
}

.ui-datepicker-calendar td:last-child .ui-state-active {
	width: 29px;
	margin-right: 0;
}

.ui-datepicker-calendar tr:last-child .ui-state-active {
	height: 29px;
	margin-bottom: 0;
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
	font-size: 14px;
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
	<script>
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

		var dateToday = new Date();

		$(document).ready(
				function() {

					$("#datepicker").datepicker(
							{
								dateFormat : 'mm/dd/yy',
								minDate : dateToday,
								inline : true,
								showOtherMonths : true,
								dayNamesMin : [ 'Sun', 'Mon', 'Tue', 'Wed',
										'Thu', 'Fri', 'Sat' ],
								onSelect : function(dateText, inst) {
									var location = $('#location').val();
									//alert(location);
									$.ajax({
										type : "GET",
										url : "getSlots",
										data : "date=" + dateText
												+ "&location=" + location,
										success : function(response) {
											$("#slots").html(response);
										},
										error : function(e) {
											alert('Error: ' + e);
										}
									});
								}
							});
				});
	</script>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home2">Door Step Services</a>
		</div>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="home2">HOME</a></li>
				<li><a href="history">VIEW HISTORY</a></li>
				<li><a href="editUser">EDIT PROFILE</a></li>
				<li><a href="changePassword">CHANGE PASSWORD</a></li>
				<li><a href="logout">LOGOUT</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div>
		<!-- <div class="container"> -->
		<!-- <h1>My Portfolio</h1>      
    <p>Some text that represents "Me"...</p> -->
		<img src="${requestScope.image }" alt="New York" width="100%"
			height="400">
		<!--  </div> -->
	</div>
	<br>
	<div class="container-fluid bg-3 text-center">
		<h3>${sessionScope.serviceCategory }Service</h3>
		<br>
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="row">

				<form action="booking" method="post" class="form-horizontal"
					id="myform" name="myform">

					<div class="form-group">
						<label class="control-label col-sm-2" for="service">Select
							a service:</label>
						<div class="col-sm-10">
							<c:forEach var="s" items="${requestScope.listOfServices }">
							<label class="control control--checkbox">${s.serviceName } <input
								type="checkbox" name="service" value=${s.serviceId } />
								<div class="control__indicator"></div>
							</label>
</c:forEach>

<%-- 
							<c:forEach var="s" items="${requestScope.listOfServices }">
								<input type="checkbox" name="service" value=${s.serviceId }>${s.serviceName }
							</c:forEach> --%>
							<br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Address">Address:</label>
						<div class="col-sm-10">
							<textarea cols="30" rows="4" name="address" class="form-control">${sessionScope.loggedInUser.address }</textarea>
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
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="date">Select
							Date:</label>
						<div class="col-sm-10">
							<input type="text" id="datepicker" name="date"
								class="form-control" /><br>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Time">Select
							Time:</label>
						<div class="col-sm-10">
							<div id="slots">
								<select class="form-control">
									<option value="slot1">Select</option>
								</select>
							</div>
							<br>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 ">
							<input type="submit" value="Add to Cart" class="btn btn-primary">
						</div>
					</div>
				</form>
				<script language="JavaScript" type="text/javascript"
					xml:space="preserve">
					//<![CDATA[
					//You should create the validator only after the definition of the HTML form
					var frmvalidator = new Validator("myform");
					frmvalidator.addValidation("service", "selone",
							"please select atleast one service.");
					frmvalidator.addValidation("address", "req",
							"Please enter address");

					frmvalidator.addValidation("date", "req",
							"Date is required.");

					frmvalidator.addValidation("address", "maxlen=50");
					frmvalidator.addValidation("city", "dontselect=000",
							"please select city.");
					frmvalidator.addValidation("location", "dontselect=000",
							"please select location.");

					frmvalidator.addValidation("timeslot", "dontselect=000",
							"please select slots.");
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