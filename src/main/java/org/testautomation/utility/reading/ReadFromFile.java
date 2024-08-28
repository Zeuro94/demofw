package org.testautomation.utility.reading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ReadFromFile implements ReadService {

    private final InputStream inputStream;
    private static final Logger log = LoggerFactory.getLogger(ReadFromFile.class);

    public ReadFromFile(String path) {
        inputStream = getResource(path);
    }

    @Override
    public String read() {
        byte[] buffer = new byte[1024];
        StringBuilder content = new StringBuilder();
        while (true) {
            int bytesRead;
            try {
                if ((bytesRead = inputStream.read(buffer)) == -1) break;
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            content.append(new String(buffer, 0, bytesRead));
        }
        return content.toString();
    }

    public static InputStream getResource(String path) {
        log.info("reading resource from location: {}", path);
        InputStream stream = ReadFromFile.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream)) {
            return stream;
        }
        try {
            return Files.newInputStream(Path.of(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
