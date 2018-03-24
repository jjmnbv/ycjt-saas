package com.beitu.saas.rest.controller.auth.response;

import com.beitu.saas.auth.entity.SaasOperationButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaochong
 * @create 2018/3/23 下午5:32
 * @description
 */
public class UserButtonResponse {

    private List<OperationButton> list;

    public UserButtonResponse(List<SaasOperationButton> buttonList,Map<Long,String> parentButton) {
        list = new ArrayList<>();
        buttonList.forEach(saasOperationButton -> {
            boolean anyMatch =false;
            for (OperationButton operationButton :list){
                if (parentButton.containsKey(operationButton.getId())){
                    anyMatch=true;
                    OperationButtonItem operationButtonItem = new OperationButtonItem(saasOperationButton.getId(),saasOperationButton.getName());
                    operationButton.getList().add(operationButtonItem);
                }
            }
            if (!anyMatch){
                OperationButton operationButton = new OperationButton();
                operationButton.setId(saasOperationButton.getPId().longValue());
                operationButton.setName(parentButton.get(saasOperationButton.getPId()));
                List <OperationButtonItem> operationButtonItemList = new ArrayList<>();
                OperationButtonItem operationButtonItem = new OperationButtonItem(saasOperationButton.getId(),saasOperationButton.getName());
                operationButtonItemList.add(operationButtonItem);
                operationButton.setList(operationButtonItemList);
            }

        });
    }

    public List<OperationButton> getList() {
        return list;
    }

    public void setList(List<OperationButton> list) {
        this.list = list;
    }

    class OperationButton{
        private Long id;
        private String name;
        private List<OperationButtonItem> list ;

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

    class OperationButtonItem{
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
