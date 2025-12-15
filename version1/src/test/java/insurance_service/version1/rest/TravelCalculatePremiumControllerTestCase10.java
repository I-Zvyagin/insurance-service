package insurance_service.version1.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase10 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 10: AgreementDateFrom is in the past")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_10";
    }
}
