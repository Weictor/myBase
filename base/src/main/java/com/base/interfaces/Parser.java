package com.base.interfaces;

/**
 * Created by 俞智威
 * on 2016-02-01.
 * 15:33
 * Procedure Explain:数据解析器
 */
public interface Parser<T> {

    T parse(String dataString);

}
