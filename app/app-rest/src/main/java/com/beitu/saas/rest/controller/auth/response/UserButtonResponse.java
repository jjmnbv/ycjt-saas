package com.beitu.saas.rest.controller.auth.response;

import com.beitu.saas.auth.entity.SaasOperationButton;
import com.fqgj.common.api.ResponseData;
import com.timevale.tech.sdk.seal.IFontsLoader;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaochong
 * @create 2018/3/23 下午5:32
 * @description
 */
public class UserButtonResponse implements ResponseData {

    private List<OperationButton> list;

    public UserButtonResponse(List<SaasOperationButton> buttonList, Map<Long, String> parentButton) {
        list = new ArrayList<>();
        parentButton.forEach((k, v) -> {
            OperationButton operationButtonNew = new OperationButton();
            operationButtonNew.setId(k);
            operationButtonNew.setName(v);
            List<OperationButtonItem> operationButtonItemList = new ArrayList<>();
            operationButtonNew.setList(operationButtonItemList);
            list.add(operationButtonNew);
        });

        buttonList.forEach(saasOperationButton -> {
            for (OperationButton operationButton : list) {
                if (saasOperationButton.getId().equals(saasOperationButton.getPId().longValue())) {
                    continue;
                }
                if (operationButton.getId().equals(saasOperationButton.getPId().longValue())) {
                    OperationButtonItem operationButtonItem = new OperationButtonItem(saasOperationButton.getId(), saasOperationButton.getName());
                    operationButton.getList().add(operationButtonItem);
                    continue;
                }
            }
        });
    }

    public List<OperationButton> getList() {
        return list;
    }

    public void setList(List<OperationButton> list) {
        this.list = list;
    }

    class OperationButton {
        private Long id;
        private String name;
        private List<OperationButtonItem> list;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<OperationButtonItem> getList() {
            return list;
        }

        public void setList(List<OperationButtonItem> list) {
            this.list = list;
        }
    }

    class OperationButtonItem {
        private Long id;
        private String name;

        public OperationButtonItem(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
