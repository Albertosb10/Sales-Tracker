package com.asolorzano.salesTracker.controller;

import com.asolorzano.salesTracker.dto.SaleDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Sale;
import com.asolorzano.salesTracker.service.ISaleService;
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
@RequestMapping("/sales")
public class SaleControler {
    @Autowired
    private ISaleService service;

    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception {
        List<SaleDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, SaleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Sale sale = service.readById(id);
        if(sale == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        SaleDTO dto = mapper.map(sale, SaleDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO saleDTO) throws Exception {
        Sale s = mapper.map(saleDTO, Sale.class);
        Sale sale = service.saveTransactional(s, s.getDetails());
        SaleDTO dto = mapper.map(sale, SaleDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO saleDTO) throws Exception {
        Sale sale = service.readById(saleDTO.getIdSale());
        if(sale == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + saleDTO.getIdSale());
        }
        Sale r = service.update(mapper.map(saleDTO, Sale.class));
        SaleDTO dto = mapper.map(r, SaleDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Sale sale = service.readById(id);
        if(sale == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
