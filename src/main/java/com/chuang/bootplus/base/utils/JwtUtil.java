package com.chuang.bootplus.base.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chuang.bootplus.base.constant.Constant;
import com.chuang.bootplus.base.database.JwtModel;
import com.chuang.bootplus.base.exception.BusException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
@Component
public class JwtUtil {

    private static final String ISS = "xinou";

    public static final long EXPIRE = 1000 * 60 * 60;

//    private static String KEY;
//
//    public void setKEY(String KEY) {
//        JwtUtil.KEY = KEY;
//    }

    private static final String KEY = "022bdc63c3c5a45879ee6581508b9d03adfec4a4658c0ab3d722e50c91a351c42c231cf43bb8f86998202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

    /**
     * @return SecretKey
     */
    private static SecretKey generalKey(){
        byte[] encodedKey = Base64.decode(KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * @return jwt
     */
    public static String createJwt(JwtModel jwtModel, String audience, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = generalKey();
        Map<String, Object> privateClaims = new HashMap<>(3);
        privateClaims.put("userId", jwtModel.getUserId().toString());
        privateClaims.put("userName", jwtModel.getUserName());
        privateClaims.put("account", jwtModel.getAccount());
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration;
        if (ttlMillis > 0){
            expiration = new Date(nowMillis + ttlMillis);
        }else {
            expiration = now;
        }
        String id = IdUtil.getSnowflake(1,1).nextIdStr();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(privateClaims)
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(jwtModel))
                .setAudience(audience)
                .setExpiration(expiration)
                .setNotBefore(now)
                .setIssuedAt(now)
                .setId(id)
                .signWith(signatureAlgorithm, key);

        return jwtBuilder.compact();
    }

    /**
     * @param jwt jwtString
     * @return Claims
     */
    public static Claims parseJwt(String jwt) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        return Jwts
                //得到DefaultJwtParser
                .parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
    }

    /**
     * @param jwtModel jwtModel
     * @param audience 接受者
     * @param effectiveTime 失效时间
     * @return JwtString
     */
    public static String getJwtString(JwtModel jwtModel, String audience, String effectiveTime) {
        int effectiveTimeInt = Integer.parseInt(effectiveTime.substring(0,effectiveTime.length()-1));
        String effectiveTimeUnit = effectiveTime.substring(effectiveTime.length()-1);
        long effectiveTimeLong;
        switch (effectiveTimeUnit){
            case "s" :{
                //秒
                effectiveTimeLong = effectiveTimeInt * 1000L;
                break;
            }
            case "m" :{
                //分钟
                effectiveTimeLong = effectiveTimeInt * 60L * 1000L;
                break;
            }
            case "h" :{
                //小时
                effectiveTimeLong = effectiveTimeInt * 60L * 60L * 1000L;
                break;
            }
            case "d" :{
                //天
                effectiveTimeLong = effectiveTimeInt * 24L * 60L * 60L * 1000L;
                break;
            }
            default:
                effectiveTimeLong = 0L;
        }
        return JwtUtil.createJwt(jwtModel, audience, effectiveTimeLong);
    }

    public static String getJwtString(JwtModel jwtModel) {
        return JwtUtil.getJwtString(jwtModel, Constant.JWT_AUDIENCE, Constant.JWT_VALID_TIME);
    }

    /**
     * @return JwtModel
     */
    public static JwtModel checkToken(String jwtToken) {
        try {
            Claims claims = JwtUtil.parseJwt(jwtToken);
            String subject = claims.getSubject();
            JwtModel jwtModel = JSONObject.parseObject(subject, JwtModel.class);
            Long userId = jwtModel.getUserId();
            if(userId == null){
                throw new BusException(ErrorCode.RE_CODE_NO_HAVE_TOKEN,ErrorCode.RE_MSG_NO_HAVE_TOKEN);
            }
            return jwtModel;
        }catch (SignatureException e){
            throw new BusException(ErrorCode.RE_CODE_JWT_NOT_MATCH, ErrorCode.RE_MSG_JWT_NOT_MATCH);
        }catch (Exception e){
            if (e.getMessage().contains("Allowed clock skew: 0")){
                throw BusException.TokenExapiredException;
            }else {
                throw new BusException(ErrorCode.RE_CODE_DATABASE_ERROR, ErrorCode.RE_MSG_DATABASE_ERROR);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                getJwtString(
                        JwtModel.builder()
                                .userId(1L).account("").userName("").build(),
                        "a",
                        "30d"
                )
        );
//        System.out.println(checkToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJhY2NvdW50XCI6XCIyXCIsXCJ0eXBlXCI6MixcInVzZXJJZFwiOjIsXCJ1c2VyTmFtZVwiOlwi5bCP5ZSQ5bCP5ZSQ5aW26Iy25Y6757OWXCJ9IiwiYXVkIjoiYSIsIm5iZiI6MTU4NDY4NjYxMCwiaXNzIjoieGlub3UiLCJ1c2VyTmFtZSI6IuWwj-WUkOWwj-WUkOWltuiMtuWOu-ezliIsImV4cCI6MTU4NzI3ODYxMCwidXNlcklkIjoiMiIsImlhdCI6MTU4NDY4NjYxMCwiYWNjb3VudCI6IjIiLCJqdGkiOiIxMjQwODkxNzAxMjMzNTg2MTc2In0.6lAzxjaYCVckY32bwReeXEIANSSqqI1n48KIL3GjVwQ").getUserId());
    }
}
