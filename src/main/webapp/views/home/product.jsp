<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>Product Details Page</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
						<a href="single-product.html">product-details</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
<div class="product_image_area">
		<div class="container">
			<div class="row s_product_inner">
				<div class="col-lg-6">
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
					  <div class="carousel-inner">
					  <c:forEach var = "image" items = "${ product.images }">
					  <div class="carousel-item">
					      <img class="d-block w-100 img-fluid" src="${ image }" alt="First slide">
					    </div>
					  </c:forEach>
					  </div>
					  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true" ></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1">
					<div class="s_product_text">
						<h3>${ product.name }</h3>
						<h2>$${ product.salePrice }</h2>
						<h6 class="l-through"><strike>$${ product.price }</strike></h6>
						<ul class="list">
							<li><a class="active" href="/home/${ product.customtype.slug }/${ product.category.slug }"><span>Category</span> : ${ product.category.name }</a></li>
							<li><a class="active" href="/home/brand/${ product.brand.slug }"><span>Brand</span> : ${ product.brand.name }</a></li>
							<li><a href="#"><span>Availibility</span> : In Stock</a></li>
						</ul>
						<p>${product.description }</p>
						<div class="product_count">
							<label for="qty">Quantity:</label>
							<input type="number" name="qty" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty">
							<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;" class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>
							<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;" class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>
						</div>
						<div class="card_area d-flex align-items-center">
							<a class="primary-btn  add_to_bag"  href="/card/add/${ product.id }" >Add to Cart</a>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<section class="product_description_area">
		<div class="container">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item">
					<a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review" aria-selected="false">Reviews</a>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
					<p>${ product.description }</p>
				</div>
				<div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
					<div class="row">
						<div class="col-lg-6">
							<div class="row total_rate">
								<div class="col-12">
									<div class="box_total">
										<h5>Overall</h5>
										<c:if test="${ evaluateAvg.totalEvaluate == 0 }">
										<h4>5.0</h4>
										</c:if>
										<c:if test="${ evaluateAvg.totalEvaluate != 0}">
										<h4>${ evaluateAvg.formatAvg() }</h4>
										</c:if>
										<h6>(${ evaluateAvg.totalEvaluate == 0?'0':evaluateAvg.totalEvaluate } Reviews)</h6>
									</div>
								</div>
							</div>
							<div class="review_list">
								<c:forEach var = "e" items = "${ evalustes }">
								<div class="review_item review_item_${ e.id }">
									<div class="media">
										<div class="d-flex">
											<img src="${ e.user.avatar }" alt="">
										</div>
										<div class="media-body">
											<h4>${ e.user.username }</h4>
											<i class="fa fa-star" <c:if test="${ 1 > e.star }"> style="color:gray"</c:if>></i>
											<i class="fa fa-star" <c:if test="${ 2 > e.star }"> style="color:gray"</c:if>></i>
											<i class="fa fa-star" <c:if test="${ 3 > e.star }"> style="color:gray"</c:if>></i>
											<i class="fa fa-star" <c:if test="${ 4 > e.star }"> style="color:gray"</c:if>></i>
											<i class="fa fa-star" <c:if test="${ 5 > e.star }"> style="color:gray"</c:if>></i>
									<sec:authorize access="isAuthenticated()">
									<sec:authentication var="username" property="principal.username"/>
									<c:if test="${ e.user.username == username }">
									<a role="button" e-id = ${ e.id } class = "ml-5 delete_evaluate" href = "/evaluate/delete/${ e.id }"><u>delete</u></a>
									</c:if> 
									</sec:authorize>
										</div>
										
									</div>
									<p>${ e.content }</p>
									
								</div>
								</c:forEach>
							</div>
						</div>
						<sec:authorize access="!isAuthenticated()">
						<div class="col-lg-6">
							<div class="review_box">
							<div class="col-md-12 text-center mt-5">
								<a href="/evaluate/add/${ product.id }" class="primary-btn">Login For Evaluate</a>
							</div>
							</div>
							</div>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
						<div class="col-lg-6">
							<div class="review_box">
								<h4>Add a Review</h4>
								<p>Your Rating:</p>
								<ul class="list">
									<li><a href = "" class = "evaluate-star" star = "1" ><i class="fa fa-star"></i></a></li>
									<li><a href = "" class = "evaluate-star" star = "2" ><i class="fa fa-star"></i></a></li>
									<li><a href = "" class = "evaluate-star" star = "3" ><i class="fa fa-star"></i></a></li>
									<li><a href = "" class = "evaluate-star" star = "4" ><i class="fa fa-star"></i></a></li>
									<li><a href = "" class = "evaluate-star" star = "5" ><i class="fa fa-star"></i></a></li>
								</ul>
								<p>Outstanding</p>
								<form class="row contact_form" action="/evaluate/add/${ product.id }" method="post" id="contactForm" novalidate="novalidate">
									<div class="col-md-12">
										<div class="form-group">
											<textarea class="form-control" name="content" id="message" rows="1" placeholder="Review" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Review'"></textarea>
										</div>
									</div>
									<input type = "hidden" value = "5" name = "star" id = "star">
									<div class="col-md-12 text-right">
										<button type="submit" value="submit" class="primary-btn">Submit Now</button>
									</div>
								</form>
							</div>
						</div>
						</sec:authorize>
					</div>
				</div>
			</div>
		</div>
	</section>
<section class="related-product-area section_gap_bottom">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<div class="section-title">
						<h1>Deals of the Week</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
							magna aliqua.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-9">
					<div class="row">
					<c:forEach var = "p" items = "${ dealofweek }">
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img class = "img-fluid" src="${ p.poster }" alt=""></a>
								<div class="desc">
									<a href="#" class="title">${ p.name }</a>
									<div class="price">
										<h6>$${ p.salePrice }</h6>
										<h6 class="l-through">$${ p.price }</h6>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="ctg-right">
						<a href="#" target="_blank">
							<img class="img-fluid d-block mx-auto" src="/img/category/c5.jpg" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$('.carousel-item').first().addClass("active")
		$('.evaluate-star').click(function(e){
			e.preventDefault();
			var star = $(this).attr('star');
			$('#star').val(star);
			$('.evaluate-star').each(function(){
				$(this).attr('star')<=star?$(this).find('i').css("color","#fbd600"):$(this).find('i').css("color","gray");
			})
		})
		$('.delete_evaluate').click(function(e){
			e.preventDefault();
			$.get($(this).attr('href'),(data)=>{
				$('.review_item_'+$(this).attr('e-id')).remove();
			});
		})
		$('.add_to_bag').click(function(e){
			e.preventDefault()
			$.get($(this).attr('href')+'?quantity='+$('#sst').val(),function(data){
				console.log(data)
			})
		})
		</script>
	</section>