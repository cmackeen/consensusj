package org.consensusj.exchange

import org.javamoney.moneta.Money
import spock.lang.Shared
import spock.lang.Specification

import javax.money.Monetary
import javax.money.MonetaryAmount


/**
 * Base class for testing exchange rate providers
 */
abstract class AbstractXChangeRateProviderSpec extends Specification {
    @Shared BaseXChangeExchangeRateProvider provider

    def "can get an exchange rate via currency strings"() {
        when:
        def rate = provider.getExchangeRate("BTC", "USD")

        then:
        rate.factor.numberValue(BigDecimal.class) > 0
    }

    def "can get an exchange rate via currencyunits"() {
        given:
        def btc = Monetary.getCurrency("BTC")
        def usd = Monetary.getCurrency("USD")

        when:
        def rate = provider.getExchangeRate(btc, usd)

        then:
        rate.factor.numberValue(BigDecimal.class) > 0
    }

    def "can get a working CurrencyConversion"() {
        when:
        def conversion = provider.getCurrencyConversion("USD")

        then:
        conversion != null

        when:
        MonetaryAmount amountInBTC = Money.of(1, "BTC")
        MonetaryAmount amountInUSD = amountInBTC.with(conversion)

        then:
        amountInUSD.number.doubleValue() > 400
        amountInUSD.currency.currencyCode == "USD"
    }

    def "can list currency codes"() {
        when:
        def markets = provider.exchange.exchangeMetaData.currencyPairs

        then:
        markets.size() > 0
    }

    def setup() {
        provider = createProvider()
        provider.start()
    }

    abstract BaseXChangeExchangeRateProvider createProvider()
}