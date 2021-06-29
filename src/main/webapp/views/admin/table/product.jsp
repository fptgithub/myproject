 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="app-content">
<div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <div class="table-responsive">
              <form action="/admin/product/table" method = "get" id = "sort_form">
              <input type = "hidden" name = "limit" value = "${ pageRequest.limit.orElse(10) }">
              <input type = "hidden" name = "page" value = "${ pageRequest.page.orElse(0) }">
              <input type = "hidden" name = "sortBy" value = "${ pageRequest.sortBy.orElse('name') }">
              <input type = "hidden" name = "sortName" value = "${ pageRequest.sortName.orElse('asc') }">
              <c:if test="${ not pageRequest.search }">
              <input type = "hidden" name = "search" value = "${ pageRequest.search }">
              </c:if>
              </form>
                <div id="sampleTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="sampleTable_length"><label>Show <select name="sampleTable_length" aria-controls="sampleTable" id = "limit" class="form-control form-control-sm">
                <option value="10"${ pageRequest.limit.orElse(10)==10?'selected':'' }>10</option>
                <option value="25" ${ pageRequest.limit.orElse(10)==25?'selected':'' }>25</option>
                <option value="50"${ pageRequest.limit.orElse(10)==50?'selected':'' }>50</option>
                <option value="100"${ pageRequest.limit.orElse(10)==100?'selected':'' }>100</option>
                </select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="sampleTable_filter" class="dataTables_filter">
                <form action = "/admin/product/table" method = "get"><label>Search:<input type="text" name = "search" id = "search" class="form-control form-control-sm" placeholder="name" aria-controls="sampleTable"></label></form></div></div></div><div class="row"><div class="col-sm-12"><table class="table table-hover table-bordered dataTable no-footer" id="sampleTable" role="grid" aria-describedby="sampleTable_info">
                  <thead>
                  <tr role="row">
                    <tr role="row">
                    <th  class = ""tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" >Edit</th>
                    <th sortBy = "name" sortName = "${ pageRequest.sortName.orElse('asc') }"  class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('name')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }" 
                    tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" >Name</th>
                    <th sortBy = "price" sortName = "${ pageRequest.sortName.orElse('asc') }" onclick="sort()" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('price')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
 					tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" >Price</th>
                    <th sortBy = "salePrice" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('salePrice')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 123.062px;">Sale Price</th>
                    <th sortBy = "poster" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('poster')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" >Poster</th>
                    <th sortBy = "color" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('color')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >Color</th>
                    <th sortBy = "createDate" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('createDate')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >Create Date</th>
                    <th sortBy = "user" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('user')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >Create by</th>
                    <th sortBy = "active" sortName = "${ pageRequest.sortName.orElse('asc') }" class="sort_btn ${ pageRequest.sortBy.orElse('name').equalsIgnoreCase('active')?pageRequest.sortName.orElse('asc').equalsIgnoreCase('asc')?'sorting_asc':'sorting_desc':'' }"
                     tabindex="0" aria-controls="sampleTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" >Active</th>
                    </tr>
                    
                  </thead>
                  <tbody>
                  <c:forEach var = "p" items = "${ pageableProduct.content }">
                  <tr role="row" class="odd">
                   <td ><div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                <button class="btn btn-info" type="button">Edit</button>
                <div class="btn-group" role="group">
                  <button class="btn btn-info dropdown-toggle" id="btnGroupDrop3" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
                  <div class="dropdown-menu dropdown-menu-right" x-placement="bottom-end" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(36px, 37px, 0px);"><a class="dropdown-item" href="/admin/product/edit/${ p.id }">Edit</a>
                  <a class="dropdown-item" href="/admin/product/delete/${ p.id }">Delete</a></div>
                </div>
              </div></td>
                      <td >${ p.name }</td>
                      <td>${ p.price }</td>
                      <td>${ p.salePrice }</td>
                      <td>${ p.poster }</td>
                      <td>${ p.color }</td>
                      <td>${ p.createDate }</td>
                      <td>${ p.user.username }</td>
                      <td>${ p.active }</td>
                      
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
$('#limit').change(function(){
	$("input[name = limit]").val($(this).val())
	submit();
});
$(".page-link").click(function(){
	$("input[name = page]").val($(this).attr("val"))
	submit();
})
$(".sort_btn").click(function(){
	$("input[name = sortBy]").val($(this).attr("sortBy"))
	$("input[name = sortName]").val($(this).attr("sortName")=="asc"?"desc":"asc")
	submit();
})
function submit(){
	$("#sort_form").submit();
}
</script>