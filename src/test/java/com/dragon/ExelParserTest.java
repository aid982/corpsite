package com.dragon;

/**
 * Created by osetskiy on 26.12.2016.
 */
import com.dragon.Service.ExelParsing;
import com.dragon.model.IndexUX;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class ExelParserTest {
    @Test
    public void run() throws  Exception{
        IndexUX ux = new IndexUX();
        Method method = ux.getClass().getMethod("setDate",Date.class);
        method.invoke(ux,new Date());
        System.out.println(ux);


        //System.out.println(ExelParsing.parseIndexUX());


    }
}
