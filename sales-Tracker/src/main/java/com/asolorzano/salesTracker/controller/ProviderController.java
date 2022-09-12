package com.asolorzano.salesTracker.controller;

import com.asolorzano.salesTracker.dto.ProviderDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Provider;
import com.asolorzano.salesTracker.service.IProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private IProviderService service;

    @Autowired
    @Qualifier("providerMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> list = service.readAll().stream()
                .map(p -> mapper.map(p, ProviderDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO providerDto) throws Exception{
        Provider pro = service.create(mapper.map(providerDto, Provider.class));
        ProviderDTO dto = mapper.map(pro,ProviderDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Provider pro = service.readById(id);
        if(pro == null){
            throw new ModelNotFoundException("Id Nº: " + id + " No fue encontrado");
        }
        ProviderDTO dto = mapper.map(pro, ProviderDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO providerDto) throws Exception{
        Provider pro = service.readById(providerDto.getIdProvider());
        if(pro == null){
            throw new ModelNotFoundException("Id no encontrado: " + providerDto.getIdProvider());
        }
        Provider provider = service.update(mapper.map(providerDto, Provider.class));
        ProviderDTO dto = mapper.map(provider, ProviderDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Provider pro = service.readById(id);
        if(pro == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
