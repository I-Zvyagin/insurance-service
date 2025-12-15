package insurance_service.version1.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.core.underwriting.TravelPremiumCalculationResult;
import insurance_service.version1.core.underwriting.UnderwritingCalculator;
import insurance_service.version1.core.validation.TravelCalculatePremiumRequestValidator;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import insurance_service.version1.dto.v1.TravelCalculatePremiumResponseV1;
import insurance_service.version1.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final UnderwritingCalculator underwritingCalculator;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return(errors.isEmpty())
                ? getResponse(request, underwritingCalculator.calculatePremium(request))
                : getResponse(errors);
    }

    private TravelCalculatePremiumResponseV1 getResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private TravelCalculatePremiumResponseV1 getResponse(TravelCalculatePremiumRequestV1 request,
                                                         TravelPremiumCalculationResult result) {
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementPremium(result.totalPremium());
        response.setRisks(result.riskPremiums());
        response.setCountry(request.getCountry());
        response.setBirthDate(request.getBirthDate());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return response;
    }
}
