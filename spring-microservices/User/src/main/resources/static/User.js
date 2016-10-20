
function dateGenerator(){
	$('#dob').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0",
		dateFormat: "yy-mm-dd"		
	});
}

function selectPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Get User Details</h3><br><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid' required></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getUserById()'>Get User</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function isValidateInsert(){
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var dob = document.getElementById("dob").value;
	var address = document.getElementById("address").value;
	var mobile = document.getElementById("mobile").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	if (fname == null || fname == "") {
		alert("First Name must be filled out");
		return false;
	}
	if (lname == null || lname == "") {
		alert("Last Name must be filled out");
		return false;
	}
	if (dob == null || dob == "") {
		alert("DOB must be filled out");
		return false;
	}
	if (address == null || address == "") {
		alert("Address must be filled out");
		return false;
	}
	if (mobile == null || mobile == "") {
		alert("Mobile No must be filled out");
		return false;
	}
	if (isNaN(mobile)) {
		alert("Mobile No must be numeric");
		return false;
	}
	if (mobile.length != 10) {
		alert("Mobile no must be 10 digit only");
		return false;
	}
	if (email == null || email == "") {
		alert("Email No must be filled out");
		return false;
	}
	if (password == null || password == "") {
		alert("Password No must be filled out");
		return false;
	}
	return true;
}

function isValidateUpdate() {
	var userid = document.getElementById("id").value;
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var dob = document.getElementById("dob").value;
	var address = document.getElementById("address").value;
	var mobile = document.getElementById("mobile").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	if (userid == null || userid == "") {
		alert("User Id must be filled out");
		return false;
	}
	if (isNaN(userid)) {
		alert("User Id must be numeric");
		return false;
	}
	if (fname == null || fname == "") {
		alert("First Name must be filled out");
		return false;
	}
	if (lname == null || lname == "") {
		alert("Last Name must be filled out");
		return false;
	}
	if (dob == null || dob == "") {
		alert("DOB must be filled out");
		return false;
	}
	if (address == null || address == "") {
		alert("Address must be filled out");
		return false;
	}
	if (mobile == null || mobile == "") {
		alert("Mobile No must be filled out");
		return false;
	}
	if (isNaN(mobile)) {
		alert("Mobile No must be numeric");
		return false;
	}
	if (mobile.length != 10) {
		alert("Mobile no must be 10 digit only");
		return false;
	}
	if (email == null || email == "") {
		alert("Email No must be filled out");
		return false;
	}
	if (password == null || password == "") {
		alert("Password No must be filled out");
		return false;
	}
	return true;
}



function getUserById() {
	var x = document.getElementById("userid").value;
	if (x == null || x == "")
		alert("User Id must be filled out");
	else if (isNaN(x))
		alert("User Id must be numeric");
	else {
		var user;
		var string;
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9091/users/"
			+ document.getElementById("userid").value;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
			{
				document.getElementById("header").innerHTML = "User Not Exist";
			}
			if (xhr.readyState == 4 && xhr.status == 200) {
				user = JSON.parse(xhr.responseText);
				string = "<table class='table table-bordered'><tr><td>User ID</td><td>"+ user.userid
				+ "</td></tr><tr><td>First Name</td><td>"+ user.fname
				+ "</td></tr><tr><td>Last Name</td><td>"+ user.lname
				+ "</td></tr><tr><td>Dob</td><td>"+ user.dob
				+ "</td></tr><tr><td>Address</td><td>"+ user.address
				+ "</td></tr><tr><td>Mobile</td><td>"+ user.mobile
				+ "</td></tr><tr><td>Email</td><td>"+ user.email
				+ "</td></tr><tr><td>Password</td><td>"+ user.password
				+ "</td></tr></table>";
				document.getElementById("header").innerHTML = string;
			}
		}
		xhr.send();
	}
}

function deletePage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Delete User</h3><br><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='deleteUserById()'>Delete User</button></td></tr></table></body></div><p id='header'></p><br><p id='header2'></p>";
	document.getElementById("para1").innerHTML = select;
}

