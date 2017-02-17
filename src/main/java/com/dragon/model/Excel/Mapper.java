package com.dragon.model.Excel;


import com.dragon.model.Excel.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osetskiy on 23.01.2017.
 */
public class Mapper {
    String name;
    Integer rowNumber;
    List<Field> mappedFields = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getMappedFields() {
        return mappedFields;
    }

    public void setMappedFields(List<Field> mappedFields) {
        this.mappedFields = mappedFields;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }


}
