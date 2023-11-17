package com.ada.aulamocks;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CotacaoDolarServiceTest {

    @Mock
    BancoCentralAPI bacen;
    @InjectMocks
    CotacaoDolarService cotacaoDolarService;

    @Test
    public void test() throws UnirestException, ParseException {

        String data = "03-29-2023";
        BigDecimal cotacaoDia = null;

        BigDecimal cotacaoAnterior = new BigDecimal(2.2597);

        CotacaoDolar cotacaoDolar = new CotacaoDolar();
        cotacaoDolar.setCotacaoCompra(cotacaoDia);

        when(bacen.buscarCotacao(data)).thenReturn(cotacaoDolar);

        BigDecimal resultado = cotacaoDolarService.buscarCotacaoCompra(data);

        assertEquals(cotacaoAnterior.compareTo(resultado), 0);

    }



    @Test
    public void testAnoNovo() throws UnirestException, ParseException {

        String data = "01-01-2023";
        BigDecimal cotacaoDia = null;

        BigDecimal cotacaoAnterior = new BigDecimal(5.2171);

        CotacaoDolar cotacaoDolar = new CotacaoDolar();
        cotacaoDolar.setCotacaoCompra(cotacaoDia);

        when(bacen.buscarCotacao(data)).thenReturn(cotacaoDolar);

        BigDecimal resultado = cotacaoDolarService.buscarCotacaoCompra(data);

        assertEquals(cotacaoAnterior.compareTo(resultado), 0);

    }

    @ParameterizedTest
    @MethodSource(value="datas")
    public void testData(String data, BigDecimal cotacaoDia, BigDecimal cotacaoAnterior) throws UnirestException, ParseException {

        // Test
        // Driven
        // Development

        CotacaoDolar cotacaoDolar = new CotacaoDolar();
        cotacaoDolar.setCotacaoCompra(cotacaoDia);

        when(bacen.buscarCotacao(data)).thenReturn(cotacaoDolar);

        BigDecimal resultado = cotacaoDolarService.buscarCotacaoCompra(data);

        assertEquals(cotacaoAnterior.compareTo(resultado), 0);

    }

    public static Collection<?> datas(){
        return Arrays.asList(new Object[][] {
                {"01-01-2023", null, new BigDecimal(5.2171)},
                {"02-21-2023", null, new BigDecimal(5.2006)},
                {"01-01-2023", null, new BigDecimal(5.2171)},
                {"01-01-2023", null, new BigDecimal(5.2171)},
                {"01-01-2023", null, new BigDecimal(5.2171)},
                {"01-01-2023", null, new BigDecimal(5.2171)},
                {"31-01-2023", null, new BigDecimal(5.2171)},


                        // ....
                }

        );
    }



}