function deleteUserById() {
	var x = document.getElementById("userid").value;
	if (x == null || x == "")
		alert("User Id must be filled out");
	else if (isNaN(x))
		alert("User Id must be numeric");
	else {
		var chk = confirm("Do you want to delete record?");
		if (chk == true) {
			var user;
			xhr = new XMLHttpRequest();
			var url = "http://192.168.50.100:9091/users/"
				+ document.getElementById("userid").value;
			xhr.open("DELETE", url, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 422)
					document.getElementById("header2").innerHTML = xhr.responseText;
				if (xhr.readyState == 4 && xhr.status == 0)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if (xhr.readyState == 4 && xhr.status == 404) {
					document.getElementById("header").innerHTML = "User Not Exist";
					document.getElementById("header2").innerHTML = "";
				}
				if (xhr.readyState == 4 && xhr.status == 200) {
					user = JSON.parse(xhr.responseText);
					/* if (user.userid == 0)
							document.getElementById("header2").innerHTML = "User Not Exist";
						else { */
					string = "<table class='table table-bordered'><tr><td>User ID</td><td>"+ user.userid
					+ "</td></tr><tr><td>First Name</td><td>"+ user.fname
					+ "</td></tr><tr><td>Last Name</td><td>"+ user.lname
					+ "</td></tr><tr><td>Dob</td><td>"+ user.dob
					+ "</td></tr><tr><td>Address</td><td>"+ user.address
					+ "</td></tr><tr><td>Mobile</td><td>"+user.mobile
					+ "</td></tr><tr><td>Email</td><td>"+user.email
					+ "</td></tr><tr><td>Password</td><td>"+user.password
					+ "</td></tr></table><br><p id='header'></p>";
					document.getElementById("para1").innerHTML = string;
					document.getElementById("header").innerHTML = "Empployee deleted";
				}
				/* } */
			}
			xhr.send();
		}

	}
}

function insertPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>User Registration</h3><br><table class='table table-bordered'>"
		+ "<tr><td>First Name</td><td><input type='text' class='form-control' name='fname' id='fname'></td> </tr><tr><td>Last Name</td><td><input type='text' class='form-control' name='lname' id='lname'></td> </tr>"
		+ "<tr><td>DOB</td><td><input class='form-control' name='dob' id='dob' readonly></td> </tr>"
		+ "<tr><td>Address</td><td><input type='text' class='form-control' name='address' id='address'></td> </tr>"
		+ "<tr><td>Mobile</td><td><input type='text' class='form-control' name='mobile' id='mobile'></td> </tr>"
		+ "<tr><td>Email Id</td><td><input type='text' class='form-control' name='email' id='email'></td> </tr>"
		+ "<tr><td>Password</td><td><input type='text' class='form-control' name='password' id='password'></td> </tr>"
		+ "<tr><td></td><td><button class='btn btn-default' onclick='addUser()'>Add User</button></td></tr></table></div><p id='header'></p><p id='header2'></p>";document.getElementById("para1").innerHTML = select;
		dateGenerator();
}

function addUser() {
	if (isValidateInsert()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9091/users";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("para1").innerHTML = "Empployee Inserted";
			}
		}

		var data = JSON.stringify({
			"fname" : document.getElementById("fname").value,
			"lname" : document.getElementById("lname").value,
			"dob" : document.getElementById("dob").value,
			"address" : document.getElementById("address").value,
			"mobile" : document.getElementById("mobile").value,
			"email" : document.getElementById("email").value,
			"password" : document.getElementById("password").value
		});
		xhr.send(data);
	}
}

