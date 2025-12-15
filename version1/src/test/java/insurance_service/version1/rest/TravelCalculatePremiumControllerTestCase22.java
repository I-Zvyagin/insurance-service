package insurance_service.version1.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase22 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 22: Country is empty, SELECTED_RISKS: [TRAVEL_EVACUATION]")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_22";
    }
}
