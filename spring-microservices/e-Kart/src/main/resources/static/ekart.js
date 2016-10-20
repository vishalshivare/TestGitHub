$( document ).ready(function() {
	login();
	$("#userList").hide();
	$("#adminList").hide();
});

function formateDate(dob) {
	var date=new Date(dob);
	var month;
	if((date.getMonth() + 1)<10)
	{
		month="0"+(date.getMonth() + 1);
		return date.getFullYear()+ "-" +month + "-" +  date.getDate();
	}
	return date.getFullYear()+ "-" +(date.getMonth() + 1) + "-" +  date.getDate();
}

function login() {
	var logIn="<h3 class='text-center'>Log In</h3><br><table class='table table-bordered'>"+
	"<tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid'></td></tr>"+
	"<tr><td>Password</td><td><input type='password' class='form-control' name='password' id='password'></td></tr>"+
	"<tr><td></td><td><button name='login' class='btn btn-success' onclick='validateUser()'>Log In</button></td></tr></table>";
	document.getElementById("para1").innerHTML = logIn;
	$("#userList").hide();
	$("#adminList").hide();
}

function dateGenerator(){
	$('#dob').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0",
		dateFormat: "yy-mm-dd"		
	});
}

$( document ).ready(function() {
	$(".link").click(function(){
		var id = $(this).attr("id");
		var element = "#secret_" + id;
		alert(element);
	})
});

function isValidLogin() {
	var userId = document.getElementById("userid").value;
	var password = document.getElementById("password").value;
	if (userId == null || userId == "") {
		alert("User Id must be filled out");
		return false;
	}
	if (password == null || password == "") {
		alert("Password must be filled out");
		return false;
	}
	return true;
}

function validateUser() {
	if(isValidLogin()){
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/login/"+
		document.getElementById("userid").value+"/"+
		document.getElementById("password").value;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("para2").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 500)
			{
				document.getElementById("para2").innerHTML = "Incorrect User Id";
			}
			if (xhr.readyState == 4 && xhr.status == 200) {
				var user = JSON.parse(xhr.responseText);
				if(user.status=='Success')
				{
					if(user.role=="admin"){
						document.getElementById("para1").innerHTML = "";
						document.getElementById("para2").innerHTML = "";
						$("#userRegistration").hide();
						$("#adminList").show();
						document.getElementById("adminName").innerHTML = "Hi,"+user.userName;
						document.getElementById("userId").value = user.userId;
					}
					else{
						document.getElementById("para2").innerHTML = "";
						$("#userRegistration").hide();
						$("#userList").show();
						document.getElementById("userName").innerHTML = "Hi,"+user.userName;
						document.getElementById("userId").value = user.userId;
						getProductList();
					}
				}
				else if(user.status=='CorrectPassword')
					document.getElementById("para2").innerHTML = "Please Enter Correct Password";
				else
					document.getElementById("para2").innerHTML = "Incorrect User Id";
			} 
		}
		var data = JSON.stringify({
			"userId" : document.getElementById("userid").value,
			"password" : document.getElementById("password").value
		});
		xhr.send(data);
	}
}

function addToCart(id) {
	var quantity=document.getElementById("quantity"+id).value
	if(quantity=="" || quantity==null)
	{
		alert("Quantity can not be blank");
	} 
	else if(quantity<0)
	{
		alert("Minimum Quantity must be 1");
	}
	else{
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/addToCart/"+id+"/"+quantity;
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert("product Added");
			}
		}
		xhr.send();  
	}
}

