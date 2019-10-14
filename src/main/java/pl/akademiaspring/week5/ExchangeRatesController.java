package pl.akademiaspring.week5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class ExchangeRatesController {

    private ExchangeRates exchangeRates;
    private List<Currency> listExchangeRates =
            Arrays.asList(Currency.EUR, Currency.USD, Currency.CHF, Currency.GBP);

    public ExchangeRatesController() {
        exchangeRates = getExchangeRate();
        setCurrency(getRandomCurrency());
    }

    private void setCurrency(Currency currency) {
        exchangeRates.setAttempt(1);
        exchangeRates.setCompareResult(1000);
        exchangeRates.setCurrencyUserValue(null);
        switch (currency) {
            case EUR:
                exchangeRates.setCurrencyName(currency.getDescription());
                exchangeRates.setCurrencyValue(exchangeRates.getRates().getEUR()
                        .setScale(2, RoundingMode.HALF_UP));
                break;
            case USD:
                exchangeRates.setCurrencyName(currency.getDescription());
                exchangeRates.setCurrencyValue(exchangeRates.getRates().getUSD()
                        .setScale(2, RoundingMode.HALF_UP));
                break;
            case CHF:
                exchangeRates.setCurrencyName(currency.getDescription());
                exchangeRates.setCurrencyValue(exchangeRates.getRates().getCHF()
                        .setScale(2, RoundingMode.HALF_UP));
                break;
            case GBP:
                exchangeRates.setCurrencyName(currency.getDescription());
                exchangeRates.setCurrencyValue(exchangeRates.getRates().getGBP()
                        .setScale(2, RoundingMode.HALF_UP));
                break;
        }
    }

    private ExchangeRates getExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRates exchangeRates = restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=PLN&symbols=USD,EUR,CHF,GBP",
                ExchangeRates.class);

        return exchangeRates;
    }

    private Currency getRandomCurrency() {
        Random generator = new Random();
        int index = generator.nextInt(listExchangeRates.size());

        return listExchangeRates.get(index);
    }

    @GetMapping("/exchange-rate")
    public String getExchangeRate(Model model) {

        model.addAttribute("exchangeRates", exchangeRates);

        return "exchangeRatesView";
    }

    @GetMapping("/check-currency-value")
    public String checkCurrencyValue(@ModelAttribute("exchangeRates") ExchangeRates exchangeRates, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("exchangeRates", exchangeRates);

            return "redirect:/exchange-rate";
        }

        if (this.exchangeRates.getCompareResult() == 0) {
            setCurrency(getRandomCurrency());
            return "redirect:/exchange-rate";
        }

        if (this.exchangeRates.getCurrencyValue().compareTo(exchangeRates.getCurrencyUserValue()) == 0) {
            this.exchangeRates.setCompareResult(0);
        } else if(this.exchangeRates.getCurrencyValue().compareTo(exchangeRates.getCurrencyUserValue()) == 1) {
            this.exchangeRates.setAttempt(this.exchangeRates.getAttempt() + 1);
            this.exchangeRates.setCompareResult(1);
        } else {
            this.exchangeRates.setAttempt(this.exchangeRates.getAttempt() + 1);
            this.exchangeRates.setCompareResult(-1);
        }

        this.exchangeRates.setCurrencyUserValue(exchangeRates.getCurrencyUserValue());

        return "redirect:/exchange-rate";
    }
}

