package com.coding.okclient;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;



import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;



import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ClientTest {
    public static void main(String[] args) {


    }
}

class GetExample {
    final OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}

class PostExample {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    final OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    public static void main(String[] args) throws IOException {
        PostExample example = new PostExample();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);
    }
}



class SegmentPortraitPic {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    final static OkHttpClient client = new OkHttpClient();
    public static void main(String [] args) {
/*        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential("SecretId", "SecretKey");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("bda.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            BdaClient client = new BdaClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SegmentPortraitPicRequest req = new SegmentPortraitPicRequest();

            // 返回的resp是一个SegmentPortraitPicResponse的实例，与请求对象对应
            SegmentPortraitPicResponse resp = client.SegmentPortraitPic(req);
            // 输出json格式的字符串回包
            System.out.println(SegmentPortraitPicResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }*/




        String json = "{\n" +
                "  \"Action\": \"SegmentPortraitPic\",\n" +
                "  \"Version\": \"2020-03-24\",\n" +
                "  \"Region\": \"ap-shanghai\",\n" +
                "  \"Url\": \"https://c-ssl.duitang.com/uploads/blog/202311/25/DWS5xlyzidVYQJW.jpg\",\n" +
                "  \"RspImgType\": \"url\",\n" +
                "  \"Scene\": \"GEN\"\n" +
                "}";

        String SecretId = "AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC";

        String SecretKey = "LRJxNbcSwCD0Zv270fBUbU9ZljSzINFs";

        String url = "https://bda.tencentcloudapi.com";


        String curl = "curl -H \"X-TC-Timestamp: 1703673734\" -H \"X-TC-Region: ap-shanghai\" -H \"Content-Type: application/json\" -H \"Authorization: TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-27/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=86328bda2827cfdcc5395a8ec52cec565e60155138a5dc442663ec2e207e7980\" -H \"X-TC-RequestClient: APIExplorer\" -H \"X-TC-Action: SegmentPortraitPic\" -H \"X-TC-Version: 2020-03-24\" -H \"Host: bda.tencentcloudapi.com\" -H \"X-TC-Language: zh-CN\"  -d '{\"Url\":\"https://c-ssl.duitang.com/uploads/blog/202311/25/DWS5xlyzidVYQJW.jpg\",\"RspImgType\":\"url\",\"Scene\":\"GEN\"}' 'https://bda.tencentcloudapi.com/'";

/*        curl -H "X-TC-Timestamp: 1703673734"
            -H "X-TC-Region: ap-shanghai"
            -H "Content-Type: application/json"
            -H "Authorization: TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-27/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=86328bda2827cfdcc5395a8ec52cec565e60155138a5dc442663ec2e207e7980"
            -H "X-TC-RequestClient: APIExplorer"
            -H "X-TC-Action: SegmentPortraitPic"
            -H "X-TC-Version: 2020-03-24"
            -H "Host: bda.tencentcloudapi.com"
            -H "X-TC-Language: zh-CN"
            -d '{"Url":"https://c-ssl.duitang.com/uploads/blog/202311/25/DWS5xlyzidVYQJW.jpg","RspImgType":"url","Scene":"GEN"}'
            'https://bda.tencentcloudapi.com/'
            */
        //构建请求体
        Map<String, Object> map = new HashMap<>();
/*        map.put("X-TC-Timestamp", Long.valueOf(1703694707L).toString());
        map.put("X-TC-Region", "ap-shanghai");
        map.put("Content-Type", "application/json");
        String sec = "TC3-HMAC-SHA256";
        String credential = "Credential";
//        String curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String curDate = "2023-12-27";
        String service = "bda";
        String tc3_request = "tc3_request";
        String signedHeaders = "SignedHeaders=content-type;host;x-tc-action";
        String signature = "Signature=c3337f2f417a63d7a590f00fa182dd8cfc8b0c469aadde3a5c4d5a10e5c91c88";
//        String Authorization = sec + " " + credential + "=" + SecretId + "/" + curDate + "/" + service + "/" + tc3_request + ", " + signedHeaders + "," + signature;
        String Authorization =  "TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-27/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=b63f484a901fe3695c56e1274ff044d4b45eac8fb83f8e8497b9e9c4b68f363d";
        map.put("Authorization", Authorization);
        map.put("X-TC-RequestClient", "APIExplorer");
        map.put("X-TC-Action", "SegmentPortraitPic");
        map.put("X-TC-Version", "2020-03-24");
        map.put("Host", "bda.tencentcloudapi.com");
        map.put("X-TC-Language", "zh-CN");*/
//        System.out.println(Authorization);
        System.out.println(System.currentTimeMillis()/1000);


        map.put("X-TC-Timestamp", Long.valueOf(System.currentTimeMillis()/1000).toString());
        map.put("X-TC-Region", "ap-shanghai");
        map.put("Content-Type", "application/json");
/*        String sec = "TC3-HMAC-SHA256";
        String credential = "Credential";
//        String curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String curDate = "2023-12-28";
        String service = "bda";
        String tc3_request = "tc3_request";
        String signedHeaders = "SignedHeaders=content-type;host;x-tc-action";
        String signature = "Signature=4b72761b4b0111eca5b39b3fb7c945057f1d342b6e20accaa9fd1a4864f6b4f1";*/
//        String Authorization = sec + " " + credential + "=" + SecretId + "/" + curDate + "/" + service + "/" + tc3_request + ", " + signedHeaders + "," + signature;
        String Authorization = "TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=1cce23ca5de1a8620540f5a1b88189334728269922bca83ff09babbfbbfd2b1f";
        map.put("Authorization", Authorization);
        map.put("X-TC-RequestClient", "APIExplorer");
        map.put("X-TC-Action", "SegmentPortraitPic");
        map.put("X-TC-Version", "2020-03-24");
        map.put("Host", "bda.tencentcloudapi.com");
        map.put("X-TC-Language", "zh-CN");
        String jsonString = com.alibaba.fastjson2.JSON.toJSONString(new Param());

        Headers.Builder header = new Headers.Builder();
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            header.add(entry.getKey(), entry.getValue().toString());
        }
        RequestBody requestBody = RequestBody.create(jsonString, JSON);
        Headers head = header.build();
        Request req = new Request.Builder()
                .headers(head)
                .url(url)
                .post(requestBody)
//                .method("post", requestBody)
                .build();
        System.out.println(req.toString());
        try (Response response = client.newCall(req).execute()) {
            System.out.println(response.body().string());
            System.out.println(response);
        } catch (IOException e) {
            System.out.println(e);
        }

/*
  -      url=https://bda.tencentcloudapi.com/,
        //Authorization:TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action,Signature=f7ac8547f1ecdc12421753e74b842814d626b0f0edc7312cc7d0441388e42d83,
        // X-TC-Timestamp:1703693378,
        // X-TC-Region:ap-shanghai,
        // X-TC-Version:2020-03-24,
        // X-TC-Action:SegmentPortraitPic,
        // X-TC-RequestClient:APIExplorer,
        // Host:bda.tencentcloudapi.com,
        // X-TC-Language:zh-CN,
        // Content-Type:application/json


        curl -X POST https://bda.tencentcloudapi.com
        -H "Authorization: TC3-HMAC-SHA256 Credential=AKIDcXoha1hGANR6qDW0cIWBJ27jB4GdwQXrywmTVWX7w4B6Ykcaut5qKst9zu3rMkMO/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host, Signature=915fa04a3f14e96ab9f38f78285231d0c9b96e109ffb9e8802926456656c82f4"
        -H "Content-Type: application/json"
        -H "Host: bda.tencentcloudapi.com"
        -H "X-TC-Action: SegmentPortraitPic"
        -H "X-TC-Timestamp: 1703728835"
        -H "X-TC-Version: 2020-03-24"
        -H "X-TC-Region: ap-shanghai"
        -H "X-TC-Language: zh-CN"
        -H "X-TC-Token: pWmm72KAIeR6qjkXL1HHHU5es7SfMTGa10198ff73c7ccb4525878825c9d2bbf9cxFWPq9a6cA0pgBMLA4st5fhBL9h0eAsMNDqyYZS8tivDliUoCITWazcYewRZGlwGjvZJKd0MdpUvCNxJJAYaUyXWASGIQbQg1sro4WrjvrrTdog9N-Ui-VPFWgcSsUqWvFD0yAvST9LEcirRBA57kAqf8C7EbFfZoIup5-k1aWqHfZT4QApUFo6_Vm6NDZCeI9bXBEy_aA89mXmVXgP7g"
        -d '{"Url":"https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg","RspImgType":"url","Scene":"GEN"}'

            -H "Host: bda.tencentcloudapi.com"
            -H "X-TC-Region: ap-shanghai"
            -H "Content-Type: application/json"
                -"Authorization:TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-27/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action,Signature=f7ac8547f1ecdc12421753e74b842814d626b0f0edc7312cc7d0441388e42d83"
            -H "X-TC-RequestClient: APIExplorer"
            -H "X-TC-Action: SegmentPortraitPic"
            -H "X-TC-Version: 2020-03-24"
            -H "X-TC-Timestamp: 1703693255"
            -H "X-TC-Language: zh-CN"
            -d '{"Url":"https://c-ssl.duitang.com/uploads/blog/202311/25/DWS5xlyzidVYQJW.jpg","RspImgType":"url","Scene":"GEN"}'
            'https://bda.tencentcloudapi.com/'*/



//        map.put("X-TC-Timestamp", );
/*        new FormBody.Builder().add();
        RequestBody.create();
        new Headers.Builder().add()*/

/*        RequestBody body = RequestBody.create(json, JSON);
        RequestBody.create();
        Request request = new Request.Builder()
                .headers()
                .url(url)
                .post(body)
                .build();*/
/*
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

}

class Param {
    //正常
//    public String Url = "https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg";
    //分辨率过大
//    public String Url = "https://c-ssl.duitang.com/uploads/item/202006/16/20200616230721_T58tL.jpeg";
    //可以处理
//    public String Url = "https://c-ssl.duitang.com/uploads/blog/202012/05/20201205234229_8b3e8.jpeg";
    public String Url = "https://c-ssl.duitang.com/uploads/blog/202101/24/20210124164732_665eb.jpeg";


    public String RspImgType = "url";
    //取值：GEN/GS。GEN为通用场景模式；GS为绿幕场景模式，针对绿幕场景下的人像分割效果更好。 两种模式选择一种传入，默认为GEN
    public String Scene = "GEN";

}





/*class DescribeRegions {
    static String SecretId = "AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC";

    static String SecretKey = "LRJxNbcSwCD0Zv270fBUbU9ZljSzINFs";
    public static void main(String [] args) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(SecretId, SecretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cvm.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            CvmClient client = new CvmClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DescribeRegionsRequest req = new DescribeRegionsRequest();

            // 返回的resp是一个DescribeRegionsResponse的实例，与请求对象对应
            DescribeRegionsResponse resp = client.DescribeRegions(req);
            // 输出json格式的字符串回包
            System.out.println(DescribeRegionsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}*/




@Slf4j
class TencentCloudAPITC3Demo {
    static String SecretId = "AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC";

