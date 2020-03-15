<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kişi Ekle</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	 <div class="sidenav">
		<a href="main">Ana Sayfa</a> <a href="person">Kişi Ekle</a> <a
			href="class">Sınıf Ekle</a> <a href="classList">Sınıflar</a>
	</div>

	<!-- Page content -->
	<div class="main">
		<div class="container" style="width: 40%; margin-top: 5%;">
			<form action="person" method="POST">
				<label for="first_name">Ad:</label><input type="text"
					name="first_name" required> <label for="last_name">Soyad:</label> <input
					type="text" name="last_name" required/> <label for="phone_number">TelefonNo:</label><input
					type="tel" name="phone_number" placeholder="5xxxxxxxxx" pattern="[5][0-9]{9}" required> <label for="email">Email:</label><input
					type="email" name="email" required> <label for="role">Rol: </label>
				<select name="role" id="role" class="selectpicker" required>
					<c:forEach items="${roles}" var="entry">
						<option value="${entry.key}">${entry.screenDesc}</option>
					</c:forEach>
				</select> <label for="lindyLevel">Lindy Seviye: </label> <select
					name="lindyLevel" id="lindyLevel" class="selectpicker" required>
					<c:forEach items="${levels}" var="entry">
						<option value="${entry.key}">${entry.screenDesc}</option>
					</c:forEach>
				</select> <label for="soloLevel">Solo Seviye: </label> <select
					name="soloLevel" id="soloLevel" class="selectpicker" required>
					<c:forEach items="${levels}" var="entry">
						<option value="${entry.key}">${entry.screenDesc}</option>
					</c:forEach>
				</select> <input type="submit" value="Kişi Ekle" id="submit-btn" />
			</form>
		</div>
	</div>

	<div class="toast <c:out value="${show}" />" data-autohide="true">
		<div class="toast-body">
			<c:out value="${message}" />
		</div>
	</div>


	<script>
		$(document).ready(function() {
			$('.toast').toast({
				animation : false
			});
			$('.toast').toast('show');
		});
	</script>
</body>
</html>