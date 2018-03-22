package com.beitu.saas.risk.domain.platform.tongdun;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class TripleTongdunReportIdOutput extends TripleServiceBaseOutput {
    private String reportId;

    public String getReportId() {
        return reportId;
    }

    public TripleTongdunReportIdOutput setReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }
}