    static String SecretKey = "LRJxNbcSwCD0Zv270fBUbU9ZljSzINFs";
    private final static Charset UTF8 = StandardCharsets.UTF_8;
    // 需要设置环境变量 TENCENTCLOUD_SECRET_ID，值为示例的 AKIDz8krbsJ5yKBZQpn74WFkmLPx3*******
    private final static String SECRET_ID = SecretId;
    // 需要设置环境变量 TENCENTCLOUD_SECRET_KEY，值为示例的 Gu5t9xGARNpq86cd98joQYCN3*******
    private final static String SECRET_KEY = SecretKey;
    private final static String CT_JSON = "application/json; charset=utf-8";

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
//        return DatatypeConverter.printHexBinary(d).toLowerCase();
        return convertBinaryToHex(d);
    }

    private static String convertBinaryToHex(byte[] binaryData) {
        StringBuilder hexBuilder = new StringBuilder();

        for (byte b : binaryData) {
            int unsignedByte = b & 0xFF; // Convert to unsigned byte
            String hexByte = Integer.toHexString(unsignedByte);

            // Add leading zero if necessary
            //并不代表结尾，而是当前一个数值表示当高16位都为0时转16进制会0省略，比如0X03 and 0XFF ==> 3 而不是03
            if (hexByte.length() == 1) {
                hexBuilder.append('0');
            }

            hexBuilder.append(hexByte);
        }

        return hexBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        String service = "bda";
        String host = "bda.tencentcloudapi.com";
        //地域参数
        String region = "ap-shanghai";
        //操作的接口名称
        String action = "SegmentPortraitPic";
        //版本
        String version = "2020-03-24";
        //签名算法
        String algorithm = "TC3-HMAC-SHA256";
//        String timestamp = "1703732127";
        String timestamp = Long.valueOf(System.currentTimeMillis()/1000).toString();
        //String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易出错
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));

        // ************* 步骤 1：拼接规范请求串 *************
        String httpRequestMethod = "POST";
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:application/json; charset=utf-8\n"
                + "host:" + host + "\n" + "x-tc-action:" + action.toLowerCase() + "\n";
        String signedHeaders = "content-type;host;x-tc-action";

        String payload = com.alibaba.fastjson2.JSON.toJSONString(new Param());
