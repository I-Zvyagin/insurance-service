package insurance_service.version1.rest;

import org.springframework.stereotype.Component;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@Component
public class JsonResponseComparator {
    public void areJsonEqual(String received, String expect) {
        assertJson(received)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expect);
    }
}
