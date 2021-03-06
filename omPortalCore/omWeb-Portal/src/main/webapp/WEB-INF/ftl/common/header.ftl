<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +800 800 8888</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> a120209520@icloud.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> 中国</a></li>
            </ul>
            <ul class="header-links pull-right">
                <li>
                    <a href="#">
                        <i class="fa fa-rmb"></i>
                        RMB
                    </a>
                </li>
                <li>
                    <a href="http://localhost:8087/page/login/" id="user-info">
                        <i class="fa fa-user-o"></i>
                        <span>我的账户</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- space -->
                <div class="col-md-1"></div>
                <!-- LOGO -->
                <div class="col-md-2">
                    <div class="header-logo">
                        <a href="#" class="logo">
                            <img src="/img/logo-ppl.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form action="http://localhost:8083/search/">
                            <select class="input-select">
                                <option value="0">All</option>
                            </select>
                            <input class="input" placeholder="在这里搜索" name="keyword" value="">
                            <button type="submit" class="search-btn">搜索</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix" id="header-account">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <div>
                            <a href="#">
                                <i class="fa fa-heart-o"></i>
                                <span>我的收藏</span>
                                <div class="qty">2</div>
                            </a>
                        </div>
                        <!-- /Wishlist -->

                        <!-- Cart -->
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <i class="fa fa-shopping-cart"></i>
                                <span>购物车</span>
                                <div class="qty" id="cart-num"></div>
                            </a>
                            <div class="cart-dropdown">
                                <div class="cart-list">
                                    <div class="product-widget hidden">
                                        <div class="product-img">
                                            <img src="" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h3 class="product-name"><a href="#"></a></h3>
                                            <h4 class="cart-item-price-num">
                                                <span class="cart-item-num"></span>
                                                <span class="cart-item-price"></span>
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="cart-summary">
                                    <small></small>
                                    <h5></h5>
                                </div>
                                <div class="cart-btns">
                                    <a href="http://localhost:8089/cart/delete/">清空购物车&nbsp;&nbsp;<i class="fa fa-remove"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->