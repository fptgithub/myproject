 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="app-content">
<div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <div class="table-responsive">
              <form action="/admin/user/table" method = "get" id = "sort_form">
              <input type = "hidden" name = "limit" value = "${ pageRequest.limit.orElse(10) }">
              <input type = "hidden" name = "page" value = "${ pageRequest.page.orElse(0) }">
              <input type = "hidden" name = "sortBy" value = "${ pageRequest.sortBy.orElse('username') }">
              <input type = "hidden" name = "sortName" value = "${ pageRequest.sortName.orElse('asc') }">
              </form>
                <div id="sampleTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="sampleTable_length"><label>Show <select name="sampleTable_length" aria-controls="sampleTable" id = "limit" class="form-control form-control-sm">
                <option value="10"${ pageRequest.limit.orElse(10)==10?'selected':'' }>10</option>
                <option value="25" ${ pageRequest.limit.orElse(10)==25?'selected':'' }>25</option>
                <option value="50"${ pageRequest.limit.orElse(10)==50?'selected':'' }>50</option>
                <option value="100"${ pageRequest.limit.orElse(10)==100?'selected':'' }>100</option>
                </select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="sampleTable_filter" class="dataTables_filter">
                <form action = "/admin/order/table" method = "get"><label>Search:<input type="text" name = "search" id = "search" class="form-control form-control-sm" placeholder="address" aria-controls="sampleTable"></label></form></div></div></div><div class="row"><div class="col-sm-12"><table class="table table-hover table-bordered dataTable no-footer" id="sampleTable" role="grid" aria-describedby="sampleTable_info">
                  <thead>
                  <tr role="row">
                    <tr role="row">
                    <th sortBy = "orderIdStr" sortName = "${ pageRequest.sortName.orElse('asc') }"  class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('orderIdStr')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }" 
                    tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" >orderIdStr</th>
                    <th sortBy = "address" sortName = "${ pageRequest.sortName.orElse('asc') }" onclick="sort()" class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('address')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
 					tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" >address</th>
                    <th sortBy = "totalPrice" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('totalPrice')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 123.062px;">totalPrice</th>
                    <th sortBy = "state" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('state')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" >state</th>
                    <th sortBy = "description" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('description')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >description</th>
                    <th  sortBy = "createDate" sortName = "${ pageRequest.sortName.orElse('asc') }"  class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('createDate')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >createDate</th>
                     <th  sortBy = "phone" sortName = "${ pageRequest.sortName.orElse('asc') }"  class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('phone')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >phone</th>
                     <th  sortBy = "user" sortName = "${ pageRequest.sortName.orElse('asc') }"  class="sort_btn ${ pageRequest.sortBy.orElse('orderIdStr').equalsIgnoreCase('user')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >user</th>
                    
                    <th 
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >action</th>
                    
                    </tr>
                    
                  </thead>
                  <tbody>
                  <c:forEach var = "o" items = "${ pageableOrder.content }">
                  <tr role="row" class="odd">
                      <td >${ o.orderIdStr }</td>
                      <td>${ o.address }</td>
                      <td>${ o.totalPrice }</td>
                      <td>
                      <select order-id = "${ o.id }" name="state" class="state">
						  <c:forEach var = "ord" items = "${ order }">
						  
						  <option value="${ ord }" <c:if test="${ ord.equals(o.state.name()) }">selected</c:if>>${ ord }</option>
						  </c:forEach>
						</select>
						</td>
                      <td>${ o.description }</td>
                      <td>${ o.createDate }</td>
                      <td>${ o.phone }</td>
                      <td>${ o.user.username }</td>
                      <td><a class="btn btn-primary" href = "/admin/order/${ o.id }">detail</a></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table></div></div>
                <div class="row">
                <div class="col-sm-12 col-md-5">
                <div class="dataTables_info" id="sampleTable_info" role="status" aria-live="polite">Showing ${ (pageableProduct.number-1)*pageableProduct.getSize() } to ${ (pageableProduct.number)*pageableProduct.getSize() } of ${ pageableProduct.getTotalElements() } entries</div>
                </div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="sampleTable_paginate">
                <ul class="pagination">
                <c:if test="${ (pageableProduct.number-2)>1 }">
                <li class="paginate_button page-item "><button aria-controls="sampleTable" data-dt-idx="1" tabindex="0" class="page-link" val = "1">1</button></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </c:if>
                <c:forEach var = "page" items = "${ pagination }">
                <li class="paginate_button page-item  ${ page == pageableProduct.number ? 'active':'' }"><button aria-controls="sampleTable" data-dt-idx="1" tabindex="0" class="page-link" val = "${ page }">${ page }</button></li>
                </c:forEach>
                <c:if test="${ (pageableProduct.number+2)<pageableProduct.getTotalPages()-1 }">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class="paginate_button page-item  "><button aria-controls="sampleTable" data-dt-idx="1" tabindex="0" class="page-link" val = "${ pageableProduct.getTotalPages()-1 }">${ pageableProduct.getTotalPages()-1 }</button></li>
                </c:if>
                </ul></div></div></div></div>
              </div>
            </div>
          </div>
        </div>
      </div>
</main>
<script type="text/javascript">
$(".state").change(function(){
	console.log($(this).find(":selected").val().replace("[","").replace("]",""));
	$.post( "/admin/order/update", { id: $(this).attr("order-id"), state: $(this).find(":selected").val().replace("[","").replace("]","") } );
});

$('#limit').change(function(){
	$("input[name = limit]").val($(this).val())
	submit();
});
$(".page-link").click(function(){
	$("input[name = page]").val($(this).attr("val"))
	submit();
})
$(".sort_btn").click(function(){
	if($(this).attr("sortBy")!='user'){
		$("input[name = sortBy]").val($(this).attr("sortBy"))
	$("input[name = sortName]").val($(this).attr("sortName")=="asc"?"desc":"asc")
	submit();
	}
})
function submit(){
	$("#sort_form").submit();
}
</script>