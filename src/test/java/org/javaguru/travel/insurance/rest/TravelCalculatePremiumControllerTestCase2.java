package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase2 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 2: PersonFirstName is null")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_2";
    }
}
