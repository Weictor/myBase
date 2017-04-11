package com.base.interfaces;

/**
 * Created by 俞智威
 * on 2016-02-01.
 * 15:43
 * Procedure Explain:数据
 */
public interface GetData<T> {

    /**
     * @param t   返回参数
     * @param tag 那个接口的判断值
     */
    void getData(T t, int tag);

}
