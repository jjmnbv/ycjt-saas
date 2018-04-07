package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.async.DunningAsyncApplication;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jungle
 */
@Service
public class DunningReportApplication {

    @Autowired
    private DunningAsyncApplication dunningAsyncApplication;

    public void generateDunningReport(String merchantCode, String borrowerCode) {
        ThreadPoolUtils.getTaskInstance().submit(() -> dunningAsyncApplication.generateDunningReport(merchantCode, borrowerCode));
    }

}
