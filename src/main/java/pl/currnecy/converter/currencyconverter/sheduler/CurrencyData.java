package pl.currnecy.converter.currencyconverter.sheduler;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import pl.currnecy.converter.currencyconverter.exceptions.exception.SymbolCurrencyNotFoundException;
import pl.currnecy.converter.currencyconverter.model.dto.CurrencyTable;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
@Setter
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyData {

    private final RestTemplate restTemplate;

    @Value("${nbp.currency.url}")
    private String currencyDatatableUrl;

    private CurrencyTable[] currencyTables;

    @PostConstruct
    public void onStartup() {
        setCurrencyTableFromExternalResource();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void onSchedule() {
        setCurrencyTableFromExternalResource();
    }

    private void setCurrencyTableFromExternalResource() {
        String[] tableTypes = {"a/", "b/", "c/"};
        for (String tableType : tableTypes) {
            CurrencyTable[] currencyTable = restTemplate.getForObject(getCurrencyDatatableUrl() + tableType, CurrencyTable[].class);
            setCurrencyTables(ArrayUtils.addAll(getCurrencyTables(), currencyTable));
        }
    }

    public BigDecimal getCurrencyValue(String currency) {
        return Arrays.stream(currencyTables)
                .flatMap(table -> table.rates().stream())
                .filter(rate -> rate.code().equals(currency))
                .map(rate -> BigDecimal.valueOf(rate.mid()))
                .findFirst()
                .orElseThrow(() -> new SymbolCurrencyNotFoundException("Currency with " + currency + " symbol not found"));
    }
}
