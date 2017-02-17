package com.dragon.controller;

import com.dragon.Service.BLPApi;
import com.dragon.Service.ExelParsing;
import com.dragon.model.IndexPFTS;
import com.dragon.model.IndexSPX;
import com.dragon.model.IndexUX;

import com.dragon.model.Security;
import com.dragon.repo.IndexPFTSRepo;
import com.dragon.repo.IndexSPXRepo;
import com.dragon.repo.IndexUXRepo;
import com.dragon.repo.SecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by osetskiy on 26.12.2016.
 */
@RestController
@RequestMapping("/")
public class main {
    private ExelParsing exelParsing;
    private BLPApi blpApi;
    private IndexUXRepo indexUXRepo;
    private IndexPFTSRepo indexPFTSRepo;
    private IndexSPXRepo indexSPXRepo;
    private SecurityRepo securityRepo;


    public main(ExelParsing exelParsing, BLPApi blpApi, IndexUXRepo indexUXRepo, IndexPFTSRepo indexPFTSRepo, IndexSPXRepo indexSPXRepo, SecurityRepo securityRepo) {
        this.exelParsing = exelParsing;
        this.blpApi = blpApi;
        this.indexUXRepo = indexUXRepo;
        this.indexPFTSRepo = indexPFTSRepo;
        this.indexSPXRepo = indexSPXRepo;
        this.securityRepo = securityRepo;
    }

    @PostMapping(path = "/IndexUX")
    @ResponseBody
    public List<IndexUX> uploadIndexUX(@RequestParam("date1") String date1,@RequestParam("date2") String date2) {
        //"20160101","20170101
           return blpApi.getUxIndex(date1,date2);

    }

    @PostMapping(path = "/IndexPFTS")
    @ResponseBody
    public List<IndexPFTS> uploadIndexPFTS(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        //"20160101","20170101
        return blpApi.getPFTSIndex(date1,date2);

    }

    @PostMapping(path = "/IndexSPX")
    @ResponseBody
    public List<IndexSPX> uploadIndexSPX(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        //"20160101","20170101
        return blpApi.getSPXIndex(date1,date2);

    }

    @GetMapping(path = "/IndexUX")
    @ResponseBody
    public List<IndexUX> getIndexUX() {
        List<IndexUX> sortedIndexUXList = new ArrayList<>();
        sortedIndexUXList.addAll(indexUXRepo.findAll());
        sortedIndexUXList.sort(Comparator.comparing((IndexUX i)->i.getDate()));
        return sortedIndexUXList;

    }

    @GetMapping(path = "/IndexPFTS")
    @ResponseBody
    public List<IndexPFTS> getIndexPFTS() {
        List<IndexPFTS> sortedIndexList = new ArrayList<>();
        sortedIndexList.addAll(indexPFTSRepo.findAll());
        sortedIndexList.sort(Comparator.comparing((IndexPFTS i)->i.getDate()));
        return sortedIndexList;

    }

    @GetMapping(path = "/IndexSPX")
    @ResponseBody
    public List<IndexSPX> getIndexSPX() {
        List<IndexSPX> sortedIndexList = new ArrayList<>();
        sortedIndexList.addAll(indexSPXRepo.findAll());
        sortedIndexList.sort(Comparator.comparing((IndexSPX i)->i.getDate()));
        return sortedIndexList;

    }

    @PostMapping(path = "/Security")
    @ResponseBody
    public Security uploadSecurity(@RequestBody Security security) {
        //"20160101","20170101
        securityRepo.save(security);
        return security;
    }

    @GetMapping(path = "/Security/{Type}")
    @ResponseBody
    public List<Security> getSecurity(@PathVariable("Type") String type) {
        //"20160101","20170101


        return null;
    }

    @GetMapping(path = "/Security")
    @ResponseBody
    public List<Security> getAllSecurity() {
        //"20160101","20170101
        return securityRepo.findAll();
    }

    @DeleteMapping(path = "/Security")
    public void deleteSecurity(@RequestBody Security security) {
        //"20160101","20170101
        securityRepo.delete(security);
    }





}