function getProductList() {
	var product;
	var string = "<h3 class='text-center'>Product List</h3><br><table class='table table-bordered' id='pTable'><thead><tr><th>Product Id</th><th>Product Name</th><th>Price</th><th>Brand</th><th>Quantity</th><th></th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/getProducts";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;

		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			product = JSON.parse(xhr.responseText);
			for (var i = 0; i < product.length; i++) {
				string = string + "<tr><td>" + product[i].productId + "</td><td>"
				+ product[i].pname + "</td><td>" + product[i].price
				+ "</td><td>" + product[i].brand
				+ "</td><td><input type='number' class='form-control' min=1 max=3 name='quantity' id='quantity"+product[i].productId+"' value='1'></td><td><a class='link' id='"+
				product[i].productId
				+"' onclick='addToCart("+product[i].productId+")''>Add To Cart</a></td> </tr>";
			}
			string = string + "</tbody></table><br><a onclick='showCart()'>Show Cart</a></li>";
			document.getElementById("para1").innerHTML = string;
		}
	}
	xhr.send();
}

function showCart() {
	var string = "<h3 class='text-center'>Cart</h3><br><table class='table table-bordered'><thead><tr><th>Order Id</th><th>Order Date</th><th>Product Id</th>" +
	"<th>Product Name</th><th>Brand</th><th>Price</th><th>Quantity</th><th>Amount</th><th></th></tr>" +
	"</thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/showCart"
		xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			var orderlist = JSON.parse(xhr.responseText);
			for(var i = 0; i < orderlist.length; i++)
			{
				string = string + "<tr><td>" + orderlist[i].orderId + "</td><td>"
				+ formateDate(orderlist[i].orderDate) + "</td><td>" + orderlist[i].productId
				+ "</td><td>" + orderlist[i].productName+ "</td><td>" + orderlist[i].productBrand
				+ "</td><td>" + orderlist[i].productPrice+ "</td><td>" + orderlist[i].quantity
				+ "</td><td>" + orderlist[i].amount+"</td><td><a class='link' id='"+
				orderlist[i].orderId
				+"' onclick='orderNow("+orderlist[i].orderId+")''>Order Now</a></td></tr>";
			}
			string = string + "</tbody></table><br><a onclick='getProductList()'>Continue Shopping</a>";
			document.getElementById("para1").innerHTML = string;
		} 
	}
	xhr.send();
}
function orderNow(orderId) {
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/orderNow/"+orderId;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			alert("Your order is placed successfully.");
			showCart();
		}
	}
	xhr.send(); 
}

function showHistory() {
	var string = "<h3 class='text-center'>History</h3><br><table class='table table-bordered'><thead><tr><th>Order Id</th><th>Order Date</th><th>Product Id</th>" +
	"<th>Product Name</th><th>Brand</th><th>Price</th><th>Quantity</th><th>Amount</th></tr>" +
	"</thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/showHistory"
		xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			var orderlist = JSON.parse(xhr.responseText);
			for(var i = 0; i < orderlist.length; i++)
			{
				string = string + "<tr><td>" + orderlist[i].orderId + "</td><td>"
				+ formateDate(orderlist[i].orderDate) + "</td><td>" + orderlist[i].productId
				+ "</td><td>" + orderlist[i].productName+ "</td><td>" + orderlist[i].productBrand
				+ "</td><td>" + orderlist[i].productPrice+ "</td><td>" + orderlist[i].quantity
				+ "</td><td>" + orderlist[i].amount+"</td></tr>";
			}
			string = string + "</tbody></table><br><a onclick='getProductList()'>Continue Shopping</a>";
			document.getElementById("para1").innerHTML = string;
		} 
	}
	xhr.send();
}

/*-----------------------------------------------product script---------------------------------------------*/
function selectProductPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Get Product Details</h3><br><table class='table table-bordered'><tr><td>Product Id</td><td><input type='text' class='form-control' name='productid' id='productid' required></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getProductById()'>Get Product</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function isValidateProductInsert(){
	var pname = document.getElementById("pname").value;
	var price = document.getElementById("price").value;
	var brand = document.getElementById("brand").value;
	if (pname == null || pname == "") {
		alert("Product Name must be filled out");
		return false;
	}
	if (price == null || price == "") {
		alert("Price must be filled out");
		return false;
	}
	if (brand == null || brand == "") {
		alert("Brand must be filled out");
		return false;
	}
	return true;
}

