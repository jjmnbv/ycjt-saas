package com.beitu.saas.app.application.credit;

import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.credit.client.SaasMultiDebitService;
import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.dto.UserMultiDebitDto;
import com.beitu.saas.intergration.user.enums.UserMultiDebitCodeEnum;
import com.beitu.saas.intergration.user.param.UserMultiDebitParam;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/16
 * Time: 下午5:44
 */
@Component
public class MultiDebitApplication {
    private static final Log LOGGER = LogFactory.getLog(MultiDebitApplication.class);


    @Autowired
    private UserIntegrationService userIntegrationService;
    @Autowired
    private SaasMultiDebitService saasMultiDebitService;
    @Autowired
    private OSSService ossService;

    /**
     * 用户借贷信息统计
     *
     * @param name
     * @param identityNo
     * @param mobile
     * @return
     */
    public String getMultiDebitInfo(String name, String identityNo, String mobile, String merchantCode) {
        UserMultiDebitParam userMultiDebitParam = new UserMultiDebitParam(name, identityNo, mobile);
        UserMultiDebitDto userMultiDebitDto = userIntegrationService.userMultiDebit(userMultiDebitParam, merchantCode);
        if (userMultiDebitDto.getCode() != UserMultiDebitCodeEnum.REQUEST_SUCCESS.getCode()) {
            LOGGER.error("多头借贷统计信息查询失败,原因: {}", userMultiDebitDto.getMsg());
            throw new ApplicationException("多头借贷统计信息查询失败");
        }

        return userMultiDebitDto.getResult();
    }

    /**
     * 获取借贷信息的url
     *
     * @param mobile
     * @return
     */
    public String getMultiDebitJson(String mobile) {
        SaasMultiDebitEntity saasMultiDebitEntity = this.getMultiDebitEntity(mobile);
        if (saasMultiDebitEntity == null) {
            return null;
        }
        return this.getMultiDebitOSSUrl(saasMultiDebitEntity.getUrl());
    }


    /**
     * 获取借贷统计信息
     *
     * @param mobile
     * @return
     */
    public SaasMultiDebitEntity getMultiDebitEntity(String mobile) {
        List<SaasMultiDebitEntity> entityList = saasMultiDebitService.getMultiDebitEntityList(mobile);

        if (entityList.size() == 0) {
            return null;
        }
        return entityList.get(0);
    }

    /**
     * 根据 ossUrl 得到 借贷统计JSON数据
     *
     * @param ossUrl
     * @return 借贷统计JSON数据
     */
    private String getMultiDebitOSSUrl(String ossUrl) {
        if (StringUtils.isNotEmpty(ossUrl)) {
            String multiDebitContent = ossService.getFileContent(ossUrl);//借贷统计数据
            return multiDebitContent;
        }
        return null;
    }
}
