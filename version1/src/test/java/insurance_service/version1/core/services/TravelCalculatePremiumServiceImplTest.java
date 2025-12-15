package insurance_service.version1.core.services;

import insurance_service.version1.core.underwriting.TravelPremiumCalculationResult;
import insurance_service.version1.core.underwriting.UnderwritingCalculator;
import insurance_service.version1.core.validation.TravelCalculatePremiumRequestValidator;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import insurance_service.version1.dto.v1.TravelCalculatePremiumResponseV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private UnderwritingCalculator underwritingCalculator;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequestV1 request;
    private TravelCalculatePremiumResponseV1 response;

    @BeforeEach
    public void setUp() {
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        request = createRequestWithAllFields();
        when(underwritingCalculator.calculatePremium(request)).thenReturn(calculationResult);
        when(requestValidator.validate(request)).thenReturn(List.of());
        response = service.calculatePremium(request);
    }

    @Test
    public void correctFirstNameFilling(){
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void correctLastNameFilling(){
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void correctAgreementDateTo(){
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void correctAgreementDateFrom(){
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void correctAgreementPrice(){
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    private TravelCalculatePremiumRequestV1 createRequestWithAllFields() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("Stan");
        request.setPersonLastName("Lee");
        request.setAgreementDateFrom(new Date(126, 7, 25));
        request.setAgreementDateTo(new Date(126, 7, 29));
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        return request;
    }
}