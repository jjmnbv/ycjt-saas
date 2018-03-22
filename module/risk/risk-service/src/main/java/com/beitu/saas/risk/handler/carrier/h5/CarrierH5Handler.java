package com.beitu.saas.risk.handler.carrier.h5;

import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5ResultVo;
import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5Vo;
import com.beitu.saas.risk.handler.carrier.h5.tianji.vo.CarrierTianjiCacheVo;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/11/6 0006 下午 3:54
 */
public interface CarrierH5Handler {

    /**
     * 获取运营商H5链接
     *
     * @param carrierH5Vo
     * @return
     */
    CarrierH5ResultVo requestCarrierUrl(CarrierH5Vo carrierH5Vo);


    /**
     * 根据taskId 获取信息
     *
     * @param taskId
     * @return
     */
    CarrierTianjiCacheVo getCacheByTaskId(String taskId);

}
