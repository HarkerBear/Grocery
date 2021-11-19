<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Items</title>
<link rel="stylesheet" type="text/css" href="css/create.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript">
	function checkSubmit(){
		var r1=checkEmpty("#name","#errName");
		var r2=checkCategory('#category','#errCategory');
		var r3=checkPrice('#price','#errPrice');
		var r4=checkFile('#preview','#errPreview');
		if(r1 && r2 && r3 && r4){
			return true;
		}else{
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>Add Items</legend>
			<form action="/management?method=3" method="post" autocomplete="off"
				enctype="multipart/form-data" onsubmit="return checkSubmit()">
				<ul class="ulform">
					<li><span>Name:</span><span id="errName"></span> <input
						id="name" name="name" onblur="checkEmpty('#name','#errName')" /><br>
					</li>
					<li><span>Category:</span><span id="errCategory"></span><br> <select id="category"
						name="category"
						onchange="checkCategory('#category','#errCategory')">
							<option value="-1">Please a choose category</option>
							<option value="1">Life</option>
							<option value="2">Academic</option>
					</select></li>
					<li><span>Price:</span><span id="errPrice"></span><br> <input
						id="price" name="price" onblur="checkPrice('#price','#errPrice')" /></li>
					<li><span>Preview:</span><span id="errPreview"></span><br>
						<input id="preview" name="preview" type="file"
						style="padding-left: 0px;" accept="image/*"
						onchange="checkFile('#preview','#errPreview')" /></li>
					<li><span>Description:</span><br> <textarea
							id="description" name="description" /></textarea>
					</li>
					<li>
						<button type="submit" class="bnt-button">Submit</button>
					</li>
				</ul>
			</form>
		</fieldset>
	</div>
</body>
</html>