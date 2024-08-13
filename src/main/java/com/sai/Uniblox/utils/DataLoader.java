package com.sai.Uniblox.utils;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.*;

@UtilityClass
public class DataLoader<T> {

    private static final String PATH = "src/main/java/com/sai/Uniblox/data/";

    public static <T> List<T> readDataFromFile(String type, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> dataList = null;
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            dataList = objectMapper.readValue(new File(String.format("%s%s.json", PATH, type)), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

}
