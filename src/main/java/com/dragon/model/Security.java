package com.dragon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

/**
 * Created by osetskiy on 13.02.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Security {
    @Id
    private String nameBLAPI;
    private String type;
    private String name;

    public String getNameBLAPI() {
        return nameBLAPI;
    }

    public void setNameBLAPI(String nameBLAPI) {
        this.nameBLAPI = nameBLAPI;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
