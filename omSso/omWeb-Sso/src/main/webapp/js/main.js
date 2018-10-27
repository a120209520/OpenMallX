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

    //注册——用户名校验
    $('#register-username > input').blur(function () {
        var username = $(this).val();
        $.getJSON('/user/check/username/'+username, function (data) {
            if (data.status == 200) {
                if (data.data == false) {
                    $('#register-username > .warning').text('用户名已经被注册!换一个吧');
                } else {
                    $('#register-username > .warning').text('');
                }
            }
        })
    });

    //注册——密码一致性校验
    $('#register-confirm-password > input').blur(function () {
        var confirmPass = $(this).val();
        var pass = $('#register-password > input').val();
        if (pass != confirmPass) {
            $('#register-confirm-password > .warning').text('两次输入密码不一致!');
        } else {
            $('#register-confirm-password > .warning').text('');
        }
    });

    //注册——手机号校验
    $('#register-cellphone > input').blur(function () {
        var cellphone = $('#register-cellphone > input').val();
        $.getJSON('/user/check/cellphone/'+cellphone, function (data) {
            if (data.status == 200) {
                if (data.data == false) {
                    $('#register-cellphone > .warning').text('手机号已经被注册!');
                } else {
                    $('#register-cellphone > .warning').text('');
                }
            }
        })
    });

    //注册提交
    $('#register-submit').click(function () {
        $.post(
            '/user/register',
            $("#register-form").serialize(),
            function (data) {
                if (data.status != 200) {
                    alert(data.msg);
                    window.location.replace('/page/register');
                } else {
                    alert("注册成功！");
                    window.location.replace('/page/login');
                }
            },
            "json"
        )
    })

    //登陆提交
    $('#login-submit').click(function () {
        $.post(
            '/user/login',
            $("#login-form").serialize(),
            function (data) {
                if (data.status != 200) {
                    alert("登陆失败!");
                } else {
                    alert("登陆成功!");
                    window.location.replace('http://localhost:8083/index');
                }
            },
            "json"
        )
    })


})(jQuery);

//展示'最新商品'列表
/*function showNewProductList(cid) {
    $.getJSON('/content/newpro/item/'+cid, function(data) {
        var $itemList = $('#new-product-list .product');
        for (var i = 0; i < data.length; i++) {
            var $item = $($itemList[i]);
            $item.find('.product-category').text(data[i].catName);
            $item.find('.product-name').text(data[i].title);
            $item.find('.product-price').text(priceTrans(data[i].price));
            $item.find('.product-img>img').attr('src', data[i].image);
        }
    })
}*/


