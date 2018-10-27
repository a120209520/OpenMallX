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

    showNewProductList(0);
    showTopProductList();
    showTopSellingList();
    transferAllPrice();
    getLoginInfo();
    //主页
    $('.section-tab-nav.tab-nav > li').click(function () {
        var cid = $(this).children('a').attr('href');
        showNewProductList(cid);
    });


})(jQuery);

//展示'最新商品'列表
function showNewProductList(cid) {
    $.getJSON('/content/newpro/item/'+cid, function(data) {
        var $itemList = $('#new-product-list .product');
        for (var i = 0; i < data.length; i++) {
            var $item = $($itemList[i]);
            $item.find('.product-category').text(data[i].catName);
            $item.find('.product-name > a').text(data[i].title);
            $item.find('.product-name > a').attr('href','/item/'+data[i].id);
            $item.find('.product-price').text(priceTrans(data[i].price));
            $item.find('.product-img>img').attr('src', data[i].image);
        }
    })
}

//展示'热销商品'列表
function showTopProductList() {
    $.getJSON('/content/newpro/item/0', function(data) {
        var $itemList = $('#top-product-list .product');
        for (var i = 0; i < data.length; i++) {
            var $item = $($itemList[i]);
            $item.find('.product-category').text(data[i].catName);
            $item.find('.product-name > a').text(data[i].title);
            $item.find('.product-name > a').attr('href','/item/'+data[i].id);
            $item.find('.product-price').text(priceTrans(data[i].price));
            $item.find('.product-img>img').attr('src', data[i].image);
        }
    })
}

//展示'销量排行'列表
function showTopSellingList() {
    $.getJSON('/content/newpro/item/0', function(data) {
        var $itemList = $('.products-widget-slick');
        for (var i = 0; i < data.length; i++) {
            var $item = $($itemList[i]);
            $item.find('.product-category').text(data[i].catName);
            $item.find('.product-name > a').text(data[i].title);
            $item.find('.product-name > a').attr('href','/item/'+data[i].id);
            $item.find('.product-price').text(priceTrans(data[i].price));
            $item.find('.product-img>img').attr('src', data[i].image);
        }
    })
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
                var username = data.data.username;
                $('#user-info > span').text(username);
            }
        }
    });
}
