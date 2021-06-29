 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="app-content">
<div class="row">
<div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">order detail</h3>
            <table class="table">
              <thead>
                <tr>
                  <th>product name</th>
                  <th>quantity</th>
                  <th>money</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var = "o" items = "${ orderdetails }">
             
                <tr>
                  <td>${ o.product.name }</td>
                  <td>${ o.quantity }</td>
                  <td>${ o.quantity*o.product.salePrice }$</td>
                </tr>
                 </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
 </div>
 </main>