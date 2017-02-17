package com.dragon;

/**
 * Created by osetskiy on 26.12.2016.
 */
import com.dragon.Service.BLPApi;
import com.dragon.model.IndexUX;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Date;

public class GetBLPApi {
    @Autowired
    BLPApi blpApi;
    @Test
    public void run() throws  Exception{
        blpApi.getUxIndex("20170130","20170130");
    }
}
