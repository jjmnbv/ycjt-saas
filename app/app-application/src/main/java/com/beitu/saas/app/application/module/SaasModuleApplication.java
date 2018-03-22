package com.beitu.saas.app.application.module;

import com.beitu.saas.channel.client.SaasModuleItemService;
import com.beitu.saas.channel.client.SaasModuleService;
import com.beitu.saas.channel.domain.SaasModuleItemVo;
import com.beitu.saas.channel.domain.SaasModuleVo;
import com.beitu.saas.channel.entity.SaasModuleEntity;
import com.beitu.saas.channel.entity.SaasModuleItemEntity;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:39
 */
@Component
public class SaasModuleApplication {
    private static final Log LOGGER = LogFactory.getLog(SaasModuleApplication.class);

    @Autowired
    private SaasModuleService saasModuleService;
    @Autowired
    private SaasModuleItemService saasModuleItemService;

    /**
     * 查询所有风控模块
     *
     * @return
     */
    public List<SaasModuleVo> getModuleList() {
        List<SaasModuleVo> moduleVos = new ArrayList<>();
        List<SaasModuleEntity> saasModuleEntityList = saasModuleService.getSaasModuleEntityList();

        saasModuleEntityList.forEach(x -> {
            SaasModuleVo moduleVo = new SaasModuleVo()
                    .setModuleCode(x.getModuleCode())
                    .setModuleDesc(x.getModuleDesc());
            moduleVos.add(moduleVo);
        });
        return moduleVos;
    }

    /**
     * 查询所有风控模块
     *
     * @return
     */
    public List<SaasModuleItemVo> getModuleItemList(String moduleCode) {
        List<SaasModuleItemVo> moduleItemVoList = new ArrayList<>();
        List<SaasModuleItemEntity> moduleItemEntityList = saasModuleItemService.getSaasModuleItemEntityList(moduleCode);

        moduleItemEntityList.forEach(x -> {
            SaasModuleItemVo saasModuleItemVo = new SaasModuleItemVo()
                    .setModuleCode(x.getModuleCode())
                    .setItemCode(x.getItemCode())
                    .setItemDesc(x.getItemDesc());
            moduleItemVoList.add(saasModuleItemVo);
        });
        return moduleItemVoList;
    }

    /**
     * 创建风控模块
     */
    public void createModule(SaasModuleVo saasModuleVo) {
        SaasModuleEntity saasChannelEntity = new SaasModuleEntity();
        BeanUtils.copyProperties(saasModuleVo, saasChannelEntity);

        saasModuleService.create(saasChannelEntity);
        LOGGER.info("== 风控模块配置成功! ==");
    }

    /**
     * 创建风控模块字段
     */
    public void createModuleItem(SaasModuleItemVo saasModuleItemVo) {
        SaasModuleItemEntity saasModuleItemEntity = new SaasModuleItemEntity();
        BeanUtils.copyProperties(saasModuleItemVo, saasModuleItemEntity);

        saasModuleItemService.create(saasModuleItemEntity);
        LOGGER.info("== 创建风控模块字段配置成功! ==");
    }

}
