<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Payment</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> -->
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
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
</style>
</head>


<body>

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
		<img
			src="http://www.homegas.net/wp-content/uploads/2014/07/Online_Bill_Pay_Banner1.gif"
			alt="New York" width="100%" height="400">
		<!--  </div> -->
	</div>
	<br>
	<div class="container-fluid bg-3 text-center">
		<h3>PAYMENT DETAILS</h3>
		<br>
		<div class="row">
			<dir class="col-sm-3"></dir>
			<div class="col-sm-6">
				<!-- Credit card form -->
				<!-- <div class="col-sm-4"></div>
				<div class="col-sm-4"> -->
					<div class="container">
						<div class="row">
							<div class="col-xs-6 col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">
											<img class="pull-right"
												src="http://i76.imgup.net/accepted_c22e0.png">Payment
											Details
										</h3>
									</div>
									<div class="panel-body">
										<form role="form" id="payment-form" action="paymentSuccess">
											<div class="row">
												<div class="col-xs-12">
													<div class="form-group">
														<label for="cardNumber">CARD NUMBER</label>
														<div class="input-group">
															<input type="text" class="form-control" name="cardNumber"
																placeholder="Valid Card Number" required autofocus
																data-stripe="number" /> <span class="input-group-addon"><i
																class="fa fa-credit-card"></i></span>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-7 col-md-7">
													<div class="form-group">
														<label for="expMonth">EXPIRATION DATE</label>
														<div class="col-xs-6 col-lg-6 pl-ziro">
															<input type="text" class="form-control" name="expMonth"
																placeholder="MM" required data-stripe="exp_month" />
														</div>
														<div class="col-xs-6 col-lg-6 pl-ziro">
															<input type="text" class="form-control" name="expYear"
																placeholder="YY" required data-stripe="exp_year" />
														</div>
													</div>
												</div>
												<div class="col-xs-5 col-md-5 pull-right">
													<div class="form-group">
														<label for="cvCode">CV CODE</label> <input type="password"
															class="form-control" name="cvCode" placeholder="CV"
															required data-stripe="cvc" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12">
													<div class="form-group">
														<label for="couponCode">COUPON CODE</label> <input
															type="text" class="form-control" name="couponCode" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12">
													<button class="btn btn-success btn-lg btn-block"
														type="submit">Place Order</button>
												</div>
											</div>
											<div class="row" style="display: none;">
												<div class="col-xs-12">
													<p class="payment-errors"></p>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				<!-- </div>
				<div class="col-sm-4"></div> -->
			</div>
			<dir class="col-sm-3"></dir>
		</div>
	</div>
	<br>

	<footer class="container-fluid text-center">
	<p>Footer Text</p>
	</footer>

</body>

</html>