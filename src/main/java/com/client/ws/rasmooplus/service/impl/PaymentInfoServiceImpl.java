package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.service.PaymentInfoService;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {


    PaymentInfoServiceImpl(){

    }

    @Override
    public Boolean process(PaymentProcessDto dto) {
        //verifica usuario por id
        //salvar informacoes de pagamento
        //cria ou atualiza usuario raspay
        //cria o pedido de pagamento
        //processa o pagamento
        //enviar email de criacao de conta
        //retorna o sucesso ou nao do pagamento

        return null;
    }
}
