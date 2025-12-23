package insurance_service.version2.core.services;

import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreCommand;
import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreResult;
import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.RiskDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.underwriting.TravelPremiumCalculationResult;
import insurance_service.version2.core.underwriting.UnderwritingCalculator;
import insurance_service.version2.core.validation.TravelPolicyValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPolicyValidator policyValidator;
    private final UnderwritingCalculator underwritingCalculator;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = policyValidator.validate(command.getPolicyDTO());
        return errors.isEmpty()
                ? buildResponse(command.getPolicyDTO())
                : buildResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(PolicyDTO policyDTO) {
        calculateRiskPremiumsForAllPersons(policyDTO);

        BigDecimal totalAgreementPremium = calculateTotalAgreementPremium(policyDTO);
        policyDTO.setAgreementPremium(totalAgreementPremium);

        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setPolicyDTO(policyDTO);
        return coreResult;
    }

    private void calculateRiskPremiumsForAllPersons(PolicyDTO policyDTO) {
        policyDTO.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = underwritingCalculator.calculatePremium(policyDTO, person);
            person.setRisks(calculationResult.riskPremiums());
        });
    }

    private BigDecimal calculateTotalAgreementPremium(PolicyDTO policyDTO) {
        return policyDTO.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
