package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase20 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 20: All fields not provided")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_20";
    }
}
