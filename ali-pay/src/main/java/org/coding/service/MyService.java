package org.coding.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.coding.pay.MyConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MyService {

    @Resource
    MyConfig myConfig;

    @GetMapping("/pay")
    public String api() throws AlipayApiException {
        AlipayClient alipayClient = alipayClient();
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
////异步接收地址，仅支持http/https，公网可访问
//        request.setNotifyUrl("");
////同步跳转地址，仅支持http/https
//        request.setReturnUrl("");
/******必传参数******/
        JSONObject bizContent = new JSONObject();
//商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", "12332345");
//支付金额，最小值0.01元
        bizContent.put("total_amount", 1.12);
//订单标题，不可使用特殊符号
        bizContent.put("subject", "测试商品");
//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

/******可选参数******/
//bizContent.put("time_expire", "2022-08-01 22:00:00");

//// 商品明细信息，按需传入
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息，按需传入
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

        request.setBizContent(bizContent.toString());
        request.setNotifyUrl("www.baidu.com");
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
// 如果需要返回GET请求，请使用
// AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);
        return pageRedirectionData;
//        System.out.println(pageRedirectionData);
//
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }

    }


    @GetMapping("/refund")
    public String refund() {
        AlipayClient alipayClient = alipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
//        bizContent.put("trade_no", "2024031222001452550502602495");
        bizContent.put("out_trade_no", "12332345");
        bizContent.put("refund_amount", 0.01);
        bizContent.put("trade_no", "");
        bizContent.put("out_request_no", "HZ01RF00232352");

//        JSONArray detail = new JSONArray();
//        detail.add("real_amount");
        // 返回参数选项，按需传入
        JSONArray queryOptions = new JSONArray();
        queryOptions.add("refund_detail_item_list");
        queryOptions.add("buyer_open_id");
//        queryOptions.add("refund_detail_item_list.real_amount");
//        queryOptions.add("fund_type");
        bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response.getBody().toString();
    }


    @GetMapping("/refundResult")
    public String refundRes() {
        AlipayClient alipayClient = alipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "123323");
        bizContent.put("out_request_no", "HZ01RF002324");

        // 返回参数选项，按需传入
        JSONArray queryOptions = new JSONArray();
//        queryOptions.add("refund_detail_item_list");
//        queryOptions.add("refund_amount");
//        queryOptions.add("trade_no");
//        queryOptions.add("gmt_refund_pay");
//        queryOptions.add("refund_royaltys");
        bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response.getBody().toString();
    }





    private AlipayClient alipayClient() {
        return new DefaultAlipayClient(
                myConfig.getServerUrl(),
                myConfig.getAppId(),
                myConfig.getPrivateKey(),
                myConfig.getFormat(),
                myConfig.getCharset(),
                myConfig.getAlipayPublicKey(),
                myConfig.getSignType());
    }
}