function isValidateProductUpdate() {
	var productid = document.getElementById("id").value;
	var pname = document.getElementById("pname").value;
	var price = document.getElementById("price").value;
	var brand = document.getElementById("brand").value;
	if (pname == null || pname == "") {
		alert("Product Name must be filled out");
		return false;
	}
	if (price == null || price == "") {
		alert("Price must be filled out");
		return false;
	}
	if (brand == null || brand == "") {
		alert("Brand must be filled out");
		return false;
	}
	return true;
}



function getProductById() {
	var x = document.getElementById("productid").value;
	if (x == null || x == "")
		alert("Product Id must be filled out");
	else if (isNaN(x))
		alert("Product Id must be numeric");
	else {
		var product;
		var string;
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/products/"
			+ document.getElementById("productid").value;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
			{
				document.getElementById("header").innerHTML = "Product Not Exist";
			}
			if (xhr.readyState == 4 && xhr.status == 500)
			{
				document.getElementById("header").innerHTML = "Product Not Exist";
			}
			if (xhr.readyState == 4 && xhr.status == 200) {
				product = JSON.parse(xhr.responseText);
				string = "<table class='table table-bordered'><tr><td>Product ID</td><td>"+ product.productId
				+ "</td></tr><tr><td>Product Name</td><td>"+ product.pname
				+ "</td></tr><tr><td>Price</td><td>"+ product.price
				+ "</td></tr><tr><td>Brand</td><td>"+ product.brand
				+ "</td></tr></table>";
				document.getElementById("header").innerHTML = string;
			}
		}
		xhr.send();
	}
}

function deleteProductPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Delete Product</h3><br><table class='table table-bordered'><tr><td>Product Id</td><td><input type='text' class='form-control' name='productid' id='productid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='deleteProductById()'>Delete Product</button></td></tr></table></body></div><p id='header'></p><br><p id='header2'></p>";
	document.getElementById("para1").innerHTML = select;
}

function deleteProductById() {
	var x = document.getElementById("productid").value;
	if (x == null || x == "")
		alert("Product Id must be filled out");
	else if (isNaN(x))
		alert("Product Id must be numeric");
	else {
		var chk = confirm("Do you want to delete record?");
		if (chk == true) {
			var product;
			xhr = new XMLHttpRequest();
			var url = "http://192.168.50.100:9094/products/"
				+ document.getElementById("productid").value;
			xhr.open("DELETE", url, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 408)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if(xhr.readyState == 4 && xhr.status == 422)
					document.getElementById("header2").innerHTML = xhr.responseText;
				if (xhr.readyState == 4 && xhr.status == 0)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if (xhr.readyState == 4 && xhr.status == 404) {
					document.getElementById("header").innerHTML = "Product Not Exist";
					document.getElementById("header2").innerHTML = "";
				}
				if (xhr.readyState == 4 && xhr.status == 500) {
					document.getElementById("header").innerHTML = "Product Not Exist";
					document.getElementById("header2").innerHTML = "";
				}
				if (xhr.readyState == 4 && xhr.status == 200) {
					product = JSON.parse(xhr.responseText);
					/* if (product.productid == 0)
							document.getElementById("header2").innerHTML = "Product Not Exist";
						else { */
					string = "<table class='table table-bordered'><tr><td>Product ID</td><td>"+ product.productId
					+ "</td></tr><tr><td>Product Name</td><td>"+ product.pname
					+ "</td></tr><tr><td>Price</td><td>"+ product.price
					+ "</td></tr><tr><td>Brand</td><td>"+ product.brand
					+ "</td></tr></table><br><p id='header'></p>";
					document.getElementById("para1").innerHTML = string;
					document.getElementById("header").innerHTML = "Product deleted";
				}
				/* } */
			}
			xhr.send();
		}

	}
}

function insertProductPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Product Registration</h3><br><table class='table table-bordered'>"
		+ "<tr><td>Product Name</td><td><input type='text' class='form-control' name='pname' id='pname'></td> </tr><tr><td>Price</td><td><input type='text' class='form-control' name='price' id='price'></td> </tr>"
		+ "<tr><td>Brand</td><td><input class='form-control' name='brand' id='brand'></td> </tr>"
		+ "<tr><td></td><td><button class='btn btn-default' onclick='addProduct()'>Add Product</button></td></tr></table></div><p id='header'></p><p id='header2'></p>";
	document.getElementById("para1").innerHTML = select;
}

