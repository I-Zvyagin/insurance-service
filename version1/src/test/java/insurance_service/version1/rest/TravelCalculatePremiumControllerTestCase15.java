package insurance_service.version1.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase15 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 15: SelectedRisks is not supported (one risk)")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_15";
    }
}
