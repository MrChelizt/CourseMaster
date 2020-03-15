<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yoklama Gir</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
	integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4="
	crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="sidenav">
		<a href="main">Ana Sayfa</a> <a href="person">Kişi Ekle</a> <a
			href="class">Sınıf Ekle</a> <a href="classList">Sınıflar</a>
	</div>

	<!-- Page content -->
	<div class="main">
		<div class="container" style="margin-top: 5%; width: 100%">
			<div class="row">Tarih: ${classes.startDate}</div>
			<div class="row">Sınıf Seviyesi: ${classes.classLevel}</div>
			<div class="row">Ders: ${classes.lessonNumber}</div>
			<div class="row" >
				<form action="attendance?classId=${classes.id}" method="POST" style="width:100%; margin-left: 5%; margin-right: 5%">
					<table>
						<tr>
							<th>İsim Soyisim</th>
							<th>Rol</th>
							<th>Katılım Durumu</th>
						</tr>
						<c:forEach items="${classes.classMembers}" var="entry">
							<tr>
								<td>${entry.person.firstName}${entry.person.lastName}</td>
								<td>${entry.role.screenDesc}</td>
								<td><input type="checkbox" name="attend"
									value="${entry.person.id}">Katıldı</td>
							</tr>
						</c:forEach>
					</table>
					<input type="submit" value="Yoklamayı Kaydet" id="submit-btn" style="width: 30%; margin-left:35%;"/>
				</form>
			</div>

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

			$.ajaxSetup({
				cache : false
			});

		});
	</script>
</body>
</html>