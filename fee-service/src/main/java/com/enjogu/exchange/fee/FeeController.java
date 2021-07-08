package com.enjogu.exchange.fee;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.enjogu.exchange.fee.rest.spec.serviceapi.v1.fees.ExchangeRateFeesApi;
import com.enjogu.exchange.fee.rest.spec.v1.fees.ExchangeRateFeesGetResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@RestController
public class FeeController implements ExchangeRateFeesApi {

    @Override
    public ExchangeRateFeesGetResponseBody getExchangeRateFees(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws BadRequestException, InternalServerErrorException {
        return new ExchangeRateFeesGetResponseBody().withFee(new BigDecimal("1.1279"));
    }
}