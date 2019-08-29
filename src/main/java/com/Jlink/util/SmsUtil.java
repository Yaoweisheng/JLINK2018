package com.Jlink.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * 短信工具类
 * 用户登录名称 green@1501046536908946.onaliyun.com
 * AccessKey ID LTAIuazX1OPsox5N
 * AccessKeySecret p98pizCFQmmparycPIF5ym9JRIdyEe
 * @author binzhang
 * @date 2019-05-11
 */
public class SmsUtil {

    @Autowired
    private static Jedis jedisStrings = RedisUtil.getJedis();

    /**
     * 发送短信
     * @param phone
     * @return
     */
    public static boolean sendMessage(String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAIuazX1OPsox5N", "p98pizCFQmmparycPIF5ym9JRIdyEe");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "绿色评价平台");
        request.putQueryParameter("TemplateCode", "SMS_165107492");
        // 生成短信验证码的六位随机数
        int code = (int)((Math.random()*9+1)*100000);
        // 将验证码存入redis中，用于短信验证比对，过期时间三分钟
        jedisStrings.set(phone, String.valueOf(code));
        jedisStrings.expire(phone, 180);
        String smscode = "{\"code\":\""+ code + "\"}";
        request.putQueryParameter("TemplateParam", smscode);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            if (response.getData().contains("\"Message\":\"OK\"")){
                return true;
            }else{
                return false;
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 短信校验
     * 返回false有两种情况
     * 1. 验证码过期
     * 2. 验证码输入错误
     * @param phone
     * @param code
     * @return
     */
    public static boolean validMessage(String phone, String code){
        String realCode = jedisStrings.get(phone);
        if (StringUtils.isBlank(realCode)){
            // 验证码过期
            return false;
        }
        if (!realCode.equals(code)){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(new SmsUtil().sendMessage("15267129712"));
        System.out.println(new SmsUtil().validMessage("15267129712","374513"));
    }
}
