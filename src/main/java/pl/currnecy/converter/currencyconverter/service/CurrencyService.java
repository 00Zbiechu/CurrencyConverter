package pl.currnecy.converter.currencyconverter.service;

import pl.currnecy.converter.currencyconverter.model.dto.ConvertRequest;

import java.math.BigDecimal;

public interface CurrencyService {

    BigDecimal convert(ConvertRequest convertRequest);
}
