package com.asolorzano.salesTracker.controller;


import com.asolorzano.salesTracker.dto.RoleDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Role;
import com.asolorzano.salesTracker.service.IRoleService;
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
@RequestMapping("/roles")
public class RoleController  {


    @Autowired
    private IRoleService service;

    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> list = service.readAll().stream()
                .map(r -> mapper.map(r, RoleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO roleDto) throws Exception{
        Role rol = service.create(mapper.map(roleDto, Role.class));
        RoleDTO dto = mapper.map(rol,RoleDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Role rol = service.readById(id);
        if(rol == null){
            throw new ModelNotFoundException("Id Nº: " + id + " No fue encontrado");
        }
        RoleDTO dto = mapper.map(rol, RoleDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO roleDto) throws Exception{
        Role rol = service.readById(roleDto.getIdRole());
        if(rol == null){
            throw new ModelNotFoundException("Id no encontrado: " + roleDto.getIdRole());
        }
        Role role = service.update(mapper.map(roleDto, Role.class));
        RoleDTO dto = mapper.map(role, RoleDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Role rol = service.readById(id);
        if(rol == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }








}
