package com.dragon.Service;

import com.dragon.model.*;
import com.dragon.model.Excel.Field;
import com.dragon.model.Excel.FileMapper;
import com.dragon.model.Excel.Mapper;
import com.dragon.repo.IndexPFTSRepo;
import com.dragon.repo.IndexUXRepo;
import com.dragon.repo.FileMapperRepo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by osetskiy on 26.12.2016.
 */
@Component
public class ExelParsing {

    private IndexUXRepo indexUXRepo;

    private IndexPFTSRepo indexPFTSRepo;

    private FileMapperRepo fileMapperRepo;

    @Autowired
    public ExelParsing(IndexUXRepo indexUXRepo, IndexPFTSRepo indexPFTSRepo, FileMapperRepo fileMapperRepo) {
        this.indexUXRepo = indexUXRepo;
        this.indexPFTSRepo = indexPFTSRepo;
        this.fileMapperRepo = fileMapperRepo;
    }

    private List<? extends TableRow> indexUXList = new ArrayList<>();
    private List<? extends TableRow> indexPFTSList = new ArrayList<>();

    public TableRow getTableRow(String name) {
        if (name.equals("IndexUX")) {
            return new IndexUX();
        } else if (name.equals("IndexPFTS")) {
            return new IndexPFTS();
        }
        return null;

    }

    public List<? extends TableRow> getTable(String tableName) {
        if (tableName.equals("IndexUX")) {
            return indexUXList;
        } else if (tableName.equals("IndexPFTS")) {
            return indexPFTSList;
        }
        return null;
    }


    public TableRow getTableRow(Row row, Mapper mapper) throws Exception {
        TableRow table;
        table = null;
        int field_number = 0;
        Iterator<Field> iterator = mapper.getMappedFields().iterator();

        try {

            while (iterator.hasNext()) {
                Field field = iterator.next();
                Cell cell = row.getCell(field.getPosition());
                if (cell != null) {
                    if (field_number == 0) {
                        table = getTableRow(mapper.getName());
                    }
                    if (table != null) {
                        if (field.getType().equals("date")) {
                            Method method = table.getClass().getMethod("set" + field.getName(), Date.class);
                            method.invoke(table, cell.getDateCellValue());
                        } else if (field.getType().equals("double")) {
                            Method method = table.getClass().getMethod("set" + field.getName(), Double.class);
                            method.invoke(table, cell.getNumericCellValue());

                        } else if (field.getType().equals("string")) {
                            Method method = table.getClass().getMethod("set" + field.getName(), String.class);
                            method.invoke(table, cell.getStringCellValue());
                        }
                    }
                }
                field_number++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            table = null;
        }

        return table;
    }

    public void saveTables() {
        indexUXRepo.deleteAll();
        indexUXRepo.save((Iterable<? extends IndexUX>) indexUXList);

        indexPFTSRepo.deleteAll();
        indexPFTSRepo.save((Iterable<? extends IndexPFTS>) indexPFTSList);

    }

    public void parseAndSaveTables() throws Exception {
        List<FileMapper> fileMappers = fileMapperRepo.findAll();
        Iterator<FileMapper> fileMapperIterator = fileMappers.iterator();
        while (fileMapperIterator.hasNext()) {
            FileMapper fileMapper = fileMapperIterator.next();

            FileInputStream inputStream = new FileInputStream(new File(fileMapper.getPathToFile()));
            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                List<Mapper> mapperList = fileMapper.getMapperList();
                Iterator<Mapper> mapperIterator = mapperList.iterator();
                while (mapperIterator.hasNext()) {
                    Mapper mapper = mapperIterator.next();
                    if (nextRow.getRowNum() >= mapper.getRowNumber()) {
                        TableRow tableRow = getTableRow(nextRow, mapper);
                        if (tableRow != null) {
                            ((List<TableRow>) getTable(mapper.getName())).add(tableRow);
                        }
                    }
                }
            }
            workbook.close();
            inputStream.close();
        }
        saveTables();

    }
}
