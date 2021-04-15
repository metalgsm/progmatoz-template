package com.prog.matoz.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.modelmapper.ModelMapper;

import com.prog.matoz.data.vo.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3156278693068634845L;

	private Long id;
	
	private String name;
	
	private Integer stock;
	
	private BigDecimal price;
	
	public static Product create(ProductVO productVO) {
		return new ModelMapper().map(productVO, Product.class);
	}
	
}
