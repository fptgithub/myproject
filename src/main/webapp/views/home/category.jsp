<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>Shop Category page</h1>
					<nav class="d-flex align-items-center">
					</nav>
				</div>
			</div>
		</div>
	</section>
<div class="container">
<form action="/home/<c:if test="${ empty categorySlug }">search</c:if>${ customSlug }<c:if test="${ not empty categorySlug }">/${ categorySlug }</c:if>" method = "get" id = "sort_form">
              <c:if test="${ empty categorySlug }">
              <input type = "hidden" name = "name" value = "${ name }">
              </c:if>
              <input type = "hidden" name = "limit" value = "${ pageRequest.limit.orElse(9) }">
              <input type = "hidden" name = "page" value = "${ pageRequest.page.orElse(1) }">
              <c:if test="${ not empty categorySlug }">
              <input type = "hidden" name = "sortBy" value = "${ pageRequest.sortBy.orElse('name') }">
              <input type = "hidden" name = "sortName" value = "${ pageRequest.sortName.orElse('asc') }">
              </c:if>
              </form>
		<div class="row">
			<div class="col-xl-3 col-lg-4 col-md-5">
				<div class="sidebar-categories">
					<div class="head">Browse Categories</div>
					<ul class="main-categories">
					<c:forEach var = "item" items ="${ categoryByCustom }">
						<li class="main-nav-list"><a data-hover="collapse"  href="/home/${ item.customTypeSlug }" aria-expanded="false" aria-controls="fruitsVegetable"><span class="lnr lnr-arrow-right"></span>${ item.customTypeName }<span class="number"></span></a>
							<ul class="collapse" id="fruitsVegetable" data-toggle="collapse" aria-expanded="false" aria-controls="fruitsVegetable">
								<c:forEach var = "c" items = "${ item.categorys }">
								
								<li class="main-nav-list child"><a href="/home/${ item.customTypeSlug }/${ c[item.indexSlug.val] }">${ c[item.indexName.val] }<span class="number"></span></a></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
					</ul>
				</div>
				<div class="sidebar-filter mt-50">
					<div class="top-filter-head">Product Filters</div>
					<div class="common-filter">
						<div class="head">Brands</div>
						<form action="#">
							<ul>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="apple" name="brand"><label for="apple">Apple<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="asus" name="brand"><label for="asus">Asus<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="gionee" name="brand"><label for="gionee">Gionee<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="micromax" name="brand"><label for="micromax">Micromax<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">Samsung<span>(19)</span></label></li>
							</ul>
						</form>
					</div>
					<div class="common-filter">
						<div class="head">Color</div>
						<form action="#">
							<ul>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="black" name="color"><label for="black">Black<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="balckleather" name="color"><label for="balckleather">Black
										Leather<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="blackred" name="color"><label for="blackred">Black
										with red<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color"><label for="gold">Gold<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="spacegrey" name="color"><label for="spacegrey">Spacegrey<span>(19)</span></label></li>
							</ul>
						</form>
					</div>
					<div class="common-filter">
						<div class="head">Price</div>
						<div class="price-range-area">
							<div id="price-range" class="noUi-target noUi-ltr noUi-horizontal"><div class="noUi-base"><div class="noUi-origin" style="left: 10%;"><div class="noUi-handle noUi-handle-lower" data-handle="0" tabindex="0" role="slider" aria-orientation="horizontal" aria-valuemin="0.0" aria-valuemax="50.0" aria-valuenow="10.0" aria-valuetext="500.00" style="z-index: 5;"></div></div><div class="noUi-connect" style="left: 10%; right: 50%;"></div><div class="noUi-origin" style="left: 50%;"><div class="noUi-handle noUi-handle-upper" data-handle="1" tabindex="0" role="slider" aria-orientation="horizontal" aria-valuemin="10.0" aria-valuemax="100.0" aria-valuenow="50.0" aria-valuetext="4000.00" style="z-index: 6;"></div></div></div></div>
							<div class="value-wrapper d-flex">
								<div class="price">Price:</div>
								<span>$</span>
								<div id="lower-value">500.00</div>
								<div class="to">to</div>
								<span>$</span>
								<div id="upper-value">4000.00</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Filter Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting">
						<select style="display: none;">
							<option value="name">name</option>
							<option value="price">price</option>
							<option value="salePrice">sale price</option>
						</select><div class="nice-select" tabindex="0">
						<span class="current" id = "current_sort">${ pageRequest.sortBy.orElse('name') }</span><ul class="list">
						<li data-value="name" class="option option_sort ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('name')?'selected':'' }">name</li>
						<li data-value="price" class="option  option_sort ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('price')?'selected':'' }">price</li>
						<li data-value="salePrice" class="option  option_sort ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('salePrice')?'selected':'' }">sale price</li>
						</ul></div>
					</div>
					<div class="sorting mr-auto">
						<select style="display: none;">
							<option value="9">Show 9</option>
							<option value="12">Show 12</option>
							<option value="15">Show 15</option>
						</select><div class="nice-select" tabindex="0"><span class="current" id = "current_show">Show ${ pageRequest.limit.orElse(9) }</span>
						<ul class="list"><li data-value="9" class="option option_show ${ pageRequest.limit.orElse(9) == 9 ?'selected focus':'' } ">Show 9</li>
						<li data-value="12" class="option option_show ${ pageRequest.limit.orElse(9) == 12 ?'selected focus':'' }">Show 12</li>
						<li data-value="15" class="option option_show ${ pageRequest.limit.orElse(9) == 15 ?'selected focus':'' }">Show 15</li></ul></div>
					</div>
					<div class="pagination">
						<c:if test="${ (products.number-2)>1 }">
						<a role = "button" class=" page_link ">1</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:forEach var = "page" items = "${ pagination }">
						
						<a role = "button" class=" page_link ${ page == products.number ? 'active':'' }">${ page } </a>
						</c:forEach>
						<c:if test="${ (products.number+2)<products.getTotalPages()-1 }">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a role = "button" class=" page_link ">${ products.getTotalPages()-1 }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
					</div>
				</div>
				<!-- End Filter Bar -->
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40 category-list">
					<div class="row">
					<c:forEach var = "p" items = "${ products.content }">
					<!-- single product -->
						<div class="col-lg-4 col-md-6">
							<div class="single-product">
								<img class="img-fluid" src="${ p.poster }" alt="">
								<div class="product-details">
									<h6>${ p.name }</h6>
									<div class="price">
										<h6>$${ p.salePrice }</h6>
										<h6 class="l-through">$${ p.price }</h6>
									</div>
									<div class="prd-bottom">

										<a href="/card/add/${ p.id }" class="social-info add_to_bag">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>
										<a href="" class="social-info">
											<span class="lnr lnr-heart"></span>
											<p class="hover-text">Wishlist</p>
										</a>
										<a href="" class="social-info">
											<span class="lnr lnr-sync"></span>
											<p class="hover-text">compare</p>
										</a>
										<a href="/home/detail/${ p.id }" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
						
					</div>
				</section>
				<!-- End Best Seller -->
				<!-- Start Filter Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting mr-auto">
					</div>
					<div class="pagination">
						<c:if test="${ (products.number-2)>1 }">
						<a role = "button" class=" page_link ">1</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:forEach var = "page" items = "${ pagination }">
						
						<a role = "button" class=" page_link ${ page == products.number ? 'active':'' }">${ page } </a>
						</c:forEach>
						<c:if test="${ (products.number+2)<products.getTotalPages()-1 }">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a role = "button" class=" page_link ">${ products.getTotalPages()-1 }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
					</div>
				</div>
				<!-- End Filter Bar -->
			</div>
		</div>
	</div>
	<!-- Start related-product Area -->
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
		$(".option_show").click(function(){
			$('#current_show').html($(this).html());
			$('.option_show').removeClass(' selected focus');
			$(this).addClass(' selected focus');
			$("input[name = limit]").val($(this).attr('data-value'))
			submit()
		})
		$(".option_sort").click(function(){
			$('#current_sort').html($(this).html());
			$('.option_sort').removeClass(' selected focus');
			$(this).addClass(' selected focus');
			$("input[name = sortBy]").val($(this).attr('data-value'))
			submit()
		})
		$(".page_link").click(function(){
		$("input[name = page]").val($(this).html())
		submit()
		})
		function submit(){
			$('#sort_form').submit();
		}
		$('.add_to_bag').click(function(e){
			e.preventDefault()
			$.get($(this).attr('href'),function(data){
				console.log(data)
			})
		})
		</script>
	</section>
	<!-- End related-product Area -->
