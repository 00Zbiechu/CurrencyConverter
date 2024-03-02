package pl.currnecy.converter.currencyconverter.model.dto;

import java.math.BigDecimal;

public record ConvertRequest(BigDecimal amount, String currency, String finalCurrency) {
}
