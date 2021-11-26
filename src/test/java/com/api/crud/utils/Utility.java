package com.api.crud.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class Utility {
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
