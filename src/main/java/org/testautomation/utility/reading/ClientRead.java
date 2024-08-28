package org.testautomation.utility.reading;

import java.io.IOException;

public class ClientRead {

    private final ReadService readService;

    public ClientRead(ReadService readService) {
        this.readService = readService;
    }

    public String createReader() throws IOException {
        return readService.read();
    }
}
