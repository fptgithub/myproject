<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <main class="app-content">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form  modelAttribute="product">
<div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="row">
              <div class="col-lg-6">
<div class="form-group">
<form:label path="name">Name Product</form:label>
<form:input class="form-control" id="productName" type="text" path="name" placeholder="Enter Name"/>
<form:errors class="text-danger" path="name"/>
</div>
<form:input path="id" type = "hidden"/>
<div class="form-group">
<form:label path="color">Color Product</form:label>
<form:input class="form-control" id="productColor" type="text" path="color" placeholder="Enter Color"/>
<form:errors class="text-danger" path="color"/>
</div>
<div class="form-group">
<form:label path="price">Price Product</form:label>
<form:input class="form-control" id="productPrice" type="number" path="price" placeholder="Enter Price"/>
<form:errors class="text-danger" path="price"/>
</div>
<div class="form-group">
<form:label path="salePrice">Sale Price Product</form:label>
<form:input class="form-control" id="productSalePrice" type="number" path="salePrice" placeholder="Enter SalePrice"/>
<form:errors class="text-danger" path="salePrice"/>
</div>
<div class="form-group">
<form:label path="slug">Slug Product</form:label>
<form:input class="form-control" id="productSlug" type="text" path="slug" placeholder="Enter Slug"/>
<form:errors class="text-danger" path="slug"/>
</div>
<div class="form-group">
<form:label path="description">Desciption Product</form:label>
<form:textarea rows="3" class="form-control" id="productDescription" type="text" path="description" placeholder="Enter Desciption"/>
</div>

</div>
<div class="col-lg-4 offset-lg-1">

                  <div class="form-group">
                    <fieldset disabled>
                      <form:label path="createDate">Create Date</form:label>
                      <form:input class="form-control" path = "createDate" id="productCreateDate" type="text" placeholder="CreateDate" />
                    </fieldset>
                    <form:input class="form-control" type = "hidden" path = "createDate"  />
                  </div>
                  <div class="form-check">
                    <form:checkbox path="active"/>&nbsp&nbspProduct Active
                  </div>
                  <div class="form-group">
<form:label path="poster">Poster Product</form:label>
<form:input class="form-control" id="productPoster" type="text" path="poster" placeholder="Enter Poster"/>
</div>
<div class="form-group">
<form:label path="images">image Product</form:label>
<form:textarea rows="3" class="form-control" id="productImage" type="text" path="images" placeholder="Enter images"/>
</div>
	<div class="form-group">
<form:label path="category">Category Product</form:label>
<form:select path="category">
<form:options items = "${ categorys }"/>
</form:select>
</div>
	<div class="form-group">
<form:label path="customtype">CustomType Product</form:label>
<form:select path="customtype">
<form:options items = "${ customtypes }"/>
</form:select>
</div>
<div class="form-group">
<button class="btn btn-primary" formaction="/admin/product/create" formmethod="post" type="submit" <c:if test = "${ not empty product.name }">disabled </c:if>> Create</button>
<button class="btn btn-primary" formaction="/admin/product/update" formmethod="post" type="submit" <c:if test = "${ empty product.name }">disabled </c:if>>Update</button>
<a class="btn btn-success" href="/admin/product/index" type="submit">Clear</a>
</div>
   </div>
  </div>
  </div>
  </div>
  </div>
</form:form>
  
 </main>
</script>