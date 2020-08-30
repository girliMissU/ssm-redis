package com.amaan.practice.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-27 15:56
 */
public class TaobaoObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String newProduct = (String)arg;
        System.out.println("发送新产品：【"+newProduct+"】同步到淘宝商城");
    }
}