//        String payload = "{\"Limit\": 1, \"Filters\": [{\"Values\": [\"\\u672a\\u547d\\u540d\"], \"Name\": \"instance-name\"}]}";
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;
        System.out.println(canonicalRequest);

        // ************* 步骤 2：拼接待签名字符串 *************
        String credentialScope = date + "/" + service + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;
        System.out.println(stringToSign);

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("TC3" + SECRET_KEY).getBytes(UTF8), date);
        byte[] secretService = hmac256(secretDate, service);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = convertBinaryToHex(hmac256(secretSigning, stringToSign)).toLowerCase();
        System.out.println(signature);

        // ************* 步骤 4：拼接 Authorization *************
        String authorization = algorithm + " " + "Credential=" + SECRET_ID + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        System.out.println(authorization);

        TreeMap<String, String> headers = new TreeMap<String, String>();
        headers.put("Authorization", authorization);
        headers.put("Content-Type", CT_JSON);
        headers.put("Host", host);
        headers.put("X-TC-Action", action);
        headers.put("X-TC-Timestamp", timestamp);
        headers.put("X-TC-Version", version);
        headers.put("X-TC-Region", region);

        StringBuilder sb = new StringBuilder();
        sb.append("curl -X POST https://").append(host)
                .append(" -H \"Authorization: ").append(authorization).append("\"")
                .append(" -H \"Content-Type: application/json; charset=utf-8\"")
                .append(" -H \"Host: ").append(host).append("\"")
                .append(" -H \"X-TC-Action: ").append(action).append("\"")
                .append(" -H \"X-TC-Timestamp: ").append(timestamp).append("\"")
                .append(" -H \"X-TC-Version: ").append(version).append("\"")
                .append(" -H \"X-TC-Region: ").append(region).append("\"")
                .append(" -d '").append(payload).append("'");
        System.out.println(sb.toString());


        System.out.println("----######################################################################################-----------------------------------------------------------");
        System.out.println("----#################################################################################--------------------------");
        System.out.println("-----#################################################################################-------------------------");


        String url = "https://bda.tencentcloudapi.com";
        MediaType JSON = MediaType.get(CT_JSON);
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> map = new HashMap<>();
        map.put("X-TC-Timestamp", Long.valueOf(System.currentTimeMillis()/1000).toString());
        map.put("X-TC-Region", region);
        map.put("Content-Type", CT_JSON);
