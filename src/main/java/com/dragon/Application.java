package com.dragon;

import com.dragon.model.Excel.Field;
import com.dragon.model.Excel.FileMapper;
import com.dragon.model.Excel.Mapper;
import com.dragon.repo.FileMapperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application {
    @Autowired
    FileMapperRepo mapperRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS");

                //registry.addMapping("/**");
                //.allowedOrigins("http://localhost:3001","**");
            }
        };
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            mapperRepo.deleteAll();
            FileMapper fileMapper = new FileMapper();
            fileMapper.setPathToFile("P://Exchange//1с/indexes.xls");
            fileMapper.setId("1");
            Mapper mapper1 = new Mapper();
            mapper1.setRowNumber(2);
            mapper1.setName("IndexUX");
            List<Field> fieldMapper1 = new ArrayList<>();
            fieldMapper1.add(new Field("Date", "date", 0));
            fieldMapper1.add(new Field("Index", "double", 1));
            mapper1.setMappedFields(fieldMapper1);

            Mapper mapper2 = new Mapper();
            mapper2.setRowNumber(2);
            mapper2.setName("IndexPFTS");
            List<Field> fieldMapper2 = new ArrayList<>();
            fieldMapper2.add(new Field("Date", "date", 3));
            fieldMapper2.add(new Field("Index", "double", 4));
            mapper2.setMappedFields(fieldMapper2);

            List<Mapper> mapperList = new ArrayList<>();
            mapperList.add(mapper1);
            mapperList.add(mapper2);

            fileMapper.setMapperList(mapperList);

            mapperRepo.save(fileMapper);


        };

    }

}
