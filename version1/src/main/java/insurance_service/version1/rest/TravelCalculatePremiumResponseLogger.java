package insurance_service.version1.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import insurance_service.version1.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumResponseLogger {

    public void getResponseLog(TravelCalculatePremiumResponseV1 response){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String responseLog = mapper.writeValueAsString(response);
            log.info(responseLog);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}