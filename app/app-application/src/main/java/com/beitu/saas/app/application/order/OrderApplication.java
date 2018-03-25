package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.order.vo.H5OrderListVo;
import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:00
 * @description
 */
@Service
public class OrderApplication {

    @Autowired
    private SaasOrderService saasOrderService;

    public BorrowerOrderApplyStatusEnum getOrderApplyStatus(String borrowerCode, String channelCode) {
          return BorrowerOrderApplyStatusEnum.NO_SUBMIT;
    }

    public SaasOrder createOrder(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb) {

        return new SaasOrder();
    }

    public List<H5OrderListVo> listH5Order(String borrowerCode, String merchantCode) {
        List<H5OrderListVo> results = new ArrayList<>(1);
        H5OrderListVo h5OrderListVo1 = new H5OrderListVo();
        h5OrderListVo1.setOrderNumb("1231232132131");
        h5OrderListVo1.setAmount("1000.00");
        h5OrderListVo1.setRepaymentDt("2018-04-09");
        h5OrderListVo1.setOrderStatus("已核销");
        h5OrderListVo1.setViewType(1);
        results.add(h5OrderListVo1);
        return results;
//        List<SaasOrderVo> saasOrderVoList = saasOrderService.listByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
//        if (CollectionUtils.isEmpty(saasOrderVoList)) {
//            return null;
//        }
//        saasOrderVoList.forEach(saasOrderVo -> {
//            H5OrderListVo h5OrderListVo = new H5OrderListVo();
//            h5OrderListVo.setAmount(saasOrderVo.getRealCapital().toString());
//            h5OrderListVo.setRepaymentDt(DateUtil.convertDateToString(saasOrderVo.getRepaymentDt()));
//            h5OrderListVo.setOrderStatus(saasOrderVo.getOrderStatus() + "");
//            h5OrderListVo.setViewType(1);
//            results.add(h5OrderListVo);
//        });
//        return results;
    }

}
