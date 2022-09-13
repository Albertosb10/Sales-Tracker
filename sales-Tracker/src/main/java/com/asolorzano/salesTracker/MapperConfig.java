package com.asolorzano.salesTracker;


import com.asolorzano.salesTracker.dto.CategoryDTO;
import com.asolorzano.salesTracker.dto.ProductDTO;
import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //esto es para  hacer la conversion del dto a las clases
    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<CategoryDTO, Category> typeMap = mapper.createTypeMap(CategoryDTO.class,Category.class);
        typeMap.addMapping(CategoryDTO::getId, Category::setIdCategory);
        typeMap.addMapping(CategoryDTO::getNameCategory, Category::setName);
        typeMap.addMapping(CategoryDTO::getDescriptionCategory, Category::setDescription);
        typeMap.addMapping(CategoryDTO::isEnabledCategory, Category::setEnabled);
        return mapper;
    }

    //como los campos son iguales al dto, solo es crear la instancia
    @Bean("clientMapper")
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }


    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap.addMapping(ProductDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer)v));
        return mapper;
    }

    @Bean("roleMapper")
    public ModelMapper roleMapper(){ return new ModelMapper();}

    @Bean("providerMapper")
    public ModelMapper providerMapper(){ return new ModelMapper();}

    @Bean("userMapper")
    public ModelMapper userMapper(){ return new ModelMapper();}

    @Bean("saleMapper")
    public ModelMapper saleMapper(){ return new ModelMapper();}



}