/*        String sec = "TC3-HMAC-SHA256";
        String credential = "Credential";
//        String curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String curDate = "2023-12-28";
        String service = "bda";
        String tc3_request = "tc3_request";
        String signedHeaders = "SignedHeaders=content-type;host;x-tc-action";
        String signature = "Signature=4b72761b4b0111eca5b39b3fb7c945057f1d342b6e20accaa9fd1a4864f6b4f1";*/
//        String Authorization = sec + " " + credential + "=" + SecretId + "/" + curDate + "/" + service + "/" + tc3_request + ", " + signedHeaders + "," + signature;
        String Authorization = "TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-29/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature="+signature;
        map.put("Authorization", Authorization);
        //mei
        map.put("X-TC-RequestClient", "APIExplorer");
        //mei 操作名称
        map.put("X-TC-Action", action);
        //mei 版本
        map.put("X-TC-Version", version);
        map.put("Host", host);
        map.put("X-TC-Language", "zh-CN");
//        String jsonString = com.alibaba.fastjson2.JSON.toJSONString(new Param());

        Headers.Builder header = new Headers.Builder();
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            header.add(entry.getKey(), entry.getValue().toString());
        }
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Headers head = header.build();
        Request req = new Request.Builder()
                .headers(head)
                .url(url)
                .post(requestBody)
//                .method("post", requestBody)
                .build();
        System.out.println(req.toString());
        try (Response response = client.newCall(req).execute()) {
/*
这个response.body只能读取一次，之后就读不到了
System.out.println(response.body().string());
//            System.out.println(response);
            System.out.println(response);*/

            System.out.println("---------**&*&**************_______------------");
            JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(response.body().string());
            System.out.println(jsonObject);
            JSONObject response1 = jsonObject.getJSONObject("Response");
            System.out.println(response1);
            String resultImageUrl = response1.getString("ResultImageUrl");
            System.out.println(resultImageUrl);
            System.out.println(toUrl(resultImageUrl));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
//zeheasdzhuan转一下
    public static String toUrl(String str) {
        return str.replace("\\u0026", "");
    }
}


