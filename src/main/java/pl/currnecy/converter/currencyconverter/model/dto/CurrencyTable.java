package pl.currnecy.converter.currencyconverter.model.dto;

import java.util.List;

public record CurrencyTable(String table, String no, String effectiveDate, List<Rates> rates) {
}
