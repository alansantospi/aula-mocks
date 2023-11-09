package com.ada.aulamocks;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BancoCentralAPI {

    private static final MathContext ROUND = new MathContext(5, RoundingMode.HALF_UP);
    private static final String QUOTATION_DAY_URL = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='{START_DATE}'&$top=100&$format=json";

    public CotacaoDolar buscarCotacao(String startDate) throws UnirestException, ParseException {
        String url = QUOTATION_DAY_URL.replace("{START_DATE}", startDate);

        HttpResponse<com.mashape.unirest.http.JsonNode> response = Unirest.get(url).asJson();
        JSONObject object = response.getBody().getObject();

        if (object == null || object.getJSONArray("value").length() == 0) {
            return null;
        } else {
            JSONArray jsonArray = object.getJSONArray("value");

            JSONObject obj = jsonArray.getJSONObject(0);
            CotacaoDolar quotation = parse(obj);

            return quotation;
        }
    }


    private CotacaoDolar parse(JSONObject object) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date dataHoraCotacao = df.parse(object.getString("dataHoraCotacao"));
        BigDecimal cotacaoCompra = new BigDecimal(object.getDouble("cotacaoCompra")).round(ROUND);
        BigDecimal cotacaoVenda = new BigDecimal(object.getDouble("cotacaoVenda")).round(ROUND);

        CotacaoDolar dq = new CotacaoDolar();
        dq.setData(this.getStartOfDay(dataHoraCotacao));
        dq.setCotacaoCompra(cotacaoCompra);
        dq.setCotacaoVenda(cotacaoVenda);

        return dq;
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(formatter);
    }

    public static Date getStartOfDay(Date date) {
        Calendar start = new GregorianCalendar();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        return start.getTime();
    }

    public static void main(String[] args) throws UnirestException, ParseException {
        CotacaoDolar cotacao = new BancoCentralAPI().buscarCotacao("11-28-2017");
        System.out.println(cotacao);
    }
}