/*
* https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Image/1322798562/cd899ec3-da6e-4aca-964d-a6c61264eb5e?q-sign-algorithm=sha1&q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje&q-sign-time=1703732728%3B1703734528&q-key-time=1703732728%3B1703734528&q-header-list=host&q-url-param-list=&q-signature=4fc32e8c051107497df7c11f5fcefe7f148a1584
* https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Image/1322798562/26ed1eaf-1d28-4bae-8187-f33941e5684a?q-sign-algorithm=sha1&u0026q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje&u0026q-sign-time=1703732502%3B1703734302&u0026q-key-time=1703732502%3B1703734302&u0026q-header-list=host&u0026q-url-param-list=&u0026q-signature=3e142733f8f4dfc27b120774f7c82a82621adef0"
*
*
*
*
* {"Response":
* {"ResultImage":"","ResultMask":"",
* "ResultImageUrl": "https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Image/1322798562/8f776343-2687-45de-a04e-b7b3ff4e40e7?q-sign-algorithm=sha1&q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje&q-sign-time=1703732964%3B1703734764&q-key-time=1703732964%3B1703734764&q-header-list=host&q-url-param-list=&q-signature=90f7b5c3cfb26c317d3a6d4fac4d33cf41e34fa3",
* "ResultMaskUrl":"https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Mask/1322798562/8f776343-2687-45de-a04e-b7b3ff4e40e7?q-sign-algorithm=sha1\u0026q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje\u0026q-sign-time=1703732964%3B1703734764\u0026q-key-time=1703732964%3B1703734764\u0026q-header-list=host\u0026q-url-param-list=\u0026q-signature=f6f482372f15d4403982c6abbe5adca2fc452fc7",
* "HasForeground":true,"RequestId":"8f776343-2687-45de-a04e-b7b3ff4e40e7"}}%
*
*
Request{method=POST, url=https://bda.tencentcloudapi.com/, headers=[Authorization:TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=227483ee69f4d390e4ee381dbea30db34e57d60b8ffde26e643f14cb4a86c5ed, X-TC-Timestamp:1703734714, X-TC-Region:ap-shanghai, X-TC-Version:2020-03-24, X-TC-Action:SegmentPortraitPic, X-TC-RequestClient:APIExplorer, Host:bda.tencentcloudapi.com, X-TC-Language:zh-CN, Content-Type:application/json]}
curl -H "X-TC-RequestClient: APIExplorer" -H "X-TC-Action: SegmentPortraitPic" -H "X-TC-Version: 2020-03-24" -H "X-TC-Region: ap-shanghai" -H "X-TC-Language: zh-CN" -H "Content-Type: application/json" -H "Authorization: TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=227483ee69f4d390e4ee381dbea30db34e57d60b8ffde26e643f14cb4a86c5ed" -H "Host: bda.tencentcloudapi.com" -H "X-TC-Timestamp: 1703734714"  -d '{"Url":"https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg","RspImgType":"url","Scene":"GEN"}' 'https://bda.tencentcloudapi.com/'
 * */




/*

Request{
method=POST, url=https://bda.tencentcloudapi.com/,
headers=[
Authorization:TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=1747dd8fa26c2131e3ff3a3b1e8ab65f4d4f1e6312db3d490f457b16b9fad299,
X-TC-Timestamp:1703750557,
 X-TC-Region:ap-shanghai,
 X-TC-Version:2020-03-24,
 X-TC-Action:SegmentPortraitPic,
 X-TC-RequestClient:APIExplorer,
 Host:bda.tencentcloudapi.com,
 X-TC-Language:zh-CN, Content-Type:application/json; charset=utf-8
 ]}

 {"Response":
 {"ResultMaskUrl":"https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Mask/1322798562/945cda4f-9c8c-4555-b1a1-39a95365f8cf?q-sign-algorithm=sha1\u0026q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje\u0026q-sign-time=1703750559%3B1703752359\u0026q-key-time=1703750559%3B1703752359\u0026q-header-list=host\u0026q-url-param-list=\u0026q-signature=9a8dd84708e03ab8caf29de7f576c703a070a0aa",
 "HasForeground":true,
 "RequestId":"945cda4f-9c8c-4555-b1a1-39a95365f8cf",
 "ResultImage":"",
 "ResultMask":"",
 "ResultImageUrl":"https://bda-segment-mini-1258344699.cos.ap-guangzhou.myqcloud.com/Image/1322798562/945cda4f-9c8c-4555-b1a1-39a95365f8cf?q-sign-algorithm=sha1\u0026q-ak=AKIDEJJ3lFOnfIpAHAqIJ5d3YqthGfpj8eje\u0026q-sign-time=1703750559%3B1703752359\u0026q-key-time=1703750559%3B1703752359\u0026q-header-list=host\u0026q-url-param-list=\u0026q-signature=048898df3d3aab194b53f8599a900e604c3e230d"}}

    Response{protocol=http/1.1, code=200, message=OK, url=https://bda.tencentcloudapi.com/}*/





class CustomParam {
    //正常
//    public String Url = "https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg";
    //分辨率过大
    public String Url = "https://c-ssl.duitang.com/uploads/item/202006/16/20200616230721_T58tL.jpeg";
    //可以处理
//    public String Url = "https://c-ssl.duitang.com/uploads/blog/202012/05/20201205234229_8b3e8.jpeg";
//    public String Url = "https://c-ssl.duitang.com/uploads/blog/202101/24/20210124164732_665eb.jpeg";


//    public String RspImgType = "url";
    //取值：GEN/GS。GEN为通用场景模式；GS为绿幕场景模式，针对绿幕场景下的人像分割效果更好。 两种模式选择一种传入，默认为GEN
//    public String Scene = "GEN";
    public SegmentationOptions SegmentationOptions = new SegmentationOptions();

}

