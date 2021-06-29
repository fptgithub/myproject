<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>Login/Register</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="category.html">Login/Register</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
<section class="login_box_area section_gap">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login_box_img">
						<img class="img-fluid" src="/img/login.jpg" alt="">
						<div class="hover">
							<h4>New to our website?</h4>
							<p>There are advances being made in science and technology everyday, and a good example of this is the</p>
							<a class="primary-btn" href="/account/register">Create an Account</a>
						</div>	
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner">
						<h3>forgot to enter</h3>
						
						<form class="row login_form" action="/account/forgotpass" method="post" id="contactForm"  novalidate="novalidate">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control"  name="email" placeholder="email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'email'">
							</div>
							<div class="col-md-12 form-group">
							<p class="text-danger">${ message }</p>
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" value="submit" class="primary-btn">for got</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>