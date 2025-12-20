package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.util.ErrorCodeUtil;
import insurance_service.version2.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {
    @InjectMocks
    private ValidationErrorFactory errorFactory;
    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @Test
    public void shouldReturnErrorDTO() {
        String expectedErrorCode = "code";
        String expectedDescription = "description";

        when(errorCodeUtil.getErrorDescription(expectedErrorCode)).thenReturn(expectedDescription);

        ValidationErrorDTO receivedDTO = errorFactory.buildError(expectedErrorCode);

        assertEquals(expectedErrorCode, receivedDTO.getErrorCode());
        assertEquals(expectedDescription, receivedDTO.getDescription());
    }

    @Test
    public void shouldReturnErrorDTOWithPlaceholder() {
        String expectedErrorCode = "code";
        String expectedDescription = "description";
        Placeholder placeholder = new Placeholder("name", "value");

        when(errorCodeUtil.getErrorDescription(expectedErrorCode, List.of(placeholder))).thenReturn(expectedDescription);

        ValidationErrorDTO receivedDTO = errorFactory.buildError(expectedErrorCode, List.of(placeholder));

        assertEquals(expectedErrorCode, receivedDTO.getErrorCode());
        assertEquals(expectedDescription, receivedDTO.getDescription());
    }
}