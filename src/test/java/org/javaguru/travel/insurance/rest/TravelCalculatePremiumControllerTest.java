package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;
    @Autowired
    private JsonResponseComparator jsonResponseComparator;

    private void getResponseAndCompare (String receivedResponseJson, String expectResponseJson) throws Exception {
        String response = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(
                                receivedResponseJson))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expect = jsonFileReader.readJsonFromFile(expectResponseJson);
        jsonResponseComparator.areJsonEqual(response,expect);
    }

    @Test
    @DisplayName("Test case 1: firstName is not filled")
    public void firstNameIsNotFilled() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_firstNameIsNotFilled.json",
                "rest/TravelCalculatePremiumResponse_firstNameIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 2: lastName is not filled")
    public void lastNameIsNotFilled() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_lastNameIsNotFilled.json",
                "rest/TravelCalculatePremiumResponse_lastNameIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 3: firstName is null")
    public void firstNameIsNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_firstNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_firstNameIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 4: lastName is null")
    public void lastNameIsNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_lastNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_lastNameIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 5: agreementDateFrom is null")
    public void agreementDateFromIsNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFromIsNull.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFromIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo is null")
    public void agreementDateToIsNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateToIsNull.json",
                "rest/TravelCalculatePremiumResponse_agreementDateToIsNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 7: all fields is not filled")
    public void allFieldsAreNotFilled() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_allFieldsAreNotFilled.json",
                "rest/TravelCalculatePremiumResponse_allFieldsAreNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 8: all fields is null")
    public void allFieldsAreNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_allFieldsIsNull.json",
                "rest/TravelCalculatePremiumResponse_allFieldsAreNotProvided.json"
        );
    }

    @Test
    @DisplayName("Test case 9: all fields is filled")
    public void allFieldsAreFilled() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_allFieldsAreFilled.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }

    @Test
    @DisplayName("Test case 10: agreementDateTo early than agreementDateFrom")
    public void agreementDateToEarlyThanAgreementDateFrom() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_DateToEarlyThanDateFrom.json",
                "rest/TravelCalculatePremiumResponse_DateToNotAfterDateFrom.json"
        );
    }

    @Test
    @DisplayName("Test case 11: agreementDateTo equals agreementDateFrom")
    public void agreementDateToEqualsAgreementDateFrom() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_DateToEqualsDateFrom.json",
                "rest/TravelCalculatePremiumResponse_DateToNotAfterDateFrom.json"
        );
    }

    @Test
    @DisplayName("Test case 12: agreementDateFrom in the past")
    public void agreementDateFromInThePast() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFromInThePast.json",
                "rest/TravelCalculatePremiumResponse_DateFromInThePast.json"
        );
    }

    @Test
    @DisplayName("Test case 13: agreementDateTo in the past")
    public void agreementDateToInThePast() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateToInThePast.json",
                "rest/TravelCalculatePremiumResponse_DateToInThePast.json"
        );
    }

    @Test
    @DisplayName("Test case 14: selectedRisks is null")
    public void selectedRisksIsNull() throws  Exception {
        getResponseAndCompare(
                "rest/TravelCalculatePremiumRequest_selectedRisksIsNull.json",
                "rest/TravelCalculatePremiumResponse_selectedRisksIsNull.json"
        );
    }
}