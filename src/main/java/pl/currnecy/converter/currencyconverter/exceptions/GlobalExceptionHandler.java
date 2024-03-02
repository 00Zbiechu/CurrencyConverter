package pl.currnecy.converter.currencyconverter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.currnecy.converter.currencyconverter.exceptions.exception.SymbolCurrencyNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SymbolCurrencyNotFoundException.class)
    public ResponseEntity<String> handleSymbolCurrencyNotFoundException(SymbolCurrencyNotFoundException symbolCurrencyNotFoundException) {
        return new ResponseEntity<>(symbolCurrencyNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
