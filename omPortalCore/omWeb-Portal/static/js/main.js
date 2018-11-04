(function($) {
	"use strict"



	// Mobile Nav toggle
	$('.menu-toggle > a').on('click', function (e) {
		e.preventDefault();
		$('#responsive-nav').toggleClass('active');
	})

	// Fix cart dropdown from closing
	$('.cart-dropdown').on('click', function (e) {
		e.stopPropagation();
	});



	/////////////////////////////////////////

	// Products Slick
	$('.products-slick').each(function() {
        reformatProductItem($(this));
	});

	// Products Widget Slick
	$('.products-widget-slick').each(function() {
		var $this = $(this),
				$nav = $this.attr('data-nav');

		$this.slick({
			infinite: true,
			autoplay: true,
			speed: 300,
			dots: false,
			arrows: true,
			appendArrows: $nav ? $nav : false,
		});
	});

	/////////////////////////////////////////

	// Product Main img Slick
	$('#product-main-img').slick({
    infinite: true,
    speed: 300,
    dots: false,
    arrows: true,
    fade: true,
    asNavFor: '#product-imgs',
  });

	// Product imgs Slick
    $('#product-imgs').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    centerMode: true,
    focusOnSelect: true,
		centerPadding: 0,
		vertical: true,
    asNavFor: '#product-main-img',
		responsive: [{
        breakpoint: 991,
        settings: {
					vertical: false,
					arrows: false,
					dots: true,
        }
      },
    ]
  });

	// Product img zoom
	var zoomMainProduct = document.getElementById('product-main-img');
	if (zoomMainProduct) {
		$('#product-main-img .product-preview').zoom();
	}

	/////////////////////////////////////////

	// Input number
	$('.input-number').each(function() {
		var $this = $(this),
		$input = $this.find('input[type="number"]'),
		up = $this.find('.qty-up'),
		down = $this.find('.qty-down');

		down.on('click', function () {
			var value = parseInt($input.val()) - 1;
			value = value < 1 ? 1 : value;
			$input.val(value);
			$input.change();
			updatePriceSlider($this , value)
		})

		up.on('click', function () {
			var value = parseInt($input.val()) + 1;
			$input.val(value);
			$input.change();
			updatePriceSlider($this , value)
		})
	});

	var priceInputMax = document.getElementById('price-max'),
			priceInputMin = document.getElementById('price-min');

/*	priceInputMax.addEventListener('change', function(){
		updatePriceSlider($(this).parent() , this.value)
	});

	priceInputMin.addEventListener('change', function(){
		updatePriceSlider($(this).parent() , this.value)
	});*/

	function updatePriceSlider(elem , value) {
		if ( elem.hasClass('price-min') ) {
			console.log('min')
			priceSlider.noUiSlider.set([value, null]);
		} else if ( elem.hasClass('price-max')) {
			console.log('max')
			priceSlider.noUiSlider.set([null, value]);
		}
	}

	// Price Slider
	var priceSlider = document.getElementById('price-slider');
	if (priceSlider) {
		noUiSlider.create(priceSlider, {
			start: [1, 999],
			connect: true,
			step: 1,
			range: {
				'min': 1,
				'max': 999
			}
		});

		priceSlider.noUiSlider.on('update', function( values, handle ) {
			var value = values[handle];
			handle ? priceInputMax.value = value : priceInputMin.value = value
		});
	}



    //添加到购物车
    $('.add-to-cart').click(function () {
        var id = $(this).parent().find('.product-name > a').attr('href').split('/')[2];
        var $bar = $(this).find('.add-to-cart-btn>span');
        $.ajax({
            url : "http://localhost:8089/cart/add/"+id+"/1/",
            dataType : "jsonp",
            type : "GET",
            success : function(data) {
                if(data.status == 200) {
                    getCartInfo();  //更新购物车
                    $bar.text('已添加!');
                }
            }
        });
    });

    //鼠标移开后恢复购物车按钮
    $('.add-to-cart').mouseout(function () {
        $(this).find('.add-to-cart-btn > span').text('添加到购物车');
    });

    showNewProductList();
    //showTopProductList();
    //showTopSellingList();
    transferAllPrice();
    getLoginInfo();
    getCartInfo();

})(jQuery);

