<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sınıflar</title>
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
		<div class="container" style="margin-top: 5%;">
			<table>
				<tr>
					<th>Sınıf İsmi</th>
					<th>Yapılan Ders Sayısı</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${classList}" var="entry">
					<tr>
						<td>${entry.danceType.screenDesc}
							${entry.classLevel.screenDesc} ${entry.startDate}</td>
						<td>${entry.lessonNumber}</td>
						<td><a type="button" class="btn attendance"
							id="attendanceBtn" href="attendance?classId=${entry.id}">Yoklama
								Al</a></td>
						<td><form action="classList" method="POST">
								<input type="hidden" id="class_id" name="class_id"
									value="${entry.id}" /> <input type="submit" class="btn closure"
									id="passiveBtn" value="Sınıfı Kapat" />
							</form></td>
					</tr>
				</c:forEach>
			</table>
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