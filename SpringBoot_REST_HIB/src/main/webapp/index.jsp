<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<title>index</title>
<style type="text/css">
table, td, th {
	border: 1px solid black;
	font-family: verdana;
}

table {
	border-collapse: collapse;
	width: 100%;
	height: 100%;
}

th {
	height: 50px;
	text-align: left;
}

td {
	padding: 3px;
	width: 50%;
}

#divData {
	height: 730px;
	overflow: scroll;
}

div.container {
	width: 100%;
	border: 1px solid gray;
}

header, footer {
	padding: 1em;
	color: white;
	background-color: black;
	clear: left;
	text-align: center;
}

nav {
	float: left;
	max-width: 160px;
	margin: 0;
	padding: 1em;
}

nav ul {
	list-style-type: none;
	padding: 0;
}

nav ul a {
	text-decoration: none;
}

article {
	margin-left: 170px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}
</style>

<script type="text/javascript">
 $(document).ready(function () {

$('#date').datepicker({
    format: "yyyy-mm-dd"
}); 

$('.datepicker').datepicker({
    startDate: '-3d'
});

});

	function selectPage() {
		/* var select = "<div id='myDiv'><body><input type='text' name='empid' id='empid'><br><button onclick='getEmployeeById()'>Get Employee</button></body></div><p id='header'></p>"; */
		var select = "<div id='myDiv'><body><table><tr><td>Employeee Id</td><td><input type='text' name='empid' id='empid'></td></tr><tr><td></td><td><button onclick='getEmployeeById()'>Get Employee</button></td></tr></table></body></div><p id='header'></p>";
		document.getElementById("para1").innerHTML = select;

	}

	function getEmployeeById() {
		var emp;
		var string;
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:8080/emp/"
				+ document.getElementById("empid").value;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 500)
				document.getElementById("header").innerHTML = "Employee Not Exist";
			if (xhr.readyState == 4 && xhr.status == 200) {
				emp = JSON.parse(xhr.responseText);
					string = "<table><tr><td>Emp ID</td><td>" + emp.empid
							+ "</td></tr><tr><td>First Name</td><td>"
							+ emp.fname
							+ "</td></tr><tr><td>Last Name</td><td>"
							+ emp.lname + "</td></tr><tr><td>Dob</td><td>"
							+ emp.dob + "</td></tr><tr><td>Address</td><td>"
							+ emp.address + "</td></tr><tr><td>Mobile</td><td>"
							+ emp.mobile + "</td></tr></table>";
					document.getElementById("header").innerHTML = string;
			}
		}
		xhr.send();

	}

	function deletePage() {

		var select = "<div id='myDiv'><body><table><tr><td>Employeee Id</td><td><input type='text' name='empid' id='empid'></td></tr><tr><td></td><td><button onclick='deleteEmployeeById()'>Delete Employee</button></td></tr></table></body></div><p id='header'></p><br><p id='header2'></p>";
		document.getElementById("para1").innerHTML = select;
	}

	function deleteEmployeeById() {
		alert("delete");
		var chk = confirm("Do you want to delete record?");
		if (chk == true) {
			var emp;
			xhr = new XMLHttpRequest();
			var url = "http://192.168.50.100:8080/emp/"
					+ document.getElementById("empid").value;
			xhr.open("DELETE", url, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					emp = JSON.parse(xhr.responseText);

					if (emp.empid == 0)
						document.getElementById("header2").innerHTML = "Employee Not Exist";
					else {
						string = "<table><tr><td>Emp ID</td><td>" + emp.empid
								+ "</td></tr><tr><td>First Name</td><td>"
								+ emp.fname
								+ "</td></tr><tr><td>Last Name</td><td>"
								+ emp.lname + "</td></tr><tr><td>Dob</td><td>"
								+ emp.dob
								+ "</td></tr><tr><td>Address</td><td>"
								+ emp.address
								+ "</td></tr><tr><td>Mobile</td><td>"
								+ emp.mobile + "</td></tr></table>";
						document.getElementById("header").innerHTML = string;
						document.getElementById("header2").innerHTML = "Empployee deleted";
					}
				}
			}
			xhr.send();
		}

	}
	function insertPage() {
		var select = "<div id='myDiv'><body><table><tr><td>Emp Id</td><td><input type='text' name='empid' id='empid' ></td> </tr>"
				+ "<tr><td>First Name</td><td><input type='text' name='fname' id='fname'></td> </tr><tr><td>Last Name</td><td><input type='text' name='lname' id='lname'></td> </tr>"
				+ "<tr><td>DOB</td><td><input type='text' name='dob' id='dob'></td> </tr>"
				+ "<tr><td>Address</td><td><input type='text' name='address' id='address'></td> </tr>"
				+ "<tr><td>Mobile</td><td><input type='text' name='mobile' id='mobile'></td> </tr>"
				+ "<tr><td></td><td><button onclick='addEmployee()'>Add Employee</button></td></tr></table></div><p id='header'></p>";
		document.getElementById("para1").innerHTML = select;
	}

	function addEmployee() {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:8080/emp";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
			}
		}
		var data = JSON.stringify({
			"empid" : document.getElementById("empid").value,
			"fname" : document.getElementById("fname").value,
			"lname" : document.getElementById("lname").value,
			"dob" : document.getElementById("dob").value,
			"address" : document.getElementById("address").value,
			"mobile" : document.getElementById("mobile").value
		});
		xhr.send(data);
		document.getElementById("header").innerHTML = "Empployee Inserted";
	}
	
	

	function selectAllPage() {
		var emp;
		var string = "";
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:8080/emps";
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				emp = JSON.parse(xhr.responseText);
				/* alert(emp[3].fname); */

				for (var i = 0; i < emp.length; i++) {
					string = string + "<table><tr><td>Emp ID</td><td>"
							+ emp[i].empid
							+ "</td></tr><tr><td>First Name</td><td>"
							+ emp[i].fname
							+ "</td></tr><tr><td>Last Name</td><td>"
							+ emp[i].lname + "</td></tr><tr><td>Dob</td><td>"
							+ emp[i].dob + "</td></tr><tr><td>Address</td><td>"
							+ emp[i].address
							+ "</td></tr><tr><td>Mobile</td><td>"
							+ emp[i].mobile + "</td></tr></table><br>";
					document.getElementById("para1").innerHTML = string;
				}
			}
		}
		xhr.send();
	}

	function updatePage() {
		var select = "<div id='myDiv'><body><table><tr><td>Employeee Id</td><td><input type='text' name='empid' id='empid'></td></tr><tr><td></td><td><button onclick='getEmployeeByIdForUpdate()'>Get Employee For Update</button></td></tr></table></body></div><p id='header'></p>";
		document.getElementById("para1").innerHTML = select;
	}

	function getEmployeeByIdForUpdate() {

		var emp;
		var string = "";
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:8080/emp/"
				+ document.getElementById("empid").value;
		xhr.open("GET", url, true);

		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 500)
				document.getElementById("header").innerHTML = "Employee Not Exist";
			if (xhr.readyState == 4 && xhr.status == 200) {
				emp = JSON.parse(xhr.responseText);
					string = "<div id='myDiv'><body><table><tr><td>Emp Id</td><td><input type='text' name='empid' id='id' ></td> </tr>"
							+ "<tr><td>First Name</td><td><input type='text' name='fname' id='fname'></td> </tr><tr><td>Last Name</td><td><input type='text' name='lname' id='lname'></td> </tr>"
							+ "<tr><td>DOB</td><td><input type='text' name='dob' id='dob'></td> </tr>"
							+ "<tr><td>Address</td><td><input type='text' name='address' id='address'></td> </tr>"
							+ "<tr><td>Mobile</td><td><input type='text' name='mobile' id='mobile'></td> </tr>"
							+ "<tr><td></td><td><button onclick='UpdateEmployee()'>Update Employee Employee</button></td></tr></table></div><p id='header'></p>";
					document.getElementById("header").innerHTML = string;
					document.getElementById("id").value = emp.empid;
					document.getElementById("fname").value = emp.fname;
					document.getElementById("lname").value = emp.lname;
					document.getElementById("dob").value = emp.dob;
					document.getElementById("address").value = emp.address;
					document.getElementById("mobile").value = emp.mobile;
			}
		}

		//alert(string);
		xhr.send();

	}

	function UpdateEmployee() {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:8080/emp/"
				+ document.getElementById("empid").value;
		xhr.open("PUT", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				/* var json = JSON.parse(xhr.responseText);
				console.log(json.email + ", " + json.password) */
			}
		}
		var data = JSON.stringify({
			"empid" : document.getElementById("id").value,
			"fname" : document.getElementById("fname").value,
			"lname" : document.getElementById("lname").value,
			"dob" : document.getElementById("dob").value,
			"address" : document.getElementById("address").value,
			"mobile" : document.getElementById("mobile").value
		});
		xhr.send(data);
		document.getElementById("header").innerHTML = "Empployee Updated";
	}
</script>
</head>
<body>

	<div id="#div1" class="container">

		<header>
		<h1>Employee Form</h1>
		</header>

		<nav>
		<ul>
			<li><a href="#" onclick="selectPage()">Select</a></li>
			<li><a href="#" onclick="deletePage()">Delete</a></li>
			<li><a href="#" onclick="insertPage()">Insert</a></li>
			<li><a href="#" onclick="updatePage()">Update</a></li>
			<li><a href="#" onclick="selectAllPage()">All Employee</a></li>
		</ul>
		</nav>

		<article>
		<div id="divData">
			<p id="para1"></p>
			<p id="para2"></p>
			<input id="date">
			
		</div>
		</article>

		<footer>Copyright © smartek21.com</footer>

	</div>
</body>
</html>