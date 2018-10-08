package org.ppl.mall.util;

import java.util.Random;

/**
 * ID生成器
 * @author PPL
 */
public class IDUtils {

	/*********************Method**********************/
    /*--------------public static method-------------*/

    /**
     * 生成图片名称ID
     * 长度: 3位, 不足补0
     * @return 图片名称ID的String
     */
	public static String genImageName() {
		return System.currentTimeMillis() + String.format("%03d", new Random().nextInt(999));
	}

    /**
     * 生成商品ID
     * 长度: 2位, 不足补0
     * @return 商品ID
     */
	public static long genItemId() {
		String str = System.currentTimeMillis() + String.format("%02d", new Random().nextInt(99));
		return Long.parseLong(str);
	}
}