class SegmentationOptions {
//左边眉毛
    public Boolean RightEyebrow = true;
//    左眉
    public Boolean LeftEyebrow = true;
//    左眼
    public Boolean LeftEye = false;
    //右边👀眼睛🧐
    public Boolean RightEye = true;
//    头发
    public Boolean Hair = true;
//    鼻子
    public Boolean Nose = true;
//    背景
    public Boolean Background = false;
//    上唇
    public Boolean UpperLip = false;
//    下唇
    public Boolean LowerLip = true;
//    牙齿
    public Boolean Tooth = true;
//    口腔（不包含牙齿）
    public Boolean Mouth = true;
//    左耳
    public Boolean LeftEar = true;
//    右耳
    public Boolean RightEar = false;
//    面部(不包含眼、耳、口、鼻等五官及头发。)
    public Boolean Face = true;
//    头部(包含所有的头部元素，相关装饰除外)
    public Boolean Head = false;
//    身体（包含脖子）
    public Boolean Body = false;
//    帽子
    public Boolean Hat = true;
//    头饰
    public Boolean Headdress = false;
//    耳环
    public Boolean Earrings = true;
//    项链
    public Boolean Necklace = true;
//    随身物品（ 例如伞、包、手机等。 ）
    public Boolean Belongings = true;


}

class CustomTencentCloudAPITC3Demo {
    static String SecretId = "AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC";

    static String SecretKey = "LRJxNbcSwCD0Zv270fBUbU9ZljSzINFs";
    private final static Charset UTF8 = StandardCharsets.UTF_8;
    // 需要设置环境变量 TENCENTCLOUD_SECRET_ID，值为示例的 AKIDz8krbsJ5yKBZQpn74WFkmLPx3*******
    private final static String SECRET_ID = SecretId;
    // 需要设置环境变量 TENCENTCLOUD_SECRET_KEY，值为示例的 Gu5t9xGARNpq86cd98joQYCN3*******
    private final static String SECRET_KEY = SecretKey;
    private final static String CT_JSON = "application/json; charset=utf-8";

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
//        return DatatypeConverter.printHexBinary(d).toLowerCase();
        return convertBinaryToHex(d);
    }

    private static String convertBinaryToHex(byte[] binaryData) {
        StringBuilder hexBuilder = new StringBuilder();

        for (byte b : binaryData) {
            int unsignedByte = b & 0xFF; // Convert to unsigned byte
            String hexByte = Integer.toHexString(unsignedByte);

            // Add leading zero if necessary
            if (hexByte.length() == 1) {
                hexBuilder.append('0');
            }

            hexBuilder.append(hexByte);
        }

        return hexBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        //服务名称
        String service = "bda";
        String host = "bda.tencentcloudapi.com";
        //地域参数
        String region = "ap-shanghai";
        //操作的接口名称
        String action = "SegmentCustomizedPortraitPic";
        //版本
        String version = "2020-03-24";
        //签名算法
        String algorithm = "TC3-HMAC-SHA256";
//        String timestamp = "1703732127";
        Long curTime = System.currentTimeMillis();
        String timestamp = Long.valueOf(curTime/1000).toString();
        //String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易出错
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = sdf.format(new Date());*/
/*        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);*/


        // 创建 SimpleDateFormat 实例，指定日期格式和时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // 获取当前日期
        Date currentDate = new Date();

        // 将日期对象格式化为字符串
        String date = dateFormat.format(currentDate);

        // ************* 步骤 1：拼接规范请求串 *************
        String httpRequestMethod = "POST";
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:application/json; charset=utf-8\n"
                + "host:" + host + "\n" + "x-tc-action:" + action.toLowerCase() + "\n";
        String signedHeaders = "content-type;host;x-tc-action";

        String payload = com.alibaba.fastjson2.JSON.toJSONString(new CustomParam());
//        String payload = "{\"Limit\": 1, \"Filters\": [{\"Values\": [\"\\u672a\\u547d\\u540d\"], \"Name\": \"instance-name\"}]}";
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;
        System.out.println(canonicalRequest);

        // ************* 步骤 2：拼接待签名字符串 *************
        String credentialScope = date + "/" + service + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;
        System.out.println(stringToSign);

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("TC3" + SECRET_KEY).getBytes(UTF8), date);
        byte[] secretService = hmac256(secretDate, service);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = convertBinaryToHex(hmac256(secretSigning, stringToSign)).toLowerCase();
        System.out.println(signature);

        // ************* 步骤 4：拼接 Authorization *************
        String authorization = algorithm + " " + "Credential=" + SECRET_ID + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        System.out.println(authorization);

        TreeMap<String, String> headers = new TreeMap<String, String>();
        headers.put("Authorization", authorization);
        headers.put("Content-Type", CT_JSON);
        headers.put("Host", host);
        headers.put("X-TC-Action", action);
        headers.put("X-TC-Timestamp", timestamp);
        headers.put("X-TC-Version", version);
        headers.put("X-TC-Region", region);

        StringBuilder sb = new StringBuilder();
        sb.append("curl -X POST https://").append(host)
                .append(" -H \"Authorization: ").append(authorization).append("\"")
                .append(" -H \"Content-Type: application/json; charset=utf-8\"")
                .append(" -H \"Host: ").append(host).append("\"")
                .append(" -H \"X-TC-Action: ").append(action).append("\"")
                .append(" -H \"X-TC-Timestamp: ").append(timestamp).append("\"")
                .append(" -H \"X-TC-Version: ").append(version).append("\"")
                .append(" -H \"X-TC-Region: ").append(region).append("\"")
                .append(" -d '").append(payload).append("'");
        System.out.println(sb.toString());


        System.out.println("----######################################################################################-----------------------------------------------------------");
        System.out.println("----#################################################################################--------------------------");
        System.out.println("-----#################################################################################-------------------------");


        String url = "https://bda.tencentcloudapi.com";
        MediaType JSON = MediaType.get(CT_JSON);
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> map = new HashMap<>();
        map.put("X-TC-Timestamp", Long.valueOf(System.currentTimeMillis()/1000).toString());
        map.put("X-TC-Region", region);
        map.put("Content-Type", CT_JSON);
