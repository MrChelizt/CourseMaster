<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IstanbulLindyHoppers</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/w3css/3/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="style.css" rel="stylesheet" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	<section class="general-info"
		style="margin: auto; width: 50%; margin-top: 5%;">
		<h2 class="title" style="text-align: center;">ISTANBUL LINDY
			HOPPERS</h2>
		<section>
			<img class="mySlides" src="img-1.webp" style="width: 100%"> <img
				class="mySlides" src="img-2.webp" style="width: 100%"> <img
				class="mySlides" src="img-3.webp" style="width: 100%"> <img
				class="mySlides" src="img-4.webp" style="width: 100%"> <img
				class="mySlides" src="img-5.webp" style="width: 100%"> <img
				class="mySlides" src="img-6.webp" style="width: 100%"> <img
				class="mySlides" src="img-7.webp" style="width: 100%">
		</section>
		<p class="about-us" style="text-align: center;">
			<i>Hakkımızda</i>
		</p>
		<p class="about-us-description">
		<p style="text-align: center;">Istanbul Lindy Hoppers, Lindy Hop
			dansını Istanbul'a getirmis ilk dans toplulugudur.</p>
		<p style="text-align: center;">Topluluk, yer aldıgı birçok konser,
			etkinlik ve festivalde katılımcılara ve izleyicilere dans gösterileri
			ile 1920'ler ve 30’ların swing dans kültürünü yasatmaktadır.
			Günümüzde gittikçe popülerlesen ‘electroswing’ müzigi konser ve
			partilerinde de aranan bir topluluk olmustur.</p>
		<p style="text-align: center;">Her ay Taksim, Besiktas, Kadıköy ve
			Mecidiyeköy stüdyolarında açılan dersler ile Lindy Hop, Solo Caz ve
			Tap dansını yaymayı amaclamaktadır.</p>
		<p style="text-align: center;">Lindy Hop ve Solo Caz dansının
			keyfini herkese göstermeyi amaçlayan İstanbul Lindy Hoppers, dünyanın
			en iyi dans festivallerine katılmakta, ulusal ve uluslararası
			egitmenleri ile bir çok yarısmada ülkemizi temsil etmekte ve
			basarılar kazanmaktadır.</p>
		​
		<p style="text-align: center;">Istanbul Lindy Hoppers, aynı
			zamanda "Crossover Istanbul, Lindy Hop & Solo Caz Dans Festivali''ni
			düzenlemekte olup her sene dünyaca ünlü egitmenleri ve dansçıları
			agırlamaktadır.</p>
	</section>

	<footer class="footer"
		style="margin: auto; width: 50px; margin-bottom: 5%;">
		<a href="https://www.facebook.com/istlindyhop/"><i
			class="fa fa-facebook-official"></i></a> <a
			href="https://www.instagram.com/istanbullindyhoppers/"><i
			class="fa fa-instagram"></i></a> <a
			href="https://open.spotify.com/user/21tvhtz7q2cnwps4zz4i3vuma?si=FM1AHv4lTcSXptLF_ELD9Q"><i
			class="fa fa-spotify"></i></a>
	</footer>
	<script>
		var myIndex = 0;
		carousel();

		function carousel() {
			var i;
			var x = document.getElementsByClassName("mySlides");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			x[myIndex - 1].style.display = "block";
			setTimeout(carousel, 3000);
		}
	</script>

</body>
</html>