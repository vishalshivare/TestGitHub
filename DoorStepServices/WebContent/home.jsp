<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Door Step Services</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="http://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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

.item {
	height: 650px;
}

#myModal {
	display: none;
}

textarea {
	resize: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var status = $("#status").val();
		if (status == "success") {
			$('#myModal').modal('show');
		}
	});
</script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50">
	<%-- <script src="<c:url value="/resources/js/login.js" />"></script> --%>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#myPage">Door Step Services</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#myPage">HOME</a></li>
				<li><a href="#band">SERVICES</a></li>
				<li><a href="#tour">BOOK A SERVICE</a></li>
				<li><a href="#contact">CONTACT</a></li>
				<li><a href="#" data-toggle="modal" data-target="#myModal">LOG
						IN/REGISTER</a></li>
				<!--   <li><button class="btn1" data-toggle="modal" data-target="#myModal">LOG IN/REGISTER</button></li> -->
				<!--  <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">MORE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Merchandise</a></li>
            <li><a href="#">Extras</a></li>
            <li><a href="#">Media</a></li> 
          </ul>
        </li> -->
				<!--  <li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li> -->
			</ul>
		</div>
	</div>
	</nav>

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<!--  <li data-target="#myCarousel" data-slide-to="2"></li> -->
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="https://dailyartdesign36.files.wordpress.com/2015/05/hammer_nails_wood_hd-wallpaper-77671.jpg"
					alt="New York" width="1200" height="700">
				<!-- <div class="carousel-caption">
					<h3>New York</h3>
					<p>The atmosphere in New York is lorem ipsum.</p>
				</div> -->
			</div>

			<div class="item">
				<img
					src="http://versatile-contracting.com/wp-content/uploads/2015/04/plumbing.jpg"
					alt="Chicago" width="1200" height="700">
				<!-- <div class="carousel-caption">
					<h3>Chicago</h3>
					<p>Thank you, Chicago - A night we won't forget.</p>
				</div> -->
			</div>

			<!--  <div class="item">
        <img src="la.jpg" alt="Los Angeles" width="1200" height="700">
        <div class="carousel-caption">
          <h3>LA</h3>
          <p>Even though the traffic was a mess, we had the best time playing at Venice Beach!</p>
        </div>      
      </div> -->
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<!-- Container (The Band Section) -->
	<div id="band" class="container text-center">
		<h3>DOOR STEP SERVICES</h3>
		<p>
			<em>Our Services</em>
		</p>
		<p>At Door Step Services our only focus is YOU. All our
			professionals are background checked and verified to ensure your
			complete safety. To further strengthen your trust in us, we provide
			all our services with a very convenient holistic approach and we hire
			only the most qualified professionals to ensure high quality
			services, such that your satisfaction levels are always at a 100%.</p>
		<br>
		<div class="row">
			<div class="col-sm-6">
				<p class="text-center">
					<strong>Plumber</strong>
				</p>
				<br> <a href="#demo" data-toggle="collapse"> <img
					src="http://www.glaciertts.com/img/plumbing.jpg"
					class="img-circle person" alt="Random Name" width="255"
					height="255">
				</a>
				<div id="demo" class="collapse">
					<p>Get expert plumbers who follow strict quality standards</p>
					<p>All your service providers are verified through criminal and
						address checks</p>
				</div>
			</div>
			<div class="col-sm-6">
				<p class="text-center">
					<strong>Carpenter</strong>
				</p>
				<br> <a href="#demo2" data-toggle="collapse"> <img
					src="http://www.larsoncarpenter.com/wp-content/uploads/2015/07/house_refurbs_and_carpentry_solihull_top.jpg"
					class="img-circle person" alt="Random Name" width="255"
					height="255">
				</a>
				<div id="demo2" class="collapse">
					<p>Get expert carpenters who follow strict quality standards</p>
					<p>We verify address and check criminal history for all service
						providers</p>
				</div>
			</div>


		</div>
	</div>

	<!-- Container (TOUR Section) -->
	<div id="tour" class="bg-1">
		<div class="container">
			<h3 class="text-center">BOOK SERVICE</h3>
			<p class="text-center">
				SAFE AND RELIABLE HOME SERVICES<br> Book trusted professionals
				for all your home needs.
			</p>
			<!-- <ul class="list-group">
      <li class="list-group-item">September <span class="label label-danger">Sold Out!</span></li>
      <li class="list-group-item">October <span class="label label-danger">Sold Out!</span></li> 
      <li class="list-group-item">November <span class="badge">3</span></li> 
    </ul> -->

			<div class="row text-center">
				<div class="col-sm-2"></div>
				<form action="booking">
					<div class="col-sm-4">
						<div class="thumbnail">
							<img
								src="http://powderriverheating.com/wp-content/uploads/sites/26/2014/03/Plumbing-Faucet-Image.jpg"
								alt="Paris" width="400" height="300">
							<p>
								<strong>BOOK</strong>
							</p>
							<input type="submit" class="btn" value="Plumber" onclick="call()"
								name="serviceCategory" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="thumbnail">
							<!-- https://www.whatsuplife.in/gurgaon/blog/wp-content/uploads/2015/02/c2.jpg -->
							<img
								src="http://www.marcroy.co.uk/wp-content/uploads/2016/03/professional-carpenter.jpg"
								alt="New York" width="400" height="300">
							<p>
								<strong>BOOK</strong>
							</p>
							<input type="submit" class="btn" value="Carpenter"
								onclick="call()" name="serviceCategory" />
						</div>
					</div>
				</form>
				<div class="col-sm-2"></div>
				<!-- <div class="col-sm-4">
        <div class="thumbnail">
          <img src="sanfran.jpg" alt="San Francisco" width="400" height="300">
          <p><strong>San Francisco</strong></p>
          <p>Sunday 29 November 2015</p>
          <button class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button>
        </div>
      </div> -->

			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h4>
							<span class="glyphicon glyphicon-lock"></span> Log In
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" action="login" method="post"
							modelAttribute="loginUser">
							<div class="form-group">
								<label for="psw"><span class="glyphicon glyphicon-user"></span>
									Email</label> <input type="text" class="form-control" name="email"
									id="psw" placeholder="Enter Email">
							</div>
							<div class="form-group">
								<label for="usrname"><span
									class="glyphicon glyphicon-asterisk"></span> Password</label> <input
									type="password" class="form-control" name="password"
									id="usrname" placeholder="Enter Password">
							</div>
							<button type="submit" class="btn btn-block">
								Log In <span class="glyphicon glyphicon-ok"></span>
							</button>
						</form>
					</div>
					<div class="modal-footer">
						<p class=" pull-left">
							<span class="glyphicon glyphicon glyphicon-plus"></span> <a
								href="register">New User</a>
						</p>
						<!-- <button type="submit" class="btn btn-danger btn-default pull-left" >
            <span class="glyphicon glyphicon glyphicon-plus"></span> New User
          </button> -->
						<p>
							<a href="forgot">Forget Password</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="status" value="${requestScope.status }" />
	<!-- Container (Contact Section) -->
	<div id="contact" class="container">
		<h3 class="text-center">Contact</h3>
		<p class="text-center">
			<em>We love our fans!</em>
		</p>

		<div class="row">
			<div class="col-md-4">
				<p>Fan? Drop a note.</p>
				<p>
					<span class="glyphicon glyphicon-map-marker"></span>CDAC-ACTS, Pune
				</p>
				<p>
					<span class="glyphicon glyphicon-phone"></span>Phone: +91
					1515151515
				</p>
				<p>
					<span class="glyphicon glyphicon-envelope"></span>Email:
					admin@admin.com
				</p>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-sm-6 form-group">
						<input class="form-control" id="name" name="name"
							placeholder="Name" type="text" required>
					</div>
					<div class="col-sm-6 form-group">
						<input class="form-control" id="email" name="email"
							placeholder="Email" type="email" required>
					</div>
				</div>
				<textarea class="form-control" id="comments" name="comments"
					placeholder="Comment" rows="5"></textarea>
				<br>
				<div class="row">
					<div class="col-md-12 form-group">
						<button class="btn pull-right" type="submit">Send</button>
					</div>
				</div>
			</div>
		</div>
		<br>
		<!-- s
  <h3 class="text-center">From The Blog</h3>  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Mike</a></li>
    <li><a data-toggle="tab" href="#menu1">Chandler</a></li>
    <li><a data-toggle="tab" href="#menu2">Peter</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h2>Mike Ross, Manager</h2>
      <p>Man, we've been on the road for some time now. Looking forward to lorem ipsum.</p>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h2>Chandler Bing, Guitarist</h2>
      <p>Always a pleasure people! Hope you enjoyed it as much as I did. Could I BE.. any more pleased?</p>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h2>Peter Griffin, Bass player</h2>
      <p>I mean, sometimes I enjoy the show, but other times I enjoy other things.</p>
    </div>
  </div>

