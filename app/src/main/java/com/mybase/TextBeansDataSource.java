/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.mybase;

import android.app.Activity;
import android.widget.Toast;

import com.base.interfaces.ShowData;
import com.base.volley.HttpConnect;
import com.shizhefei.mvc.IDataSource;

import java.util.ArrayList;
import java.util.List;

public class TextBeansDataSource implements IDataSource<List<TextBean>> {

    private int page = 1;

    private Activity context;
    List<TextBean> TextBeans;

    TextBeansDataSource(Activity context) {
        this.context = context;
        HttpConnect.init(context.getApplicationContext());
        TextBeans = new ArrayList<TextBean>();
    }

    @Override
    public List<TextBean> refresh() throws Exception {
        return loadTextBeans(1);
    }

    @Override
    public List<TextBean> loadMore() throws Exception {
        return loadTextBeans(page + 1);
    }

    private List<TextBean> loadTextBeans(int page) throws Exception {
        HttpService.findTranslationList(new ShowData<FindTranslationListBean>() {
            @Override
            public void showData(FindTranslationListBean findTranslationListBean) {
                if (findTranslationListBean.isSuccess()) {
                    for (int i = 0; i < 5; i++) {
                        TextBeans.add(new TextBean("page 1  Java编程思想 " + i));
                    }
                } else
                    Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
            }
        }, new FindTranslationListPostBean("2B06414BC1F54AF4B438DC71DF7CC0A9", "",
                "", "", "2", "8", ""));
        //由于网络延迟，数据加载不能同步，需要将线程延迟
        Thread.sleep(1000);
        this.page = page;
        return TextBeans;
    }

    @Override
    public boolean hasMore() {
        return true;
    }
}
