package org.consensusj.exchange;

import javax.money.convert.ExchangeRateProvider;

/**
 * ExchangeRateProvider extension that allows an observer to be notified of exchange rate updates
 */
public interface ObservableExchangeRateProvider extends ExchangeRateProvider {
    void registerExchangeRateObserver(CurrencyUnitPair pair, ExchangeRateObserver observer);
    void start();
    void stop();
}
