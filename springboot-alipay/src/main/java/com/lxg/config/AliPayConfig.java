package com.lxg.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author LXG
 * @date 2021-12-2
 */

public class AliPayConfig {

    /**
     * 应用ID,您的APPID，收款账号即是您的APPID对应支付宝账号
     */
    public static String APP_ID = "2021000117636533";

    /**
     * 商户应用私钥，您的PKCS8格式RSA2私钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDCty7nmum6kNBWr3VjECvszCfwjShQb/jrr0VcsQOn8Tnl1c2W2ltdsMg0IDAajjuJEKIvcjXNtoV4bwpq0Cai6VNtS9y2v6VpHREat4mslNjIAWxODlzROTMgdF+X156N7dd+23ovxhYFZnnDowaWhyH3EK/9ZZPj3zVsdQyg+7QcN+ntA0UnvqJ9fWAkpWSLF9+OlhSW0oAedQl1rHkw9rB/yzyCB433YWY3tYXXHNVPpva9M/rv2xTBrf7nQGIVlTtgzdrKOzo72sYbuKKt8C7PKP1eRpGmzAGnvf0Uc48Z9qncUt+99ctw0Kcy5MLbTDLJ+XDsnRHdaEDfK7E7AgMBAAECggEBAJi4KHfLTwrfTijPUVizdGAz+Bd5/2anjCr+u35p8B4m22xi9u8csJCZeU04+cG6UQO+Ihbokz6Xq7O8FAWW4Nbh/mc5GxHS2p/JBoNX9QmBbU3hn9rQ9Qe8v1bzL47etSNIqr560ijqSpdNuq8xDJe/wWoQkphCtW4CwdohLBiOMgzcJIjXFMknDrHRBspoqrrpn1rrInvt8TGa+VRLd3l3E08dZ5V1+Q2U0eceBfwqcKw7zGnIRW9AL6kd7qo2iJ6Rf4URhJkqnEQFGtF3ga5qFUNbJNdmYJhIvatFvg4T+Pe3x+bym4qGiSS6TG5FsL1EddpDqhOOFfUPNu66IhECgYEA+4IqvnTJpNR6nCoYh+wQOGtHgy0FvdZARJdNbS62lg2e7oKPdosH9dewF+dKZzRIErq/4CSntQhQL4zIeH6QYCzgXxlv7Fzmn1i3iymO6kHeYSaSXtm+cdqTIoA2yE3MzeifMpx2n0XDFisx7tDUps+xKtupVbjxUCd6Y0ECfXcCgYEAxjFfm4rktAQyzazFzxwblhu1Vg2pkoLaQ0J8HMYdZwsBjdL061peW5hHCapQa2Rs2IqpJj8JpHGhMVwS9PZIbAZb4Y4c1HI3FZcKfmCGo1h/eOfd3fwN2L6os2MTfvvuWNN34TzW+GDXAGY0SMzpKH3PHCM3c6quOrevtgEgC10CgYEA+lOTUZ8R9/HX20j2w4pMlqsIMWKFXW4650oksTAWYQFLl57xRY6ZrLIxvYjigBgESEgBpAk3trKAhOM9wNhPwLnJ1D2QLZRFooH9pRAHA9fti4qFksxshguD+InefL0axDLtCMW8N0iLJKx3dXf6v9DmSH77aqu52wqAAPv6i38CgYBb4Ru/kf5AbgnXS5dQUml/h0zjneXU5BCnSbUb6cRLttB7nSapzPUdZVTZaDlr79x97ppH/sVLlNnzdgnbwikE21XRFP7Mhj5/JcxZlgoUw7wkouk6NdV9e7Xdo7qwDb8LMs3Kf1fxQYOqzbbDYStd+CUBmFnmp2OqGOm+aHsgiQKBgQCYqPPSxggLQ37VBbTEA/4U/oh02v6zu9pIfXNnh7Sy04HT1COFNY6y5SefW2M9a/MTbnwxCtoPYth8NBe7ItnsrZS+LfGXIpKjRK8UHoINwA5g13MnjYRM33wuP1NghPp7Ja+NtlDO9mSTFPlwwTea4u8pWpUgXUgRZ1bKtHcQ9g==";
    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     * 这里的是沙箱环境的
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwi1fDtjQrgMtwiAImlEpjgL+OlbzQgbTrFk6i+rf7MWafIABg5jYZ2SANFtvJiSFnWSih6KciYXi6kLcPlHfVsX0bECjUeoiF41mu9ttCSjdLX11IsGMEG6R2fyulcXQcw8uQpVhdF1mwOeZognyP5o50VtTh8VT/pZyA+ztxELWEfU4NpdcJj7+yxJWihMWSCvJsKa4ukhOqfr4wVDOpsWFau8wasNSdraG4ksIzSV8TiIKGRhQGQE9TgNuXCP46dT+5NBKi1vZQOtXFp5SQntbZQflwB0vZMTosBbsxRG6uuXXuhCLo7LaxdhReDvg84rDxGNNg9mMi7/jSX825QIDAQAB";
    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * 即支付成功之后，需要跳转到的页面，一般为网站的首页
     */
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志存储路径
    public static String log_path = "D:\\logs\\";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
