package com.beitu.saas.risk.domain.platform.tongdun;

import java.io.Serializable;
import java.util.List;

public class TripleTongDunReportQueryOutput extends TripleTongdunReportIdOutput {

    private static final long serialVersionUID = -5419496624414778667L;
    //建议描述   分    详情
    private String finalDecision;
    private String finalScore;
    private List<Item> itemList;
    private List<String> itemStrList;

    public List<String> getItemStrList() {
        return itemStrList;
    }

    public TripleTongDunReportQueryOutput setItemStrList(List<String> itemStrList) {
        this.itemStrList = itemStrList;
        return this;
    }


    public String getFinalDecision() {
        return finalDecision;
    }

    public TripleTongDunReportQueryOutput setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
        return this;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public TripleTongDunReportQueryOutput setFinalScore(String finalScore) {
        this.finalScore = finalScore;
        return this;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public TripleTongDunReportQueryOutput setItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public static class Item implements Serializable {
        private static final long serialVersionUID = -5419496624434778667L;

        private String itemName;
        private List<Info> infoList;

        public String getItemName() {
            return itemName;
        }

        public Item setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public List<Info> getInfoList() {
            return infoList;
        }

        public Item setInfoList(List<Info> infoList) {
            this.infoList = infoList;
            return this;
        }
    }

    public static class Info implements Serializable {
        private static final long serialVersionUID = -5419696624414778667L;

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public Info setKey(String key) {
            this.key = key;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Info setValue(String value) {
            this.value = value;
            return this;
        }
    }


}
