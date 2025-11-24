package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase21 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 21: Country is null, SELECTED_RISKS: [TRAVEL_EVACUATION]")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_21";
    }
}
