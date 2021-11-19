<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Items List</title>
<link href="css/list.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/sweetalert2.js"></script>
<script type="text/javascript">
	function showPreview(previewObj){
		var preview=${previewObj}.attr("data-preview");
		var name=${previewObj}.attr("data-name");
		Swal.fire({
			title: name,
			/* html: "<img src='"+preview+"' style='width:361px;height:240px'/>", */
			html: "<img src='/upload/1.jpg' style='width:361px;height:240px'/>",
			showCloseButton:true,
			showConfirmButton:false,
		})
	}
</script>

</head>
<body>
	<c:if test="${param.c!=null}">
		<c:set var="categoryParam" value="&c=${param.c}"></c:set>
	</c:if>
	<c:if test="${param.c==null}">
		<c:set var="categoryParam" value=""></c:set>
	</c:if>

	<div class="container">
		<fieldset>
			<legend>Items List</legend>
			<div class="add">
				<a href="/management?method=2" class="btn-button">Add</a>
			</div>
			<div class="back">
				<a href="/page" class="btn-button">Back to Index</a>
			</div>
			<table>
				<thead>
					<tr>
						<th>No</th>
						<th>Category</th>
						<th>Name</th>
						<th>Price</th>
						<th class="description">Description</th>
						<th>Operation</th>
					</tr>
				<thead>
					<c:forEach items="${pageModel.pageData}" var="item"
						varStatus="status">
						<tr>
							<td>${status.index+pageModel.pageStartRow+1}</td>
							<td><c:choose>
									<c:when test="${item.category==1}">Life</c:when>
									<c:when test="${item.category==2}">Academic</c:when>
									<c:otherwise>No Type</c:otherwise>
								</c:choose></td>
							<td>${item.name}</td>
							<td><fmt:formatNumber pattern="$0.00" value="${item.price}"></fmt:formatNumber></td>
							<td class="description">${item.description}</td>
							<td>
								<%-- <a class="oplink" data-preview="${item.preview}"
					data-name="${item.name}" href="javascript:void(0)"
					onclick="showPreview(this)">Preview</a> --%> <a class="oplink"
								href="/management?method=4&id=${item.id}">Edit</a> <!-- <a class="oplink" href="#">Delete</a></td> -->
						</tr>
					</c:forEach>
			</table>
			<div class="page-nav">
		<ul>
			<li><a href="/management?p=1&method=1">First</a></li>
			<li><a
				href="/management?p=${pageModel.hasPrePage?pageModel.page-1:1}${categoryParam}&method=1">Previous</a></li>
			<c:forEach begin="1" end="${pageModel.totalPages}" var="pno" step="1">
				<li><span ${pno==pageModel.page?"class='current'":"" }><a
						href="/management?p=${pno}&method=1">${pno}</a></span></li>
			</c:forEach>
			<li><a
				href="/management?p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages}&method=1">Next</a></li>
			<li><a href="/management?p=${pageModel.totalPages}&method=1">Last</a></li>
		</ul>
	</div>
	</fieldset>
	</div>
</body>
</html>