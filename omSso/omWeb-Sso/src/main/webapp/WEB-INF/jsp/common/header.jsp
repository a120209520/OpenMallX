<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                <li><a href="#"><i class="fa fa-rmb"></i> RMB</a></li>
                <li><a href="#"><i class="fa fa-user-o"></i> 我的账户</a></li>
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
                        <form action="/search/">
                            <select class="input-select">
                                <option value="0">All</option>
                                <option value="1">Category 01</option>
                                <option value="1">Category 02</option>
                            </select>
                            <input class="input" placeholder="在这里搜索" name="keyword" value="${query}">
                            <button type="submit" class="search-btn">搜索</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">

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