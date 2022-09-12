package com.asolorzano.salesTracker.controller;


import com.asolorzano.salesTracker.dto.ClientDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Client;
import com.asolorzano.salesTracker.service.IClientService;
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
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDto) throws Exception{
        Client cli = service.create(mapper.map(clientDto, Client.class));
        ClientDTO dto = mapper.map(cli,ClientDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client cli = service.readById(id);
        if(cli == null){
            throw new ModelNotFoundException("Id Nº: " + id + " No fue encontrado");
        }
        ClientDTO dto = mapper.map(cli, ClientDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDto) throws Exception{
        Client cli = service.readById(clientDto.getIdClient());
        if(cli == null){
            throw new ModelNotFoundException("Id no encontrado: " + clientDto.getIdClient());
        }
        Client client = service.update(mapper.map(clientDto, Client.class));
        ClientDTO dto = mapper.map(client, ClientDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Client cli = service.readById(id);
        if(cli == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

