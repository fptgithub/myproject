<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="banner-area organic-breadcrumb">
        <div class="container">
            <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                <div class="col-first">
                    <h1>Shopping Cart</h1>
                    <nav class="d-flex align-items-center">
                        <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                        <a href="category.html">Cart</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
   <section class="cart_area">
        <div class="container">
            <div class="cart_inner">
                <div class="table-responsive">
                <p class="text-danger">${ message }</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Product</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                                <th scope="col">Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var = "c" items = "${ products }">
                         <tr id = "product${ c.product.id }">
                                <td>
                                    <div class="media">
                                        <div class="d-flex" >
                                            <img class = "img-fluid" src="${ c.product.poster }" alt="">
                                        </div>
                                        <div class="media-body">
                                        <p class = "">${ c.product.name }</p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <h5>$${ c.product.salePrice }</h5>
                                </td>
                                <td>
                                    <div class="product_count">
                                        <input type="number" name="qty" id="quantity_${ c.product.id }" maxlength="12" value="${ c.quantity }" title="Quantity:" class="input-text qty" disabled>
                                        <a  class="increase items-count " product-id = "${ c.product.id }" href = "/card/add/${ c.product.id }" type="button"><i class="lnr lnr-chevron-up"></i></a>
                                        <a  class="reduced items-count " product-id = "${ c.product.id }" href = "/card/reduce/${ c.product.id }" type="button"><i class="lnr lnr-chevron-down"></i></a>
                                    </div>
                                </td>
                                <td>
                                    <h5 class ="total_price" id = "total${ c.product.id }">$${ c.product.salePrice*c.quantity }</h5>
                                </td>
                                <td>
                                <button product-id="${ c.product.id }" class="btn btn-danger delete_product" type="button">delete&nbsp;&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
</svg></button>
                                </td>
                            </tr>
                        </c:forEach>
                           
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Subtotal</h5>
                                </td>
                                <td>
                                <c:set var="total" value="${0}"/>
								<c:forEach var="c" items="${products}">
    							<c:set var="total" value="${total + (c.quantity*c.product.salePrice)}" />
								</c:forEach>
                                    <h5 class ="sum_total_price">$${ total }</h5>
                                </td>
                            </tr>
                            <tr class="shipping_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Shipping</h5>
                                </td>
                                <td>
                                    <div class="shipping_box">
                                    <form:form action="/order/create" method = "post" id = "oder_form" modelAttribute="orderdto">
                                    <form:input path = "address" placeholder="address"/>
                                    <form:errors class="text-danger" path="address"/>
                                    <form:input path = "phone" placeholder="phone"/>
                                    <form:errors class="text-danger" path="phone"/>
                                    <form:input rows="5" path = "description" cols="10" placeholder="description"/>
                                    <form:errors class="text-danger" path="description"/>
                                    </form:form>
                                    </div>
                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a class="gray_btn" href="/home">Continue Shopping</a>
                                        <a class="primary-btn" id = "oder_btn" href="/oder/create">Buy</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script type="text/javascript">
        $('.items-count').click(function(e){
        	e.preventDefault()
        	$.get($(this).attr('href'),(data)=>{
        		$("#quantity_"+$(this).attr('product-id')).val(data.quantity)
        		$("#total"+$(this).attr('product-id')).html("$"+data.product.salePrice*data.quantity)
        		var total = 0;
        		$('.total_price').each((idx,val)=>{
        			total+=parseFloat(val.innerHTML.substring(1));
        		})
        		$('.sum_total_price').html("$"+total)
        	})
        })
        $(".delete_product").click(function(){
        	console.log("/card/delete/"+$(this).attr("product-id"));
        	$.get("/card/delete/"+$(this).attr("product-id"),(data) => {
        		$("#product"+$(this).attr("product-id")).remove();
        		var total = 0;
        		$('.total_price').each((idx,val)=>{
        			total+=parseFloat(val.innerHTML.substring(1));
        		})
        		$('.sum_total_price').html("$"+total)
        	})
        })
        $('#oder_btn').click(function(e){
        	e.preventDefault();
        	$('#oder_form').submit();
        })
        </script>
    </section>