package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase11 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 11: AgreementDateTo is null")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_11";
    }
}
