package com.beitu.saas.risk.domain.platform.tongdun;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class TripleTongdunReportQueryInput extends TripleServiceBaseInput {
    private String reportId;

    public String getReportId() {
        return reportId;
    }

    public TripleTongdunReportQueryInput setReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getMobile() {
        return "";
    }

    @Override
    public String getIdentityNo() {
        return "";
    }
}