function selectAllPage() {
	var user;
	var string = "<h3 class='text-center'>User List</h3><br><table class='table table-bordered'><thead><tr><th>User Id</th><th>First Name</th><th>Last Name</th><th>DOB</th><th>Address</th><th>Mobile</th><th>Email</th><th>Password</th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9091/users";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			user = JSON.parse(xhr.responseText);
			for (var i = 0; i < user.length; i++) {
				string = string + "<tr><td>" + user[i].userId + "</td><td>"
				+ user[i].fname + "</td><td>" + user[i].lname
				+ "</td><td>" + user[i].dob + "</td><td>"
				+ user[i].address + "</td><td>" + user[i].mobile
				+ "</td><td>" +  user[i].email + "</td><td>"
				+user[i].password 
				+ "</td> </tr>";	
			}
			string = string + "</tbody></table>";
			document.getElementById("para1").innerHTML = string;

		}
	}
	xhr.send();
}

function updatePage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Update User</h3><br><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getUserByIdForUpdate()'>Get User For Update</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function getUserByIdForUpdate() {
	var x = document.getElementById("userid").value;
	if (x == null || x == "")
		alert("User Id must be filled out");
	else if (isNaN(x))
		alert("User Id must be numeric");
	else {
		var user;
		var string = "";
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9091/users/"
			+ document.getElementById("userid").value;
		xhr.open("GET", url, true);

		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
				document.getElementById("header").innerHTML = "User Not Exist";
			if (xhr.readyState == 4 && xhr.status == 200) {
				user = JSON.parse(xhr.responseText);
				string = "<div id='myDiv'><body><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' readonly name='userid' id='id' ></td> </tr>"
					+ "<tr><td>First Name</td><td><input type='text' class='form-control' name='fname' id='fname'></td> </tr>"
					+"<tr><td>Last Name</td><td><input type='text' class='form-control' name='lname' id='lname'></td> </tr>"
					+ "<tr><td>DOB</td><td><input class='form-control' name='dob' id='dob' readonly></td> </tr>"
					+ "<tr><td>Address</td><td><input type='text' class='form-control' name='address' id='address'></td> </tr>"
					+ "<tr><td>Mobile</td><td><input type='text' class='form-control' name='mobile' id='mobile'></td> </tr>"
					+ "<tr><td>Email</td><td><input type='text' class='form-control' name='email' id='email'></td> </tr>"
					+ "<tr><td>Password</td><td><input type='text' class='form-control' name='password' id='password'></td> </tr>"
					+ "<tr><td></td><td><button class='btn btn-default' onclick='UpdateUser()'>Update User User</button></td></tr></table>" +
					"<input type='hidden' class='form-control' name='createddate' id='createddate'><br><input type='hidden' class='form-control' name='status' id='status'>" +
					"<br><input type='hidden' class='form-control' name='lastmodified' id='lastmodified'></div></p>";
				document.getElementById("header").innerHTML = string;
				dateGenerator();
				document.getElementById("id").value = user.userId;
				document.getElementById("fname").value = user.fname;
				document.getElementById("lname").value = user.lname;
				document.getElementById("dob").value = user.dob;
				document.getElementById("address").value = user.address;
				document.getElementById("mobile").value = user.mobile;
				document.getElementById("email").value = user.email;
				document.getElementById("password").value = user.password;
				document.getElementById("createddate").value = user.createdDate;
				document.getElementById("status").value = user.status;
				document.getElementById("lastmodified").value = user.lastModified;
			}
		}
		xhr.send();
	}
}

function UpdateUser() {
	if (isValidateUpdate()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9091/users/"
			+ document.getElementById("userid").value;
		xhr.open("PUT", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("para2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) 
				document.getElementById("header").innerHTML = "User Updated";
		}
		var data = JSON.stringify({
			"userId" : document.getElementById("id").value,
			"fname" : document.getElementById("fname").value,
			"lname" : document.getElementById("lname").value,
			"dob" : document.getElementById("dob").value,
			"address" : document.getElementById("address").value,
			"mobile" : document.getElementById("mobile").value,
			"email" : document.getElementById("email").value,
			"password" : document.getElementById("password").value,
			"createdDate" : document.getElementById("createddate").value,
			"status" : document.getElementById("status").value,
			"lastModified" : document.getElementById("lastmodified").value
		});
		xhr.send(data);
	}
}