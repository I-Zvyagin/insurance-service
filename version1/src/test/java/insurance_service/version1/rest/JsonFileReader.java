package insurance_service.version1.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Component
public class JsonFileReader {

    public String readJsonFromFile(String filePath) {
        try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()){
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
