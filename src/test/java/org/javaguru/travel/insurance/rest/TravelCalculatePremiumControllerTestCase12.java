package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase12 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 12: AgreementDateFrom is less than agreementDateFrom")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_12";
    }
}
