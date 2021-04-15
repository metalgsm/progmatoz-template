package com.prog.matoz.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
	private final PagedResourcesAssembler<ProductVO> assembler;

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ProductVO findById(@PathVariable("id") Long id) {
		ProductVO productVO = productService.findById(id);
		productVO.add(linkTo(methodOn(TemplateController.class).findById(id)).withSelfRel());
		return productVO;
	}

	@GetMapping(value = { "/all", "/all/{page}/{page-size}/{direction}" }, produces = { "application/json",
			"application/xml", "application/x-yaml" })
	public PagedModel<EntityModel<ProductVO>> findAll(@PathVariable Optional<Integer> page,
			@PathVariable(value = "page-size") Optional<Integer> pageSize, @PathVariable Optional<String> direction) {

		var _sortDirection = direction.isPresent()
				? direction.get().equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC
				: Direction.DESC;
		
		var _page = page.isPresent() ? page.get() : 1;
		
		var _size = pageSize.isPresent() ? pageSize.get() : 25;

		Pageable pageable = PageRequest.of(_page, _size, Sort.by(_sortDirection, "id"));

		Page<ProductVO> products = productService.findAll(pageable);
		products.stream()
				.forEach(p -> p.add(linkTo(methodOn(TemplateController.class).findById(p.getId())).withSelfRel()));

		PagedModel<EntityModel<ProductVO>> pageModel = assembler.toModel(products);
		
		return pageModel;
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
	public TemplateController(ProductService productService, PagedResourcesAssembler<ProductVO> assembler) {
		super();
		this.productService = productService;
		this.assembler = assembler;
	}

}
