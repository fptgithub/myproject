<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>Confirmation</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="category.html">Confirmation</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<section class="order_details section_gap">
		<div class="container">
			<div class="row order_d_inner">
				<div class="col-lg-8">
					<div class="details_item">
						<h4>Order Info</h4>
						<ul class="list">
							<li><a href="#"><span>Order number</span> : ${ order.orderIdStr }</a></li>
							<li><a href="#"><span>Order Date</span> : ${ order.createDate }</a></li>
							<li><a href="#"><span>Total</span> : $ ${ order.totalPrice }</a></li>
							<li><a href="#"><span>Address</span> : ${ order.address }</a></li>
							<li><a href="#"><span>Phone Number</span> : ${ order.phone }</a></li>
							<c:if test="${ not empty order.description }">
							<li><a href="#"><span>Description</span> : ${ order.description }</a></li>
							</c:if>
							<li><a href="#"><span>State</span> : ${ order.state.name() }</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="order_details_table">
				<h2>Order Details</h2>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Product</th>
								<th scope="col">Quantity</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var = "item" items = "${ orderDetail }">
						<tr>
								<td>
									<p>${ item.product.name }</p>
								</td>
								<td>
									<h5>x ${ item.quantity }</h5>
								</td>
								<td>
									<p>$${ item.product.salePrice * item.quantity }</p>
								</td>
							</tr>
						</c:forEach>
							<tr>
								<td>
									<h4>Total</h4>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<p>$${ order.totalPrice }</p>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>