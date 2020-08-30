package com.amaan.practice;

import com.amaan.practice.observer.JingDongObserver;
import com.amaan.practice.observer.ProductList;
import com.amaan.practice.observer.TaobaoObserver;
import org.junit.Before;
import org.junit.Test;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-27 15:58
 */
public class TestObserver {
    private ProductList observable;
    private TaobaoObserver taobaoObserver;
    private JingDongObserver jingDongObserver;
    @Before
    public void initial(){
        observable = ProductList.getUniqueInstance();
        taobaoObserver = new TaobaoObserver();
        jingDongObserver = new JingDongObserver();
    }
    @Test
    public void testObserver(){
        observable.addObserver(taobaoObserver);
        observable.addObserver(jingDongObserver);
        observable.addProduct("咖啡伴侣");
    }
}
