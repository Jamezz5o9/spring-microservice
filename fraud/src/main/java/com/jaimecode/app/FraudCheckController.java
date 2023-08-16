package com.jaimecode.app;

import com.jaimecode.app.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster
            (@PathVariable("customerId") Integer customerID){
        boolean isFraudCheck = fraudCheckService.isFraudulentCustomer(customerID);
        log.info("Fraud check request for customer {}", customerID);
        return new FraudCheckResponse(isFraudCheck);
    }
}