/*        String sec = "TC3-HMAC-SHA256";
        String credential = "Credential";
//        String curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String curDate = "2023-12-28";
        String service = "bda";
        String tc3_request = "tc3_request";
        String signedHeaders = "SignedHeaders=content-type;host;x-tc-action";
        String signature = "Signature=4b72761b4b0111eca5b39b3fb7c945057f1d342b6e20accaa9fd1a4864f6b4f1";*/
//        String Authorization = sec + " " + credential + "=" + SecretId + "/" + curDate + "/" + service + "/" + tc3_request + ", " + signedHeaders + "," + signature;
        String Authorization = "TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-29/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature="+signature;
        map.put("Authorization", Authorization);
        //mei
        map.put("X-TC-RequestClient", "APIExplorer");
        //mei 操作名称
        map.put("X-TC-Action", action);
        //mei 版本
        map.put("X-TC-Version", version);
        map.put("Host", host);
        map.put("X-TC-Language", "zh-CN");
//        String jsonString = com.alibaba.fastjson2.JSON.toJSONString(new Param());

        Headers.Builder header = new Headers.Builder();
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            header.add(entry.getKey(), entry.getValue().toString());
        }
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Headers head = header.build();
        Request req = new Request.Builder()
                .headers(head)
                .url(url)
                .post(requestBody)
