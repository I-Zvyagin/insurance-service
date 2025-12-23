package insurance_service.version2.core.api.command;

import insurance_service.version2.core.api.dto.PolicyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumCoreCommand {

    private PolicyDTO policyDTO;

}
