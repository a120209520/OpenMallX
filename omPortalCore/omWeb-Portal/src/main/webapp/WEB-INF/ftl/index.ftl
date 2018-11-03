<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Home</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="/css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="/css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="/css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="/css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
        <!-- HEADER -->
        <#include "common/header.ftl"/>

		<!-- NAVIGATION -->
        <#include "common/navigation.ftl"/>

		<!-- SHOP NOW -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
                    <!-- shop -->
                    <#list showNowList as node>
                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="${node.pic}" alt="${node.title}">
                                </div>
                                <div class="shop-body">
                                    <h3>${node.title}<br>${node.subTitle}</h3><br><br>
                                    <a href="/search/?keyword=${node.title}" class="cta-btn">现在抢购<i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </#list>
                    <!-- /shop -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SHOP NOW-->

		<!-- NEW PRODUCT -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">最新产品</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
                                    <li><a data-toggle="tab" href="0">新品上市</a></li>
                                    <#list newProductCat as node>
                                        <li><a data-toggle="tab" href="${node.id}">${node.name}</a></li>
                                    </#list>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1" id="new-product-list">

                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="aaa"></a></h3>
                                                <h4 class="product-price"> <del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"> <del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"> <del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"> <del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->

									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEW PRODUCT -->

		<!-- HOT DEAL SECTION -->
		<div id="hot-deal" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="hot-deal">
							<ul class="hot-deal-countdown">
								<li>
									<div>
										<h3>02</h3>
										<span>天</span>
									</div>
								</li>
								<li>
									<div>
										<h3>10</h3>
										<span>小时</span>
									</div>
								</li>
								<li>
									<div>
										<h3>34</h3>
										<span>分钟</span>
									</div>
								</li>
								<li>
									<div>
										<h3>60</h3>
										<span>秒</span>
									</div>
								</li>
							</ul>
							<h2 class="text-uppercase">双11狂欢活动</h2>
							<p>全场商品不止5折!</p>
							<a class="primary-btn cta-btn" href="#">现在秒杀！</a>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /HOT DEAL SECTION -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">热销商品</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active"><a data-toggle="tab" href="#tab2">销量TOP</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab2" class="tab-pane fade in active">
									<div class="products-slick" data-nav="#slick-nav-2" id="top-product-list">
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="" alt="">
												<div class="product-label">
													<span class="sale">-30%</span>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<p class="product-category"></p>
												<h3 class="product-name"><a href="#"></a></h3>
												<h4 class="product-price"><del class="product-old-price"></del></h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
											</div>
										</div>
										<!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"><del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"><del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category"></p>
                                                <h3 class="product-name"><a href="#"></a></h3>
                                                <h4 class="product-price"><del class="product-old-price"></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    <span>添加到购物车</span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /product -->


									</div>
									<div id="slick-nav-2" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- /Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">销量排行</h4>
							<div class="section-nav">
								<div id="slick-nav-3" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-3">
							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="" alt="">
									</div>
									<div class="product-body">
										<p class="product-category"></p>
										<h3 class="product-name"><a href="#"></a></h3>
										<h4 class="product-price"><del class="product-old-price"></del></h4>
									</div>
								</div>
								<!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>

							<div>
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>
						</div>
					</div>

					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">销量排行</h4>
							<div class="section-nav">
								<div id="slick-nav-4" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-4">
							<div>
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>

							<div>
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>
						</div>
					</div>

					<div class="clearfix visible-sm visible-xs"></div>

					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">销量排行</h4>
							<div class="section-nav">
								<div id="slick-nav-5" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-5">
							<div>
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>

							<div>
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
                                <!-- product widget -->
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="" alt="">
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category"></p>
                                        <h3 class="product-name"><a href="#"></a></h3>
                                        <h4 class="product-price"><del class="product-old-price"></del></h4>
                                    </div>
                                </div>
                                <!-- /product widget -->
							</div>
						</div>
					</div>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<p>Sign Up for the <strong>NEWSLETTER</strong></p>
							<form>
								<input class="input" type="email" placeholder="Enter Your Email">
								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
							</form>
							<ul class="newsletter-follow">
								<li>
									<a href="#"><i class="fa fa-wechat"></i></a>
								</li>
                                <li>
									<a href="#"><i class="fa fa-qq"></i></a>
								</li>
                                <li>
									<a href="#"><i class="fa fa-weibo"></i></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->

		<!-- FOOTER -->
		<#include "common/footer.ftl"/>
		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/slick.min.js"></script>
		<script src="/js/nouislider.min.js"></script>
		<script src="/js/jquery.zoom.min.js"></script>
		<script src="/js/jquery.cookie.js"></script>
		<script src="/js/main.js"></script>
	</body>
</html>