function addProduct() {
	if (isValidateProductInsert()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/products";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("para1").innerHTML = "Product Inserted";
			}
		}

		var data = JSON.stringify({
			"pname" : document.getElementById("pname").value,
			"price" : document.getElementById("price").value,
			"brand" : document.getElementById("brand").value
		});
		xhr.send(data);
	}
}

function selectAllProductPage() {
	var product;
	var string = "<h3 class='text-center'>Product List</h3><br><table class='table table-bordered'><thead><tr><th>Product Id</th><th>Product Name</th><th>Price</th><th>Brand</th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/products";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			product = JSON.parse(xhr.responseText);
			for (var i = 0; i < product.length; i++) {
				string = string + "<tr><td>" + product[i].productId + "</td><td>"
				+ product[i].pname + "</td><td>" + product[i].price
				+ "</td><td>" + product[i].brand
				+ "</td> </tr>";
			}
			string = string + "</tbody></table>";
			document.getElementById("para1").innerHTML = string;

		}
	}
	xhr.send();
}

function updateProductPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Update Product</h3><br><table class='table table-bordered'><tr><td>Product Id</td><td><input type='text' class='form-control' name='productid' id='productid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getProductByIdForUpdate()'>Get Product For Update</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function getProductByIdForUpdate() {
	var x = document.getElementById("productid").value;
	if (x == null || x == "")
		alert("Product Id must be filled out");
	else if (isNaN(x))
		alert("Product Id must be numeric");
	else {
		var product;
		var string = "";
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/products/"
			+ document.getElementById("productid").value;
		xhr.open("GET", url, true);

		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
				document.getElementById("header").innerHTML = "Product Not Exist";
			if (xhr.readyState == 4 && xhr.status == 500)
				document.getElementById("header").innerHTML = "Product Not Exist";
			if (xhr.readyState == 4 && xhr.status == 200) {
				product = JSON.parse(xhr.responseText);
				string = "<div id='myDiv'><body><table class='table table-bordered'><tr><td>Product Id</td><td><input type='text' class='form-control' readonly name='productid' id='id' ></td> </tr>"
					+ "<tr><td>Product Name</td><td><input type='text' class='form-control' name='pname' id='pname'></td> </tr>"
					+"<tr><td>Price</td><td><input type='text' class='form-control' name='price' id='price'></td> </tr>"
					+ "<tr><td>Brand</td><td><input class='form-control' name='brand' id='brand'></td> </tr>"
					+ "<tr><td></td><td><button class='btn btn-default' onclick='UpdateProduct()'>Update Product Product</button></td></tr></table>" +
					"<input type='hidden' class='form-control' name='createddate' id='createddate'><br><input type='hidden' class='form-control' name='status' id='status'>" +
					"<br><input type='hidden' class='form-control' name='lastmodified' id='lastmodified'></div></p>";
				document.getElementById("header").innerHTML = string;
				document.getElementById("id").value = product.productId;
				document.getElementById("pname").value = product.pname;
				document.getElementById("brand").value = product.brand;
				document.getElementById("price").value = product.price;
				document.getElementById("createddate").value = product.createdDate;
				document.getElementById("status").value = product.status;
				document.getElementById("lastmodified").value = product.lastModified;
			}
		}
		xhr.send();
	}
}

