package com.prog.matoz.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import com.prog.matoz.data.vo.ProductVO;

public class ProductMocks {

	public static final ProductVO findById() {
		return new ProductVO(generatedLong(), randomProduct(), generatedInteger(), generateRandomBigDecimal());
	}

	public static final ArrayList<ProductVO> findAll() {
		return new ArrayList<ProductVO>() {
			private static final long serialVersionUID = 1L;

			{
				getProducts().forEach(p -> {
					add(new ProductVO(generatedLong(), p, generatedInteger(), generateRandomBigDecimal()));
				});
			}
		};
	}

	private static BigDecimal generateRandomBigDecimal() {
		return new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2);
	}

	private static Long generatedLong() {
		return 1L + (long) (Math.random() * (1L - 10L));
	}

	private static Integer generatedInteger() {
		return 0 + new Random().nextInt((9999 - 0) + 1);
	}

	private static String randomProduct() {
		ArrayList<String> products = getProducts();

		int index = (int) (Math.random() * products.size());

		return products.get(index);
	}

	private static ArrayList<String> getProducts() {
		return new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("Earrings Fashion");
				add("Shorts Pants");
				add("Wine Glass Rack");
				add("Polishing Machine Nail Tool");
				add("Night Vision IP Camera Speed Dome WIFI");
				add("Basic Mouse Pad");
				add("Magnetic Balls Building Blocks Super Strong");
				add("Jacket Polyester");
				add("Mouse Keyboard Combo RGB");
				add("Fishing Reel Baitcasting");
				add("Protective Case Watch Cover");
				add("Night Light Moon LED");
				add("Plasma Ball Science & Exploration");
				add("Warrior LED Lighting Lightsabers");
				add("Fried Egg Mold Pancake");
				add("Meat Tools Glasses Artistic Sprayer");
				add("Mandoline Slicer Vegetable");
			}
		};
	}
}
