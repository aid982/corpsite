package com.dragon.Service;

import com.dragon.model.Index;
import com.dragon.model.IndexPFTS;
import com.dragon.model.IndexSPX;
import com.dragon.model.IndexUX;
import com.dragon.repo.FileMapperRepo;
import com.dragon.repo.IndexPFTSRepo;
import com.dragon.repo.IndexSPXRepo;
import com.dragon.repo.IndexUXRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by osetskiy on 30.01.2017.
 */
@Component
public class BLPApi {
    @Value("${host}")
    private String url;
    private IndexUXRepo indexUXRepo;
    private IndexPFTSRepo indexPFTSRepo;
    private IndexSPXRepo indexSPXRepo;

    @Autowired
    public BLPApi(IndexUXRepo indexUXRepo, IndexPFTSRepo indexPFTSRepo, IndexSPXRepo indexSPXRepo) {
        this.indexUXRepo = indexUXRepo;
        this.indexPFTSRepo = indexPFTSRepo;
        this.indexSPXRepo = indexSPXRepo;
    }

    public  List<IndexUX> getUxIndex(String date1,String date2) {
        //"20170101"
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("date1", date1)
                .queryParam("date2", date2);
        String reqJSON = "[\"UX Index\"]";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<Object>(reqJSON,headers);
        ResponseEntity<IndexUX[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, request,IndexUX[].class);
        List<IndexUX> indexUXList = Arrays.asList(response.getBody());
        indexUXRepo.save(indexUXList);
        return indexUXList;
    }

    public  List<IndexPFTS> getPFTSIndex(String date1, String date2) {
        //"20170101"
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("date1", date1)
                .queryParam("date2", date2);
        String reqJSON = "[\"PFTS Index\"]";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<Object>(reqJSON,headers);
        ResponseEntity<IndexPFTS[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, request,IndexPFTS[].class);
        List<IndexPFTS> indexList = Arrays.asList(response.getBody());
        indexPFTSRepo.save(indexList);
        return indexList;
    }

    public  List<IndexSPX> getSPXIndex(String date1, String date2) {
        //"20170101"
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("date1", date1)
                .queryParam("date2", date2);
        String reqJSON = "[\"SPX Index\"]";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<Object>(reqJSON,headers);
        ResponseEntity<IndexSPX[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, request,IndexSPX[].class);
        List<IndexSPX> indexList = Arrays.asList(response.getBody());
        indexSPXRepo.save(indexList);
        return indexList;
    }


}
