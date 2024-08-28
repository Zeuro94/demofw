package org.testautomation.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testautomation.utility.reading.ClientRead;
import org.testautomation.utility.reading.ReadFromFile;
import org.testautomation.utility.reading.ReadService;

import java.io.IOException;

public class JsonUtility {

    private JsonUtility() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type){
        ReadService readService = new ReadFromFile(path);
        ClientRead clientRead = new ClientRead(readService);
        try {
            return objectMapper.readValue(clientRead.createReader(), type);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

