package com.sai.Uniblox.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class DataLoader {

    public static <T> List<T> readDataFromFile(String type, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> dataList = null;

        try {
            // Construct CollectionType for List<T>
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

            // Read data from the file
            dataList = objectMapper.readValue(new File(String.format("%s.json", type)), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