function UpdateProduct() {
	if (isValidateProductUpdate()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/products/"
			+ document.getElementById("productid").value;
		xhr.open("PUT", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) 
				document.getElementById("header").innerHTML = "Product Updated";
		}
		var data = JSON.stringify({
			"productId" : document.getElementById("id").value,
			"pname" : document.getElementById("pname").value,
			"price" : document.getElementById("price").value,
			"brand" : document.getElementById("brand").value,
			"createdDate" : document.getElementById("createddate").value,
			"status" : document.getElementById("status").value,
			"lastModified" : document.getElementById("lastmodified").value
		});
		xhr.send(data);
	}
}
/*-----------------------user script--------------------------------*/
function selectUserPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Get User Details</h3><br><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid' required></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getUserById()'>Get User</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function isValidateUserInsert(){
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var dob = document.getElementById("dob").value;
	var address = document.getElementById("address").value;
	var mobile = document.getElementById("mobile").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var rePassword = document.getElementById("rePassword").value;
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
		alert("Email must be filled out");
		return false;
	}
	if (password == null || password == "") {
		alert("Password must be filled out");
		return false;
	}
	if (password.length < 4 || password.length > 8) {
		alert("Password lenght must be 4 to 8 characters.");
		return false;
	}
	if (rePassword == null || rePassword == "") {
		alert("Re-enter Password must be filled out");
		return false;
	}
	if (rePassword != password) {
		alert("Password not matched");
		return false;
	}
	return true;
}

function isValidateUserUpdate() {
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var dob = document.getElementById("dob").value;
	var address = document.getElementById("address").value;
	var mobile = document.getElementById("mobile").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var rePassword = document.getElementById("rePassword").value;
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
	if (password.length < 4 || password.length > 8) {
		alert("Password lenght must be 4 to 8 characters.");
		return false;
	}
	if (rePassword == null || rePassword == "") {
		alert("Re-enter Password must be filled out");
		return false;
	}
	if (rePassword != password) {
		alert("Password not matched");
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
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
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
				string = "<table class='table table-bordered'><tr><td>User ID</td><td>"+ user.userId
				+ "</td></tr><tr><td>First Name</td><td>"+ user.fname
				+ "</td></tr><tr><td>Last Name</td><td>"+ user.lname
				+ "</td></tr><tr><td>Dob</td><td>"+ user.dob
				+ "</td></tr><tr><td>Address</td><td>"+ user.address
				+ "</td></tr><tr><td>Mobile</td><td>"+ user.mobile
				+ "</td></tr><tr><td>Email</td><td>"+ user.email
				+ "</td></tr></table>";
				document.getElementById("header").innerHTML = string;
			}
		}
		xhr.send();
	}
}

function deleteUserPage() {
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
				if (xhr.readyState == 4 && xhr.status == 408)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
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
					string = "<table class='table table-bordered'><tr><td>User ID</td><td>"+ user.userId
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
			}
			xhr.send();
		}

	}
}

function insertUserPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>User Registration</h3><br><table class='table table-bordered'>"
		+ "<tr><td>First Name</td><td><input type='text' class='form-control' name='fname' id='fname'></td> </tr><tr><td>Last Name</td><td><input type='text' class='form-control' name='lname' id='lname'></td> </tr>"
		+ "<tr><td>DOB</td><td><input class='form-control' name='dob' id='dob' readonly></td> </tr>"
		+ "<tr><td>Address</td><td><input type='text' class='form-control' name='address' id='address'></td> </tr>"
		+ "<tr><td>Mobile</td><td><input type='text' class='form-control' name='mobile' id='mobile'></td> </tr>"
		+ "<tr><td>Email Id</td><td><input type='text' class='form-control' name='email' id='email'></td> </tr>"
		+ "<tr><td>Password</td><td><input type='password' class='form-control' name='password' id='password'></td> </tr>"
		+ "<tr><td>Re-enter Password</td><td><input type='password' class='form-control' name='password' id='rePassword'></td> </tr>"
		+ "<tr><td></td><td><button class='btn btn-default' onclick='addUser()'>Add User</button></td></tr></table></div><p id='header'></p><p id='header2'></p>";
	document.getElementById("para1").innerHTML = select;
	dateGenerator();
}

