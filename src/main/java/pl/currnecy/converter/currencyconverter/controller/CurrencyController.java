package pl.currnecy.converter.currencyconverter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.currnecy.converter.currencyconverter.model.dto.ConvertRequest;
import pl.currnecy.converter.currencyconverter.service.CurrencyService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/convert")
    public ResponseEntity<BigDecimal> convertCurrency(@RequestBody ConvertRequest convertRequest) {
        return new ResponseEntity<>(currencyService.convert(convertRequest), HttpStatus.OK);
    }
}
