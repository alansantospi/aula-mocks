package com.ada.aulamocks;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CotacaoDolarServiceTest {

    @Autowired
    CotacaoDolarService cotacaoDolarService;

    @Test
    public void test() throws UnirestException, ParseException {

        BigDecimal esperado = new BigDecimal(2.2597);
        esperado.setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal resultado = cotacaoDolarService.buscarCotacaoCompra("03-28-2014");

        assertEquals(esperado, resultado);



    }


}