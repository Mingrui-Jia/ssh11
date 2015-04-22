<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link href="http://getbootstrap.com/examples/starter-template/starter-template.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script type="text/javascript">
	var app = angular.module("DetailApp", []);
	
	app.controller("DetailController", function($scope, $http) {
		var text = ${id };
		var down = "http://it-ebooks-api.info/v1/book/" + text;
		$http.get(down).success(function(response) {
			$scope.details = response;
			console.log(response);
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details</title>
</head>
<body ng-app="DetailApp">
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">ebook4u</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<%
				String str = (String) session.getAttribute("currentUser");
			%>
			<ul class="nav navbar-nav">
				<li class="active"><a
						href="<%=request.getContextPath()%>/book/search"><span class="glyphicon glyphicon-search"></span></a></li>
					<li><a href="<%=request.getContextPath()%>/user/<%=str%>"><span class="glyphicon glyphicon-user"></span></a></li>
					<li><a href="<%=request.getContextPath()%>/contact.jsp"><span class="glyphicon glyphicon-phone-alt"></span></a></li>
				
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	<div ng-controller="DetailController" class=" starter-template">
	<h4>ISBN:</h4>
		<h4 id="id">{{details.ISBN}}</h4><a class="btn btn-primary" href="addFavor/${id }/<%=str%>"><span class="glyphicon glyphicon-thumbs-up"></span></a>
		<div ng-show="details">
			<div class="row">
				<div class="col-sm-3">
					<img ng-src="{{details.Image}}" />
				</div>
				<div class="col-sm-8">
					<h3>{{details.Title}}</h3>
					<h4>
						<span ng-show="details">--</span>{{details.SubTitle}}
					</h4>
					<p>{{details.Description}}</p>
					<h3>
						<a href="{{details.Download}}"><u>Download</u> </a>
					</h3>
				</div>
				
			</div>
		</div>
		
 
 		<h2>People who favor this book:</h2>
		<ul>
			<c:forEach var="user" items="${users}">
				<li><h3><a href="<%=request.getContextPath()%>/user/profile/${user}">${user}</a></h3></li>
			</c:forEach>
		</ul>
 
	
</body>
	</div>
	
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
     <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>






<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script type="text/javascript">
	var app = angular.module("DetailApp", []);
	
	app.controller("DetailController", function($scope, $http) {
		var text = ${id };
		var down = "http://it-ebooks-api.info/v1/book/" + text;
		$http.get(down).success(function(response) {
			$scope.details = response;
			console.log(response);
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details</title>
</head>
<body ng-app="DetailApp">
	<div ng-controller="DetailController" class="container">
	<h4>ISBN:</h4>
		<h4 id="id">${id }</h4>
		<div ng-show="details">
			<h2>Details</h2>
			<div class="row">
				<div class="col-sm-3">
					<img ng-src="{{details.Image}}" />
				</div>
				<div class="col-sm-8">
					<h3>{{details.Title}}</h3>
					<h4>
						<span ng-show="details">--</span>{{details.SubTitle}}
					</h4>
					<p>{{details.Description}}</p>
					<h3>
						<a href="{{details.Download}}"><u>Download</u> </a>
					</h3>
				</div>
				
			</div>
		</div>
	</div>
	<form action="add/${id }" method="post">INPUT
						<input class="btn btn-default" type="submit" value="{{book.ID}}" path="id" name="ID">
					</form>
	<div>
		<%
			String str = (String) session.getAttribute("currentUser");
			out.println(str);
		%>
		<a class="btn btn-danger" href="addFavor/${id }/<%=str%>">add</a>
	</div>
</body>
</html> --%>