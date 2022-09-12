package com.asolorzano.salesTracker.controller;

import com.asolorzano.salesTracker.dto.CategoryDTO;
import com.asolorzano.salesTracker.exceptions.ModelNotFoundException;
import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.service.ICategoryService;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    /*Forma alternativa de mapear las clases por medio del DTO
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<Category> List = service.readAll();
        List<CategoryDTO>listDTO = new ArrayList<>();
        for (Category c : List) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(c.getIdCategory());
            dto.setNameCategory(c.getName());
            dto.setDescriptionCategory(c.getDescription());
            dto.setEnabledCategory(c.isEnabled());
            listDTO.add(dto);

        }
        return  null;
    }

     */
    @Autowired
    //con elqualifier le paso el metodo que voy a imbocar
    @Qualifier("categoryMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /*
    //notas cortas de un servidor que se insertan en
    @PostMapping
    //request body crea los atributos con sus respectivos valores
    public Category create(@RequestBody Category category) throws Exception{
        return service.create(category);

    }

     */

    //@postmapping con DTOs
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDto) throws Exception{
        Category cat = service.create(mapper.map(categoryDto, Category.class));
        CategoryDTO dto = mapper.map(cat,CategoryDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    /*
    @GetMapping("/{id}")
    public Category readById(@PathVariable("id") Integer id) throws Exception{
        return service.readById(id);
    }

     */

    //buscar por >ID con DTOs

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category cat = service.readById(id);
        if(cat == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        CategoryDTO dto = mapper.map(cat, CategoryDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    /*
    @PutMapping
    public Category update(@RequestBody Category category) throws Exception {
        return service.update(category);
    }

     */

    //el put con dtos
    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO categoryDto) throws Exception{
        Category cat = service.readById(categoryDto.getId());
        if(cat == null){
            throw new ModelNotFoundException("Id no encontrado: " + categoryDto.getId());
        }
        Category category = service.update(mapper.map(categoryDto, Category.class));
        CategoryDTO dto = mapper.map(category, CategoryDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    /*

    /*
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
    }

     */

    //delete con dtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Category cat = service.readById(id);
        if(cat == null){
            throw new ModelNotFoundException("Id no encontrado: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/name/{param}")
    //le pasamos una lista por que no podemos saber cuantas personas hay en la base de datos
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("param") String param) throws Exception
    {
        List<CategoryDTO> list = service.findByName(param).stream()
                //la siguiente linea es para hacer la tx con el DTO
                .map(c -> mapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find/name/like/{param}")
    public ResponseEntity<List<CategoryDTO>> findByNameLike(@PathVariable("param") String param) throws Exception
    {
        List<CategoryDTO> list = service.findByNameLike(param).stream()
                .map(c -> mapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