function addUser() {
	if (isValidateUserInsert()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/users";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 500)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) {
				var user = JSON.parse(xhr.responseText);
				document.getElementById("para1").innerHTML = "Registered successfully<br>Your User Id is :"+user.userId
				+"<br>cleck here for <a onclick='login()'>Log In</a>";
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

function selectAllUserPage() {
	var user;
	var string = "<h3 class='text-center'>User List</h3><br><table class='table table-bordered'><thead><tr><th>User Id</th><th>First Name</th><th>Last Name</th><th>DOB</th><th>Address</th><th>Mobile</th><th>Email</th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9091/users";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
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
				+ "</td><td>" +  user[i].email + "</td></tr>";	
			}
			string = string + "</tbody></table>";
			document.getElementById("para1").innerHTML = string;

		}
	}
	xhr.send();
}

function updateUserPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Update User</h3><br><table class='table table-bordered'><tr><td>User Id</td><td><input type='text' class='form-control' name='userid' id='userid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getUserByIdForUpdate()'>Get User For Update</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function getUserByIdForUpdate() {
	var user;
	var string = "";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9094/users/"
		+ document.getElementById("userId").value;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 404)
			document.getElementById("header").innerHTML = "User Not Exist";
		if (xhr.readyState == 4 && xhr.status == 200) {
			user = JSON.parse(xhr.responseText);
			string = "<div id='myDiv'><body><table class='table table-bordered'>"
				+ "<tr><td>First Name</td><td><input type='text' class='form-control' name='fname' id='fname'></td> </tr>"
				+"<tr><td>Last Name</td><td><input type='text' class='form-control' name='lname' id='lname'></td> </tr>"
				+ "<tr><td>DOB</td><td><input class='form-control' name='dob' id='dob' readonly></td> </tr>"
				+ "<tr><td>Address</td><td><input type='text' class='form-control' name='address' id='address'></td> </tr>"
				+ "<tr><td>Mobile</td><td><input type='text' class='form-control' name='mobile' id='mobile'></td> </tr>"
				+ "<tr><td>Email</td><td><input type='text' class='form-control' name='email' id='email'></td> </tr>"
				+ "<tr><td>Password</td><td><input type='password' class='form-control' name='password' id='password'></td> </tr>"
				+ "<tr><td>Re-enter Password</td><td><input type='password' class='form-control' name='rePassword' id='rePassword'></td> </tr>"
				+ "<tr><td></td><td><button class='btn btn-default' onclick='UpdateUser()'>Update User</button></td></tr></table>"
				+"<input type='hidden' class='form-control' name='createddate' id='createddate'><br><input type='hidden' class='form-control' name='status' id='status'>"
				+"<br><input type='hidden' class='form-control' name='lastmodified' id='lastmodified'>" 
				+"<input type='hidden' class='form-control' name='role' id='role'><input type='hidden' class='form-control'name='userid' id='userid'></div></p>";
			document.getElementById("para1").innerHTML = string;
			dateGenerator();
			document.getElementById("userid").value = user.userId;
			document.getElementById("fname").value = user.fname;
			document.getElementById("lname").value = user.lname;
			document.getElementById("dob").value = formateDate(user.dob);
			document.getElementById("address").value = user.address;
			document.getElementById("mobile").value = user.mobile;
			document.getElementById("email").value = user.email;
			document.getElementById("password").value = user.password;
			document.getElementById("rePassword").value = user.password;
			document.getElementById("createddate").value = user.createdDate;
			document.getElementById("status").value = user.status;
			document.getElementById("lastmodified").value = user.lastModified;
			document.getElementById("role").value = user.role;
		}
	}
	xhr.send();
}

function UpdateUser() {
	if (isValidateUserUpdate()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9094/users/"
			+ document.getElementById("userId").value;
		xhr.open("PUT", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 408)
				document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
			if(xhr.readyState == 4 && xhr.status == 500)
				document.getElementById("para2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 200) 
				document.getElementById("para1").innerHTML = "User Updated";
		}
		var data = JSON.stringify({
			"userId" : document.getElementById("userid").value,
			"fname" : document.getElementById("fname").value,
			"lname" : document.getElementById("lname").value,
			"dob" : document.getElementById("dob").value,
			"address" : document.getElementById("address").value,
			"mobile" : document.getElementById("mobile").value,
			"createdDate" : document.getElementById("createddate").value,
			"status" : document.getElementById("status").value,
			"lastModified" : document.getElementById("lastmodified").value,
			"email":document.getElementById("email").value,
			"password":document.getElementById("password").value,
			"role":document.getElementById("role").value
		});
		xhr.send(data);
	}
}

