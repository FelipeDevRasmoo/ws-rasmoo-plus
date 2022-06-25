package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;

public interface PaymentInfoService {

    Boolean process(PaymentProcessDto dto);
}
