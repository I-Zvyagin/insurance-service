package insurance_service.version2.core.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Component
public class ErrorCodeUtil {

    private final ResourceLoader resourceLoader;
    private final String errorCodesFilePath;

    private final Properties errorProperties = new Properties();

    ErrorCodeUtil(
            ResourceLoader resourceLoader,
            @Value("${error.codes.file:classpath:errorCodes.properties}") String errorCodesFilePath
    ) {
        this.resourceLoader = resourceLoader;
        this.errorCodesFilePath = errorCodesFilePath;
    }

    @PostConstruct
    public void init() {
        try {
            Resource resource = resourceLoader.getResource(errorCodesFilePath);
            if(!resource.exists()) {
                throw new IllegalStateException("Error codes file not found: " + errorCodesFilePath);
            }
            try (InputStream inputStream = resource.getInputStream()) {
                errorProperties.load(inputStream);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load error codes from " + errorCodesFilePath, e);
        }
    }

    public String getErrorDescription(String errorCode) {
        return errorProperties.getProperty(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorProperties.getProperty(errorCode);
        for(Placeholder placeholder : placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
            errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceholderValue());
        }
        return errorDescription;
    }
}
