package insurance_service.version1.rest;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    private final Stopwatch stopwatch;

    public TravelCalculatePremiumRequestExecutionTimeLogger() {
        this.stopwatch = Stopwatch.createStarted();
    }

    public void getExecutionTimeLog() {
        stopwatch.stop();
        long executionTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        log.info("Request processing time (ms): {}", executionTime);
    }
}