//展示'最新商品'列表
function showNewProductList() {
    //$('#tab0').siblings().addClass('hidden');
    $('#tab0').removeClass('hidden');
    $('#new-product-bar > li > a').click(function () {
        var id = $(this).attr('href');
        $('#tab'+id).siblings().addClass('hidden');
        $('#tab'+id).removeClass('hidden');
    })
}

//展示'热销商品'列表
function showTopProductList() {
    $.ajax({
        url : 'http://localhost:8083/content/newpro/item/0',
        dataType : "jsonp",
        type : "GET",
        success : function(data) {
            var $itemList = $('#top-product-list .product');
            for (var i = 0; i < data.length; i++) {
                var $item = $($itemList[i]);
                $item.find('.product-category').text(data[i].catName);
                $item.find('.product-name > a').text(data[i].title);
                $item.find('.product-name > a').attr('href','/item/'+data[i].id);
                $item.find('.product-price').text(priceTrans(data[i].price));
                $item.find('.product-img>img').attr('src', data[i].image);
            }
        }
    });
}

//展示'销量排行'列表
function showTopSellingList() {
    $.ajax({
        url : 'http://localhost:8083/content/newpro/item/0',
        dataType : "jsonp",
        type : "GET",
        success : function(data) {
            var $itemList = $('.products-widget-slick');
            for (var i = 0; i < data.length; i++) {
                var $item = $($itemList[i]);
                $item.find('.product-category').text(data[i].catName);
                $item.find('.product-name > a').text(data[i].title);
                $item.find('.product-name > a').attr('href','/item/'+data[i].id);
                $item.find('.product-price').text(priceTrans(data[i].price));
                $item.find('.product-img>img').attr('src', data[i].image);
            }
        }
    });
}

//给主页的商品信息重新调整格式
//Products Slick
function reformatProductItem($itemList) {
    $itemList = $itemList,
        $nav = $itemList.attr('data-nav');

    $itemList.slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        infinite: true,
        speed: 300,
        dots: false,
        arrows: true,
        appendArrows: $nav ? $nav : false,
        responsive: [{
            breakpoint: 991,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 1,
            }
        },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                }
            },
        ]
    });
}

//把数据库中的价格转为前端显示方式
function priceTrans(serverPrice) {
    return serverPrice/100 + '.' + serverPrice/10%10 + serverPrice%10;
}

//修改价格显示格式
function transferAllPrice() {
    var nodes = $('.product-price');
    for (var i=0; i<nodes.length; i++) {
        var viewPrice = priceTrans($(nodes[i]).text());
        $(nodes[i]).text(viewPrice);
    }
}

//获取登陆信息
function getLoginInfo() {
    $.ajax({
        url : "http://localhost:8087/user/token/jsonp/" + $.cookie('login'),
        dataType : "jsonp",
        type : "GET",
        success : function(data) {
            if(data.status == 200) {
                $.cookie('userId', data.data.id);
                var username = data.data.username;
                $('#user-info > span').text(username);
                $('#header-account').show();
            } else {
                $('#user-info > span').text('请登录');
                $('#header-account').hide();
            }
        }
    });
}

//获取购物车信息
function getCartInfo() {
    var userId = $.cookie('userId');
    if(userId == null) {
        userId = "";
    }
    $.ajax({
        url : "http://localhost:8089/cart/list/" + userId,
        dataType : "jsonp",
        type : "GET",
        success : function(data) {
            var hiddenNode = $('.cart-list > .product-widget.hidden');
            hiddenNode.siblings('.product-widget').remove();
            var totalPrice = 0;
            for (var i = 0; i < data.length; i++) {
                var newCartItem = hiddenNode.clone();
                newCartItem.removeClass('hidden');
                newCartItem.find('.product-img > img').attr('src', data[i].image);
                newCartItem.find('.product-name > a').text(data[i].title);
                newCartItem.find('.cart-item-num').text(data[i].num+'x');
                newCartItem.find('.cart-item-price').text(priceTrans(data[i].price));
                $('.cart-list').append(newCartItem);
                totalPrice += (data[i].price / 100.0) * data[i].num;
            }
            $('.cart-summary > small').text('共有'+data.length+'件商品');
            $('.cart-summary > h5').text('小计: '+totalPrice.toFixed(2)+'元');
            $('#cart-num').text(data.length);
        }
    });
}
