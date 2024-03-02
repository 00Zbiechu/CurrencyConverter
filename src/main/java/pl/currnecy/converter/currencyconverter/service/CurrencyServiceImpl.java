package pl.currnecy.converter.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.currnecy.converter.currencyconverter.currency.CurrencyConverter;
import pl.currnecy.converter.currencyconverter.model.dto.ConvertRequest;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyConverter currencyConverter;

    @Override
    public BigDecimal convert(ConvertRequest convertRequest) {
        return currencyConverter.convert(convertRequest);
    }
}
