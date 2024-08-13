package com.sai.Uniblox.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.List;

@UtilityClass // Marks this class as a utility class with only static methods
public class DataLoader {

    // Generic method to read data from a JSON file and map it to a List of type T
    public static <T> List<T> readDataFromFile(String type, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> dataList = null;

        try {
            // Construct CollectionType for List<T>, to specify the type of the collection
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

            // Read data from the JSON file, converting it into the specified list type
            dataList = objectMapper.readValue(new File(String.format("%s.json", type)), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