function selectAllOrderPage() {
	var order;
	var string = "<h3 class='text-center'>Order List</h3><br><table class='table table-bordered'><thead><tr><th>Order Id</th><th>User Id</th><th>Order Date</th><th>Product Id</th><th>Quantity</th><th>Amount</th><th>Ordered</th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9093/orders";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 408)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if(xhr.readyState == 4 && xhr.status == 422)
			document.getElementById("header2").innerHTML = xhr.responseText;
		if (xhr.readyState == 4 && xhr.status == 0)
			document.getElementById("para1").innerHTML = "Server is Down Please try After sometime";
		if (xhr.readyState == 4 && xhr.status == 200) {
			order = JSON.parse(xhr.responseText);
			for (var i = 0; i < order.length; i++) {
				string = string + "<tr><td>" + order[i].orderId + "</td><td>"
				+ order[i].userId + "</td><td>" + order[i].orderDate
				+ "</td><td>" + order[i].productId + "</td><td>"
				+ order[i].quantity + "</td><td>" + order[i].amount
				+ "</td><td>" +  order[i].ordered + "</td></tr>";	
			}
			string = string + "</tbody></table>";
			document.getElementById("para1").innerHTML = string;

		}
	}
	xhr.send();
}

function deleteOrderPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Delete Order</h3><br><table class='table table-bordered'><tr><td>Order Id</td><td><input type='text' class='form-control' name='orderid' id='orderid'></td></tr><tr><td></td><td><button class='btn btn-default' onclick='deleteOrderById()'>Delete Order</button></td></tr></table></body></div><p id='header'></p><br><p id='header2'></p>";
	document.getElementById("para1").innerHTML = select;
}

function deleteOrderById() {
	var x = document.getElementById("orderid").value;
	if (x == null || x == "")
		alert("Order Id must be filled out");
	else if (isNaN(x))
		alert("Order Id must be numeric");
	else {
		var chk = confirm("Do you want to delete record?");
		if (chk == true) {
			var order;
			xhr = new XMLHttpRequest();
			var url = "http://192.168.50.100:9093/orders/"
				+ document.getElementById("orderid").value;
			xhr.open("DELETE", url, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 408)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if(xhr.readyState == 4 && xhr.status == 422)
					document.getElementById("header2").innerHTML = xhr.responseText;
				if (xhr.readyState == 4 && xhr.status == 0)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if (xhr.readyState == 4 && xhr.status == 404) {
					document.getElementById("header").innerHTML = "Order Not Exist";
					document.getElementById("header2").innerHTML = "";
				}
				if (xhr.readyState == 4 && xhr.status == 200) {
					order = JSON.parse(xhr.responseText);
					/* if (order.orderid == 0)
							document.getElementById("header2").innerHTML = "Order Not Exist";
						else { */
					string = "<table class='table table-bordered'><tr><td>Order ID</td><td>"+ order.orderid
					+ "</td></tr><tr><td>First Name</td><td>"+ order.userId
					+ "</td></tr><tr><td>Last Name</td><td>"+ order.orderDate
					+ "</td></tr><tr><td>Dob</td><td>"+ order.productId
					+ "</td></tr><tr><td>Address</td><td>"+ order.quantity
					+ "</td></tr><tr><td>Mobile</td><td>"+order.amount
					+ "</td></tr><tr><td>Email</td><td>"+order.ordered
					+ "</td></tr></table><br><p id='header'></p>";
					document.getElementById("para1").innerHTML = string;
					document.getElementById("header").innerHTML = "Order deleted";
				}
			}
			xhr.send();
		}
	}
}