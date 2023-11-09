package com.ada.aulamocks;

import java.math.BigDecimal;
import java.util.Date;

public class CotacaoDolar {
    private Date data;

    private BigDecimal cotacaoCompra;

    private BigDecimal cotacaoVenda;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }

    public void setCotacaoCompra(BigDecimal cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }

    public BigDecimal getCotacaoVenda() {
        return cotacaoVenda;
    }

    public void setCotacaoVenda(BigDecimal cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }
}


