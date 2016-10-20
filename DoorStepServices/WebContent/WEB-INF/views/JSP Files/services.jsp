<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 585px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Services</title>
</head>
<body>
<body>

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
		<div class="collapse navbar-collapse navbar-right" id="myNavbar">
			<ul class="nav navbar-nav ">
				<li class="active"><a href="menu">Home</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid text-center">
		<div class="row content ">
			<div class="col-sm-2 sidenav text-left">
				<p>
					<a href="serviceCategory"> Service Category</a>
				</p>
				<p>
					<a href="services"> Services</a>
				</p>
				<p>
					<a href="city"> City</a>
				</p>
				<p>
					<a href="location"> Location</a>
				</p>
				<p>
					<a href="vendor"> Vendor</a>
				</p>
				<p>
					<a href="users"> Users</a>
				</p>
				<p>
					<a href="show-bookings">Show Bookings</a>
				</p>
			</div>
			<div class="col-sm-10 text-center">
				<div class="row">
					<div class="col-sm-12 text-center">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<h1>Add Services from respective category</h1>
							<form method="POST" class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-sm-4" for="service">Select
										Service Category:</label>
									<div class="col-sm-6">
										<select name="serviceCategoryId" class="form-control">
											<c:forEach var="service" items="${requestScope.servicelist}">
												<option value="${service.serviceCategoryId}">${service.serviceCategoryName}</option>
											</c:forEach>
										</select><br>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="service">Enter
										Service Name:</label>
									<div class="col-sm-6">
										<input type="text" name="serviceName" class="form-control"
											placeholder="Service Name" /><br>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-4" for="service">Enter
										Service Rate:</label>
									<div class="col-sm-6">
										<input type="text" name="rate" class="form-control"
											placeholder="Service Rate" /><br>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-2 "></div>
									<div class="col-sm-8 ">
										<input type="submit" value="ADD Service"
											class="btn btn-primary" /><br>
									</div>
									<div class="col-sm-2 "></div>
								</div>

								<br>
								<div class="row">
									<div class="col-sm-2 "></div>
									<div class="col-sm-8 ">
										<table class="table table-bordered">
											<thead style="text-align: center;">
												<tr>
													<td>Serial</td>
													<td>Service Name</td>
													<td>Service Rate</td>
													<td>Categorye</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="service"
													items="${requestScope.servicesDetailList}">
													<tr>
														<td>${service.serviceId}</td>
														<td>${service.serviceName}</td>
														<td>${service.rate}</td>
														<td>${service.serviceCategory.serviceCategoryName}</td>

													</tr>
												</c:forEach>
											</tbody>
										</table>

									</div>
									<div class="col-sm-2 "></div>
								</div>



							</form>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>

</body>
</html>