package insurance_service.version1.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumRequestLogger {

    public void getRequestLog(TravelCalculatePremiumRequestV1 request){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String requestLog = mapper.writeValueAsString(request);
            log.info(requestLog);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}