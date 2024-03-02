package pl.currnecy.converter.currencyconverter.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.currnecy.converter.currencyconverter.model.dto.ConvertRequest;
import pl.currnecy.converter.currencyconverter.sheduler.CurrencyData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CurrencyConverter {

    private final CurrencyData currencyData;

    public BigDecimal convert(ConvertRequest convertRequest) {
        BigDecimal startCurrencyRate = currencyData.getCurrencyValue(convertRequest.currency());
        BigDecimal endCurrencyRate = currencyData.getCurrencyValue(convertRequest.finalCurrency());
        BigDecimal amountToConvert = convertRequest.amount();
        return amountToConvert.multiply(startCurrencyRate).divide(endCurrencyRate, RoundingMode.HALF_UP)
                .round(new MathContext(2, RoundingMode.UP));
    }

}
