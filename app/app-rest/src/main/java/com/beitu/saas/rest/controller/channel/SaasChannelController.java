package com.beitu.saas.rest.controller.channel;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.channel.vo.ChannelStatDetailVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelDetailVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.param.SaasChannelRiskSettingsParam;
import com.beitu.saas.common.utils.ShortUrlUtil;
import com.beitu.saas.rest.controller.channel.request.*;
import com.beitu.saas.rest.controller.channel.response.SaasChannelDetailResponse;
import com.beitu.saas.rest.controller.channel.response.SaasChannelListResponse;
import com.beitu.saas.rest.controller.channel.response.SaasChannelStatListResponse;
import com.beitu.saas.rest.controller.channel.response.SaasMerchantAdminResponse;
import com.fqgj.common.api.ApiResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.response.ModuleResponse;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:03
 */
@Api(description = "渠道相关接口")
@RestController
@RequestMapping("/channel")
public class SaasChannelController {
    private static final Log LOGGER = LogFactory.getLog(SaasChannelController.class);


    @Autowired
    private SaasChannelApplication saasChannelApplication;
    @Autowired
    private SaasChannelService saasChannelService;

    /**
     * 新建渠道
     *
     * @param saasChannelRequestParam
     * @return
     */
    @RequestMapping(value = "/addOrUpdateChannel", method = RequestMethod.POST)
    @ApiOperation(value = "新建/编辑渠道", response = Response.class)
    public Response addOrUpdateChannel(@RequestBody SaasChannelRequestParam saasChannelRequestParam) {

        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        List<SaasChannelRiskSettingsParam> settingsVos = new ArrayList<>();
        boolean setModule = CollectionUtils.isNotEmpty(saasChannelRequestParam.getSaasModuleRequestParams());
        boolean setModuleItem = saasChannelRequestParam.getSaasModuleItemRequestParams().size() == 1 && !StringUtils.isEmpty(saasChannelRequestParam.getSaasModuleItemRequestParams().get(0).getItemCode());

        if (setModuleItem) {
            saasChannelRequestParam.getSaasModuleItemRequestParams().stream().forEach(x -> {
                SaasChannelRiskSettingsParam saasChannelRiskSettingsVo = new SaasChannelRiskSettingsParam();
                saasChannelRiskSettingsVo.setModuleCode(x.getModuleCode())
                        .setItemCode(x.getItemCode())
                        .setRequired(x.getRequired());
                settingsVos.add(saasChannelRiskSettingsVo);
            });
        } else if (setModule) {
            saasChannelRequestParam.getSaasModuleRequestParams().stream().forEach(x -> {
                SaasChannelRiskSettingsParam settingsVo = new SaasChannelRiskSettingsParam();
                settingsVo.setModuleCode(x.getModuleCode())
                        .setRequired(x.getRequired());
                settingsVos.add(settingsVo);
            });
        }

        SaasChannelParam saasChannelParam = new SaasChannelParam();
        saasChannelParam.setMerchantCode(merchantCode);
        BeanUtils.copyProperties(saasChannelRequestParam, saasChannelParam);

        try {
            saasChannelApplication.addOrUpdateChannel(saasChannelParam, settingsVos);
        } catch (Exception e) {
            LOGGER.error("==  创建渠道失败, 机构号:{}, 渠道名称:{} ,失败原因:{}  ==", merchantCode, saasChannelRequestParam.getChannelName(), e);
            return Response.error(null, ChannelErrorCodeEnum.CHANNEL_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }

    /**
     * 机构下所有管理员查询
     *
     * @return
     */
    @RequestMapping(value = "/merchantAdminList", method = RequestMethod.POST)
    @ApiOperation(value = "获取机构下所有管理员", response = SaasMerchantAdminResponse.class)
    public Response getMerchantAdminList() {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        List<SaasAdmin> saasAdminList = saasChannelApplication.getSaasAdminListByMerchantCode(merchantCode);
        return Response.ok().putData(new SaasMerchantAdminResponse(saasAdminList));
    }

    /**
     * 获取单个渠道详情
     */
    @RequestMapping(value = "/getChannel/{channelCode}", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个渠道详情", response = SaasChannelDetailResponse.class)
    public Response getChannel(@PathVariable(value = "channelCode") String channelCode) {
        SaasChannelDetailVo saasChannelDetail = saasChannelApplication.getSaasChannelDetail(channelCode);
        return Response.ok().putData(new SaasChannelDetailResponse(saasChannelDetail));
    }


    /**
     * 获取渠道列表
     *
     * @return
     */
    @RequestMapping(value = "/channelList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasChannelListResponse.class)
    public ModuleResponse getChannelList(@RequestBody SaasChannelQueryRequestParam saasChannelQueryRequestParam, Page page) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        SaasChannelParam saasChannelParam = new SaasChannelParam();
        BeanUtils.copyProperties(saasChannelQueryRequestParam, saasChannelParam);
        saasChannelParam.setMerchantCode(merchantCode);
        List<SaasChannelVo> saasChannelList = saasChannelApplication.getSaasChannelList(saasChannelParam, page);

        SaasChannelListResponse saasChannelListResponse = new SaasChannelListResponse(saasChannelList);
        return new ModuleResponse<>(saasChannelListResponse, page);
    }

    /**
     * 获取单个渠道详情
     */
//    @RequestMapping(value = "/shortUrl", method = RequestMethod.POST)
//    @ApiOperation(value = "获取单个短链接", response = Response.class)
    public Response getShortUrl(HttpServletResponse resp, String url) {
        String shortUrl = ShortUrlUtil.generateShortUrl(url);
        return Response.ok().putData(shortUrl);
    }

    /**
     * 二维码请求
     *
     * @param resp
     * @param qrUrl
     * @throws IOException
     */
    @RequestMapping(value = "/getQRcode", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取二维码", response = ApiResponse.class)
    @SignIgnore
    @VisitorAccessible
    public ApiResponse getQRcode(HttpServletResponse resp, String qrUrl) throws IOException {
        if (qrUrl != null && !"".equals(qrUrl)) {
            resp.setHeader("Content-type", "image/jpeg");
            resp.setHeader("Content-disposition", "attachment;filename=code.jpeg");
            OutputStream stream = null;
            try {
                int width = 400;//图片的宽度
                int height = 400;//高度
                stream = resp.getOutputStream();

                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(qrUrl, BarcodeFormat.QR_CODE, height, width);
                MatrixToImageWriter.writeToStream(m, "jpeg", stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
        return new ApiResponse();
    }

    /**
     * 获取渠道统计列表
     *
     * @return
     */
    @RequestMapping(value = "/channelStatList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道统计列表", response = SaasChannelListResponse.class)
    public ModuleResponse getChannelStatList(@RequestBody ChannelStatQueryRequestParam channelStatQueryRequestParam, Page page) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        ChannelStatQueryParam channelStatQueryParam = new ChannelStatQueryParam();
        BeanUtils.copyProperties(channelStatQueryRequestParam, channelStatQueryParam);
        channelStatQueryParam.setMerchantCode(merchantCode);
        List<ChannelStatDetailVo> voList = saasChannelApplication.getChannelStatByPage(channelStatQueryParam, page);

        SaasChannelStatListResponse saasChannelStatListResponse = new SaasChannelStatListResponse(voList);
        return new ModuleResponse<>(saasChannelStatListResponse, page);
    }


    /**
     * 禁用/启用 渠道操作
     */
    @RequestMapping(value = "/operateChannel", method = RequestMethod.POST)
    @ApiOperation(value = "禁用/启用", response = Response.class)
    public Response operateChannel(@RequestBody SaasOperateChannelRequestParam saasOperateChannelRequestParam) {
        saasChannelService.operateSaasChannel(saasOperateChannelRequestParam.getChannelCode(), saasOperateChannelRequestParam.getStatus());
        return Response.ok().putData("操作成功");
    }
}
