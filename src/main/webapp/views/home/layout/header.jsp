<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					
					<a class="navbar-brand logo_h" href="/home"><img src="/img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="/home">Home</a></li>
							
							<c:forEach var = "item" items ="${ categoryByCustom }">
							<li class="nav-item submenu dropdown">
							<a  href="/home/${ item.customTypeSlug }" class="nav-link dropdown-toggle" data-hover="dropdown" >${ item.customTypeName }</a>
								<ul class="dropdown-menu my_dropdown-menu">
								<c:forEach var = "c" items = "${ item.categorys }">
								<li class="nav-item"><a class="nav-link" href="/home/${ item.customTypeSlug }/${ c[item.indexSlug.val] }">${ c[item.indexName.val] }</a></li>
								</c:forEach>
								</ul>
							</li>
							</c:forEach>
							<sec:authorize access="!isAuthenticated()">
							<li class="nav-item"><a class="nav-link" href="/account/login">Login</a></li>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
							<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
							<li class="nav-item"><a class="nav-link" href="/account/edit">Edit account</a></li>
							</sec:authorize>
							<li class="nav-item"><a class="nav-link" href="/order/detail">check order</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="nav-item"><a href="/card" class="cart"><span class="ti-bag"></span></a></li>
							<li class="nav-item">
								<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div class="search_input" id="search_input_box">
			<div class="container">
				<form class="d-flex justify-content-between" action="/home/search" method = "get">
					<input type="text" name ="name" class="form-control" id="search_input" placeholder="Search Here">
					<button type="submit" class="btn"></button>
					<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
				</form>
			</div>
		</div>
	</header>
	<!-- End Header Area -->
