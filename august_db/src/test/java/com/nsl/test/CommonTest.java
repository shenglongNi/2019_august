package com.nsl.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class CommonTest {


    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Integer[] param = {1, 2, 3, 4};
        String s = mapper.writeValueAsString(param);
        System.out.println(s);
    }
}