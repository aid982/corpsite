package com.dragon.model.Excel;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osetskiy on 24.01.2017.
 */
public class FileMapper {
    @Id
    String Id;
    private String pathToFile;
    private List<Mapper> mapperList = new ArrayList<>();

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public List<Mapper> getMapperList() {
        return mapperList;
    }

    public void setMapperList(List<Mapper> mapperList) {
        this.mapperList = mapperList;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
