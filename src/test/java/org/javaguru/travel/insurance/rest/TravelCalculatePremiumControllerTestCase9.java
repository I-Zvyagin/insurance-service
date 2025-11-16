package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase9 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 9: AgreementDateTo is null")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_9";
    }
}
