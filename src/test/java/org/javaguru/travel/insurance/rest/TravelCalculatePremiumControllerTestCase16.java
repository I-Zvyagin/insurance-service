package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase16 extends TravelCalculatePremiumControllerTestCase {

    @Test
    @DisplayName("Test case 16: SelectedRisks is not supported (two risks)")
    public void execute() throws Exception {
        getResponseAndCompare();
    }

    @Override
    protected String getFolderName() {
        return "test_case_16";
    }
}
