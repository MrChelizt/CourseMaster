<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sınıf Yarat</title>
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
		<div class="container" style="width: 40%; margin-top: 5%;">
			<form action="class" method="POST">



				<label for="dance_type">Türü:</label> <select id="dance_type"
					name="dance_type" required>
					<option value>Lütfen Seçiniz</option>
					<c:forEach items="${types}" var="entry">
						<option value="${entry}"
							<c:if test="${entry == danceType}"> selected </c:if>>${entry.screenDesc}</option>
					</c:forEach>
				</select> <label for="class_level">Seviye:</label> <select id="class_level"
					name="class_level" required>
					<option value>Lütfen Seçiniz</option>
					<c:forEach items="${levels}" var="level">
						<option value="${level.key}"
							<c:if test="${level.key == classLevel}"> selected </c:if>>${level.key}</option>
					</c:forEach>
				</select> <label for="datetimepicker1">Başlangıç Tarihi:</label>
				<div class="row">
					<div class="col-sm-6"
						style="max-width: 100%; width: 100%; flex: 0 0 100%;">
						<div class="form-group">
							<div class="input-group date" id="datetimepicker1"
								data-target-input="nearest">
								<input type="text" class="form-control datetimepicker-input"
									data-target="#datetimepicker1" id="datetimepicker1"
									name="datetimepicker1" required/>
								<div class="input-group-append" data-target="#datetimepicker1"
									data-toggle="datetimepicker">
									<div class="input-group-text">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<c:if test="${danceType eq 'LINDY_HOP'}">
					<label for="followers">Follower Ekle:</label>
					<select name="followers" id="followers" class="selectpicker"
						multiple data-live-search="true" required>
						<c:forEach items="${followers}" var="entry">
							<option value="${entry.id}">${entry.firstName}
								${entry.lastName}</option>
						</c:forEach>
					</select>
					<label for="leaders">Leader Ekle:</label>
					<select name="leaders" id="leaders" class="selectpicker" multiple
						data-live-search="true" required>
						<c:forEach items="${leaders}" var="entry">
							<option value="${entry.id}">${entry.firstName}
								${entry.lastName}</option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${danceType eq 'SOLO_CHARLESTON'}">
					<label for="solos">Kişi Ekle:</label>
					<select name="solos" id="solos" class="selectpicker" multiple
						data-live-search="true">
						<c:forEach items="${solo}" var="entry">
							<option value="${entry.id}">${entry.firstName}
								${entry.lastName}</option>
						</c:forEach>
					</select>
				</c:if>
				<input type="submit" value="Sınıf Ekle" id="submit-btn" />
			</form>
		</div>
	</div>

	<div class="toast <c:out value="${show}" />" data-autohide="true">
		<div class="toast-body">
			<c:out value="${message}" />
		</div>
	</div>


	<script>
		$(document)
				.ready(
						function() {
							$('.toast').toast({
								animation : false
							});
							$('.toast').toast('show');

							$.ajaxSetup({
								cache : false
							});

							//TODO: localhost u başka yerden çekmek
							$('#class_level')
									.change(
											function(event) {
												var dance = $(
														"select#dance_type")
														.val();
												var level = $(
														"select#class_level")
														.val();
												window.location.href = "http://localhost:8080/CourseMaster/class?classLevel="
														+ level
														+ "&danceType="
														+ dance;
											});

							$('#dance_type')
									.change(
											function(event) {
												var dance = $(
														"select#dance_type")
														.val();
												var level = $(
														"select#class_level")
														.val();
												window.location.href = "http://localhost:8080/CourseMaster/class?classLevel="
														+ level
														+ "&danceType="
														+ dance;
											});
						});

		$(function() {
			$('#datetimepicker1').datetimepicker({
				weekStart : 1,
				maxViewMode : 2,
				language : "tr",
				todayHighlight : true,
				beforeShowDay : function(date) {
					if (date.getMonth() == (new Date()).getMonth())
						switch (date.getDate()) {
						case 4:
							return {
								tooltip : 'Example tooltip',
								classes : 'active'
							};
						case 8:
							return false;
						case 12:
							return "green";
						}
				}
			});
		});
	</script>
</body>
</html>