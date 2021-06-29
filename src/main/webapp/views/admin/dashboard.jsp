<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
          <p>A free and open source Bootstrap 4 admin template</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
        </ul>
      </div>
      
      <div class = "row">
      <div class="col-md-6 col-lg-4">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-users fa-3x"></i>
            <div class="info">
              <h4>Users</h4>
              <p><b>${ dashboardData.totalUser }</b></p>
            </div>
          </div>
        </div>
        
        <div class="col-md-6 col-lg-4">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-shopping-basket fa-3x"></i>
            <div class="info">
              <h4>Order</h4>
              <p><b>${ dashboardData.totalOrder }</b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="widget-small info coloured-icon"><i class="icon fa fa-product-hunt fa-3x"></i>
            <div class="info">
              <h4>ProductSold</h4>
              <p><b>${ dashboardData.totalProductSold }</b></p>
            </div>
          </div>
        </div>
        </div>
      <div class = "row">
      <div class="col-md-6 col-lg-4">
          <div class="widget-small warning coloured-icon"><i class="icon fa fa-money fa-3x"></i></i>
            <div class="info">
              <h4>sales</h4>
              <p><b>${ dashboardData.totalSale }</b></p>
            </div>
          </div>
        </div>
      <div class="col-md-6 col-lg-4">
          <div class="widget-small warning coloured-icon"><i class="icon fa fa-thumbs-o-up fa-3x"></i>
            <div class="info">
              <h4>Evaluate</h4>
              <p><b>${ dashboardData.totalEvaluate }</b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-star fa-3x"></i>
            <div class="info">
              <h4>Stars</h4>
              <p><b>${ dashboardData.avgStar() }</b></p>
            </div>
          </div>
        </div>
      </div>
        
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">Monthly Sales</h3>
            <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">Support Requests</h3>
            <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
            </div>
          </div>
        </div>
      </div>
    </main>