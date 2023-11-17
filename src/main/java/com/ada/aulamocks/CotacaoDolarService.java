package com.ada.aulamocks;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;

@Service
public class CotacaoDolarService {

    @Autowired
    private BancoCentralAPI bancoCentralAPI;


    public BigDecimal buscarCotacaoCompra(String data) throws UnirestException, ParseException {

        // vai lá na api do banco central e retorna a cotação do dia
        CotacaoDolar cotacaoDolar = bancoCentralAPI.buscarCotacao(data);
        return cotacaoDolar.getCotacaoCompra();

    }


}