e -->
	</div>

	<!-- <div id="googleMap"></div> -->

	<!-- Add Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script>
		var myCenter = new google.maps.LatLng(41.878114, -87.629798);

		function initialize() {
			var mapProp = {
				center : myCenter,
				zoom : 12,
				scrollwheel : false,
				draggable : false,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};

			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);

			var marker = new google.maps.Marker({
				position : myCenter,
			});

			marker.setMap(map);
		}

		google.maps.event.addDomListener(window, 'load', initialize);
	</script>

	<!-- Footer -->
	<footer class="text-center"> <a class="up-arrow"
		href="#myPage" data-toggle="tooltip" title="TO TOP"> <span
		class="glyphicon glyphicon-chevron-up"></span>
	</a> <br>
	<br>
	<p>@2016 CDAC-ACTS</p>
	</footer>

	<script>
		$(document).ready(
				function() {
					// Initialize Tooltip
					$('[data-toggle="tooltip"]').tooltip();

					// Add smooth scrolling to all links in navbar + footer link
					$(".navbar a, footer a[href='#myPage']").on('click',
							function(event) {

								// Make sure this.hash has a value before overriding default behavior
								if (this.hash !== "") {

									// Prevent default anchor click behavior
									event.preventDefault();

									// Store hash
									var hash = this.hash;

									// Using jQuery's animate() method to add smooth page scroll
									// The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
									$('html, body').animate({
										scrollTop : $(hash).offset().top
									}, 900, function() {

										// Add hash (#) to URL when done scrolling (default click behavior)
										window.location.hash = hash;
									});
								} // End if
							});
				})
	</script>

</body>
</html>