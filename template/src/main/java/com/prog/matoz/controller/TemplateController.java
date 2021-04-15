package com.prog.matoz.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prog.matoz.data.vo.ProductVO;
import com.prog.matoz.service.ProductService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/product")
@Api(tags = "Template")
public class TemplateController {

	private final ProductService productService;

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ProductVO findById(@PathVariable("id") Long id) {
		ProductVO productVO = productService.findById(id);
		productVO.add(linkTo(methodOn(TemplateController.class).findById(id)).withSelfRel());
		return productVO;
	}

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ArrayList<ProductVO> findAll() {
		ArrayList<ProductVO> products = productService.findAll();
		products.stream()
				.forEach(p -> p.add(linkTo(methodOn(TemplateController.class).findById(p.getId())).withSelfRel()));

		return products;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ProductVO create(@RequestBody ProductVO productVO) {
		ProductVO vo = productService.create(productVO);
		vo.add(linkTo(methodOn(TemplateController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ProductVO update(@RequestBody ProductVO productVO) {
		ProductVO vo = productService.update(productVO);
		vo.add(linkTo(methodOn(TemplateController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}

	@Autowired
	public TemplateController(ProductService productService) {
		super();
		this.productService = productService;
	}

}
