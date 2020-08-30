package com.amaan.practice.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 佛祖保佑，永无BUG
 * 被观察者，产品列表
 * @author AMAAN
 * SSMR
 * 2020-08-27 15:44
 */
public class ProductList extends Observable {

    //产品列表
    private List<String> productList = null;

    //声明为 private 避免调用默认构造方法创建对象
    private ProductList() {
    }
    // 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
    private static class SingletonHolder {
        private static final ProductList INSTANCE = new ProductList();
    }
    public static ProductList getUniqueInstance() {
        SingletonHolder.INSTANCE.productList = new ArrayList<>();
        return SingletonHolder.INSTANCE;
    }

    /**
     * 增加观察者（电商接口）
     * 从父类继承的addObserver(Observer observer)也是可以用的
     * @param observer
     */
    public void addProductListObserver(Observer observer){
        this.addObserver(observer);
    }

    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增产品："+newProduct);
        /*
        设置被观察者对象发生了变化
        通知观察者，并传递新产品
         */
        this.setChanged();
        this.notifyObservers(newProduct);
    }

}
