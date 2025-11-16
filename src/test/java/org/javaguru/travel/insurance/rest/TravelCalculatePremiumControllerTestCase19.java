package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase19 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 19: All fields not provided expect RISK_TYPE")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_19";
    }
}
