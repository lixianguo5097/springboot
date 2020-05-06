package com.lxg.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lxg.model.easyexcel.EasyUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LXG
 * @date 2020-5-6
 */
public class EasyUserListener extends AnalysisEventListener<EasyUser> {
    private List<EasyUser> easyUserList = new ArrayList<>();
    @Override

    public void invoke(EasyUser easyUser, AnalysisContext analysisContext) {
        System.out.println(easyUser);
        easyUserList.add(easyUser);
    }

    /**
     * 所有数据解析完成了 会来调用
     * 建议在这里使用insertBatch方法批量入库
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(easyUserList);
    }
}
