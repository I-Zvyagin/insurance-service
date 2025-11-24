package org.javaguru.travel.insurance.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumResponseLogger responseLogger;
	private final TravelCalculatePremiumRequestLogger requestLogger;
	private final TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		TravelCalculatePremiumRequestExecutionTimeLogger timeLogger = new TravelCalculatePremiumRequestExecutionTimeLogger();
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		requestLogger.getRequestLog(request);
		responseLogger.getResponseLog(response);
		timeLogger.getExecutionTimeLog();
		return response;
	}
}