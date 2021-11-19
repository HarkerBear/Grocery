<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Second-hand Grocery by Yiyu</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/shg.js"></script>
</head>
<body>
	<c:if test="${param.c!=null}">
		<c:set var="categoryParam" value="&c=${param.c}"></c:set>
	</c:if>
	<c:if test="${param.c==null}">
		<c:set var="categoryParam" value=""></c:set>
	</c:if>

	<div class="header">
		<div id="logoImg">
			<img src="image/logo.png" width=100px height=100px>
		</div>
		<div id="logoName">
			<label>Second-hand Grocery for You~</label>
		</div>
		<div class="menu" onmouseleave="hideMenu()">
			<div class="menuTitle">
				<a href="#" onclick="showMenu()">Menu</a>
			</div>
			<ul id="menuLA">
				<li><a href="page?c=1">Life</a></li>
				<li><a href="page?c=2">Academic</a></li>
				<li><a href="page?">ALL</a></li>
			</ul>
		</div>
		<div class="auth">
			<ul>
				<li><a href="#">Sign in |</a></li>
				<li><a href="#">Sign up</a></li>
			</ul>
		</div>
	</div>

	<div class="content">
		<div class="banner">
			<img src="image/banner.jpg" class="bannerImg">
		</div>
		<div class="goods">
			<ul>
				<c:forEach items="${pageModel.pageData}" var="item">
					<li><img src="${item.preview}" class="itemImg">
						<div class="info">
							<div class="name">
								<h3>${item.name }</h3>
							</div>
							<span class="description">${item.description}</span>
							<div class="cartPrice">
								<div class="price">
									<fmt:formatNumber pattern="$ 0.00" value="${item.price}"></fmt:formatNumber>
								</div>
								<div class="cartBtn">
									<a href="#" class="cart"> <img src="image/cart.svg"
										class="cartImg"></a>
								</div>
							</div>
						</div></li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="page-nav">
		<ul>
			<li><a href="/page?p=1${categoryParam}">First</a></li>
			<li><a
				href="/page?p=${pageModel.hasPrePage?pageModel.page-1:1}${categoryParam}">Previous</a></li>
			<c:forEach begin="1" end="${pageModel.totalPages}" var="pno" step="1">
				<li><span ${pno==pageModel.page?"class='current'":"" }><a href="/page?p=${pno}${categoryParam}">${pno}</a></span>
				</li>
			</c:forEach>
			<li><a
				href="/page?p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages}${categoryParam}">Next</a></li>
			<li><a href="/page?p=${pageModel.totalPages}${categoryParam}">Last</a></li>
		</ul>
	</div>

	<div class="footer">
		<span>Made by Yiyu</span>@second-hand grocery<br> Contact <span>yiyu_tian@berkeley.edu</span>
		for any questions.<br>
		<div class="admin">
			<a href="/management.html">Manager</a>
		</div>
		
	</div>

</body>
</html>