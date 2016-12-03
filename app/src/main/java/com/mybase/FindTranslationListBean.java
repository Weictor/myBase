package com.mybase;

import java.util.List;

/**
 * Created by 俞智威
 * on 2015-12-07.
 * 16:23
 * Procedure Explain:
 */

public class FindTranslationListBean extends BaseBean {

    private List<FindTranslationListDataBean> data;

    public List<FindTranslationListDataBean> getData() {
        return data;
    }

    public void setData(List<FindTranslationListDataBean> data) {
        this.data = data;
    }
}
