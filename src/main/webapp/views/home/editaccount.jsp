<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<div class="col-lg-6 mx-auto">
					<div class="login_form_inner">
						<h3>Register to enter</h3>
						<form:form class="row login_form" action="/account/update" method="POST" id="contactForm"   modelAttribute="registerObject" >
							<form:input path="username" type = "hidden" value = "aaaaaaaaaaaaa" class="form-control"  placeholder="old Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'old Password'"/>
							<div class="col-md-12 form-group">
								<form:password path="oldPassword" class="form-control"  placeholder="old Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'old Password'"/>
								<form:errors class="text-danger" path="oldPassword"/>
							</div>
							<div class="col-md-12 form-group">
								<form:password path="password" class="form-control"  placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'"/>
								<form:errors class="text-danger" path="password"/>
							</div>
							<div class="col-md-12 form-group">
								<form:password class="form-control" path="passwordAgain" placeholder="Password Again" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password Again'"/>
								<form:errors class="text-danger" path="passwordAgain"/>
							</div>
							<div class="col-md-12 form-group">
								<form:input class="form-control" type="email" path="email"  placeholder="email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'email'"/>
								<form:errors class="text-danger" path="email"/>
							</div>
							<div class="col-md-12 form-group">
								<div class="creat_account">
									<form:radiobuttons path="sex" items="${ registerObject.renderSex() }"/>
									<form:errors class="text-danger" path="sex"/>
								</div>
							</div>
							<div class="col-md-12 form-group">
							<c:if  test="${ not empty message  }">
							<p class="text-danger">${ message }</p>
							</c:if>
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" value="submit" class="primary-btn">update</button>
							</div>
						</form:form>
					</div>
				</div>
	</div>
		</div>
	</section>