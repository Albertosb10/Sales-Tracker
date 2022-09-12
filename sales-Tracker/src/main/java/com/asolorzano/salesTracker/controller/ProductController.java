package com.asolorzano.salesTracker.controller;

import com.asolorzano.salesTracker.dto.ProductDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Product;
import com.asolorzano.salesTracker.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = service.readAll().stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDto) throws Exception{
        Product product = service.create(mapper.map(productDto, Product.class));
        ProductDTO dto = mapper.map(product,ProductDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product pro = service.readById(id);
        if(pro == null){
            throw new ModelNotFoundException("Id Nº: " + id + " No fue encontrado");
        }
        ProductDTO dto = mapper.map(pro, ProductDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO productDto) throws Exception{
        Product pro = service.readById(productDto.getIdProduct());
        if(pro == null){
            throw new ModelNotFoundException("Id no encontrado: " + productDto.getIdProduct());
        }
        Product product = service.update(mapper.map(productDto, Product.class));
        ProductDTO dto = mapper.map(product, ProductDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product pro = service.readById(id);
        if(pro == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
