package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;

import java.util.List;

/**
 * Created by Hermit on 6/3/17.
 */
public class TongdunReportVo implements ResponseData {

    private String finalScore; //同盾分数

    private String finalDecision; //同盾结论

    private Boolean blacked;//是否黑名单

    private List<String> blackItems; //黑名单项

    private List<BorrowRecord> borrowRecords; //借款记录

//    private List<String> threeMonthRelationInfo;//三个月关联信息


    public class BorrowRecord {
        private String item;
        private String detailCount;
        private List<String> details;
        private Integer sort;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getDetailCount() {
            return detailCount;
        }

        public void setDetailCount(String detailCount) {
            this.detailCount = detailCount;
        }

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
    }


    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }

    public Boolean getBlacked() {
        return blacked;
    }

    public void setBlacked(Boolean blacked) {
        this.blacked = blacked;
    }

    public List<String> getBlackItems() {
        return blackItems;
    }

    public void setBlackItems(List<String> blackItems) {
        this.blackItems = blackItems;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

//    public List<String> getThreeMonthRelationInfo() {
//        return threeMonthRelationInfo;
//    }
//
//    public void setThreeMonthRelationInfo(List<String> threeMonthRelationInfo) {
//        this.threeMonthRelationInfo = threeMonthRelationInfo;
//    }
}
