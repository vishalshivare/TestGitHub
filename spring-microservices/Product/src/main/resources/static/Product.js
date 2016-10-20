
function dateGenerator(){
	$('#dob').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0",
		dateFormat: "yy-mm-dd"		
	});
}

function selectPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Get Product Details</h3><br><table class='table table-bordered'><tr><td>Product Id</td><td><input type='text' class='form-control' name='productid' id='productid' required></td></tr><tr><td></td><td><button class='btn btn-default' onclick='getProductById()'>Get Product</button></td></tr></table></body></div><p id='header'></p>";
	document.getElementById("para1").innerHTML = select;
}

function isValidateInsert(){
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

function isValidateUpdate() {
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
		var url = "http://192.168.50.100:9092/products/"
			+ document.getElementById("productid").value;
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
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

function deletePage() {
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
			var url = "http://192.168.50.100:9092/products/"
				+ document.getElementById("productid").value;
			xhr.open("DELETE", url, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 422)
					document.getElementById("header2").innerHTML = xhr.responseText;
				if (xhr.readyState == 4 && xhr.status == 0)
					document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
				if (xhr.readyState == 4 && xhr.status == 404) {
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

function insertPage() {
	var select = "<div id='myDiv'><body><h3 class='text-center'>Product Registration</h3><br><table class='table table-bordered'>"
		+ "<tr><td>Product Name</td><td><input type='text' class='form-control' name='pname' id='pname'></td> </tr><tr><td>Price</td><td><input type='text' class='form-control' name='price' id='price'></td> </tr>"
		+ "<tr><td>Brand</td><td><input class='form-control' name='brand' id='brand'></td> </tr>"
		+ "<tr><td></td><td><button class='btn btn-default' onclick='addProduct()'>Add Product</button></td></tr></table></div><p id='header'></p><p id='header2'></p>";
		document.getElementById("para1").innerHTML = select;
		dateGenerator();
}

function addProduct() {
	if (isValidateInsert()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9092/products";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
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

function selectAllPage() {
	var product;
	var string = "<h3 class='text-center'>Product List</h3><br><table class='table table-bordered'><thead><tr><th>Product Id</th><th>Product Name</th><th>Price</th><th>Brand</th></tr></thead><tbody>";
	xhr = new XMLHttpRequest();
	var url = "http://192.168.50.100:9092/products";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
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

function updatePage() {
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
		var url = "http://192.168.50.100:9092/products/"
			+ document.getElementById("productid").value;
		xhr.open("GET", url, true);

		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 422)
				document.getElementById("header2").innerHTML = xhr.responseText;
			if (xhr.readyState == 4 && xhr.status == 0)
				document.getElementById("header").innerHTML = "Server is Down Please try After sometime";
			if (xhr.readyState == 4 && xhr.status == 404)
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
				dateGenerator();
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
	if (isValidateUpdate()) {
		xhr = new XMLHttpRequest();
		var url = "http://192.168.50.100:9092/products/"
			+ document.getElementById("productid").value;
		xhr.open("PUT", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
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