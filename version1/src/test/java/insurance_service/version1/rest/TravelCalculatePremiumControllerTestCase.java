package insurance_service.version1.rest;

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
public abstract class TravelCalculatePremiumControllerTestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private JsonResponseComparator jsonResponseComparator;

    protected abstract String getFolderName();

    protected void getResponseAndCompare() throws Exception {

        getResponseAndCompare(
                "rest/" + getFolderName() + "/request.json",
                "rest/" + getFolderName() + "/response.json"
        );
    }
    protected void getResponseAndCompare (String receivedResponseJson, String expectedResponseJson) throws Exception {
        String response = mockMvc.perform(post("/insurance/travel/api/")
                        .content(jsonFileReader.readJsonFromFile(
                                receivedResponseJson))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expect = jsonFileReader.readJsonFromFile(expectedResponseJson);
        jsonResponseComparator.areJsonEqual(response,expect);
    }
}