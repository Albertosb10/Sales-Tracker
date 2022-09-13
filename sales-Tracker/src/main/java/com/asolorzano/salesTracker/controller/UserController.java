package com.asolorzano.salesTracker.controller;

import com.asolorzano.salesTracker.dto.UserDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.User;
import com.asolorzano.salesTracker.service.IUserService;
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
@RequestMapping("/users")
public class UserController {


    @Autowired
    private IUserService service;

    @Autowired
    @Qualifier("userMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        List<UserDTO> list = service.readAll().stream()
                .map(u -> mapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) throws Exception{
        User use = service.create(mapper.map(userDTO, User.class));
        UserDTO dto = mapper.map(use,UserDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        User use = service.readById(id);
        if(use == null){
            throw new ModelNotFoundException("Id Nº: " + id + " No fue encontrado");
        }
        UserDTO dto = mapper.map(use, UserDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) throws Exception{
        User use = service.readById(userDTO.getIdUser());
        if(use == null){
            throw new ModelNotFoundException("Id no encontrado: " + userDTO.getIdUser());
        }
        User user = service.update(mapper.map(userDTO, User.class));
        UserDTO dto = mapper.map(user, UserDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        User user = service.readById(id);
        if(user == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