//                .method("post", requestBody)
                .build();
        System.out.println(req.toString());
        try (Response response = client.newCall(req).execute()) {
/*
这个response.body只能读取一次，之后就读不到了
System.out.println(response.body().string());
//            System.out.println(response);
            System.out.println(response);*/

            System.out.println("---------**&*&**************_______------------");
            JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(response.body().string());
            System.out.println(jsonObject);
            JSONObject response1 = jsonObject.getJSONObject("Response");
            System.out.println(response1);
            byte[] MaskImageBytes = response1.getString("MaskImage").getBytes();
            byte[] MaskImageDecode = Base64.getDecoder().decode(MaskImageBytes);
            byte[] PortraitImageBytes = response1.getString("PortraitImage").getBytes();
            byte[] PortraitImageDecode = Base64.getDecoder().decode(PortraitImageBytes);
            String path1 = "/Users/duitang/IdeaProjects/uCreateTest/mavenTest/src/main/resources/file/img.jpg";
            String path2 = "/Users/duitang/IdeaProjects/uCreateTest/mavenTest/src/main/resources/file/img2.jpg";
            try (FileOutputStream outputStream1 = new FileOutputStream(path1);
            FileOutputStream outputStream2 = new FileOutputStream(path2)) {
                outputStream1.write(MaskImageDecode);
                outputStream2.write(PortraitImageDecode);
            } catch (IOException e) {
                System.out.println(e);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

/*
* curl -X POST
* https://bda.tencentcloudapi.com
* -H "Authorization: TC3-HMAC-SHA256 Credential=AKIDsblx5A_nAfxyV9Ysjl6J0DAFjFwx4iGlHlR3McieOWyC9euAEmjMf_YgvrjTrAo-/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host, Signature=b0b08b970103f0750c6fd9debea4ede291b5570aa7ba316dfa5102bf34fc978f"
* -H "Content-Type: application/json"
* -H "Host: bda.tencentcloudapi.com"
* -H "X-TC-Action: SegmentCustomizedPortraitPic"
* -H "X-TC-Timestamp: 1703771233"
* -H "X-TC-Version: 2020-03-24"
* -H "X-TC-Region: ap-shanghai"
* -H "X-TC-Language: zh-CN"
* -H "X-TC-Token: 38yyAN3hp7q35WGJkhjuj7fDOGnc6f7a3b1db2fcc07274495c0dd2f5d16c6ea6irVYGAcnIlPs8NweEVJlq5glNWU49v2cIYXl9tW0dSETX4_F7on1zs0nKGfaPWadX0Guj8p8BWntIqeA65nBt7mcUcej7tnsnBYyqmrFiGeIJwh1KVmcMNeSEQ8d6kwsG1xOmgiuz_hWG4pNpesuWvQwT1yBOnzgOimuTewGLSNJ-0whyKFZral2LmsuIKAgaXh1BkB7WSkfn9LC7uGHYA"
* -d '{
* "Url":"https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg",
* "SegmentationOptions":{"Background":false,"Hair":true,"LeftEyebrow":true,"RightEyebrow":true,"LeftEye":true,"RightEye":true,"Nose":false,"UpperLip":false,"LowerLip":false,"Tooth":false,"Mouth":true,"LeftEar":false,"RightEar":false,"Face":false,"Head":false,"Body":false,"Hat":true,"Headdress":false,"Earrings":false,"Necklace":false,"Belongings":false}}'
*
*
* curl
* -H "X-TC-Action: SegmentCustomizedPortraitPic"
* -H "X-TC-Version: 2020-03-24"
* -H "Authorization: TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=ccd0906aa505d35954f0028c41ddad4f9274137097cdd8f2973ebe61e7ba7de0"
* -H "X-TC-RequestClient: APIExplorer"
* -H "Host: bda.tencentcloudapi.com"
* -H "X-TC-Language: zh-CN"
* -H "Content-Type: application/json"
* -H "X-TC-Timestamp: 1703773185"
* -H "X-TC-Region: ap-shanghai"
* -d '{
* "Url":"https://c-ssl.duitang.com/uploads/blog/202201/03/20220103022022_372a6.jpg",
* "SegmentationOptions":{"Background":false,"Hair":true,"LeftEyebrow":true,"RightEyebrow":true,"LeftEye":true,"RightEye":true,"Nose":false,"UpperLip":false,"LowerLip":false,"Tooth":false,"Mouth":true,"LeftEar":false,"RightEar":false,"Face":false,"Head":false,"Body":false,"Hat":true,"Headdress":false,"Earrings":false,"Necklace":false,"Belongings":false}}'
* 'https://bda.tencentcloudapi.com/'
*
*
* Request{
* method=POST,
* url=https://bda.tencentcloudapi.com/,
* headers=[
* Authorization:TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=a5385e63ee1cde3ab4c12295ff83b92fca48b6fb2eb208b2eb0efa78aa425b5a,
*  X-TC-Timestamp:1703773803,
* X-TC-Region:ap-shanghai,
* X-TC-Version:2020-03-24,
* X-TC-Action:SegmentCustomizedPortraitPic,
* X-TC-RequestClient:APIExplorer,
* Host:bda.tencentcloudapi.com,
* X-TC-Language:zh-CN,
* Content-Type:application/json;]}

 *
*
*
*
*
*
*
* curl -X POST
* https:// bda.tencentcloudapi.com
* -H "Authorization: TC3-HMAC-SHA256 Credential=AKID4zgARTtBOQIvLXbT9duyoRr43pQnywgC/2023-12-28/bda/tc3_request, SignedHeaders=content-type;host;x-tc-action, Signature=ea11b89323ab9c5d7020daa361b0c7a098c1cd57b651377571d6c66cebb85ac8"
* -H "Content-Type: application/json; charset=utf-8"
* -H "Host:  bda.tencentcloudapi.com"
* -H "X-TC-Action: SegmentCustomizedPortraitPic"
* -H "X-TC-Timestamp: 1703772817"
* -H "X-TC-Version: 2020-03-24"
* -H "X-TC-Region: ap-shanghai"
* -d '{
* "Url":"https://c-ssl.duitang.com/uploads/blog/202101/24/20210124164732_665eb.jpeg",
* "segmentationOptions":{"LeftEye":true,"RightEyebrow":true}}'

 * */
