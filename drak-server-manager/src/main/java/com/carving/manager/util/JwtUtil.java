package com.carving.manager.util;

import com.alibaba.fastjson.JSONObject;
import com.carving.manager.constant.SecretConstant;
import com.carving.manager.exception.token.base.TokenException;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 工具类
 *
 * @author Li Songlin
 * @date 2019/11/14 9:47
 **/
@Slf4j
public class JwtUtil {

    /**
     * 生产token
     *
     * @param userName
     * @param refreshTime
     * @param ua
     * @param domain
     * @return
     */
    public static String generalToken(String userName, Long refreshTime, String ua, String domain) {
        SignatureAlgorithm signAlg = SignatureAlgorithm.HS256;

        // 将密钥转换成 base64字节数据
        byte[] apikeyByte = DatatypeConverter.parseBase64Binary(SecretConstant.DATAKEY);
        // 使用SHA256生成一个签名密钥
        Key key = new SecretKeySpec(apikeyByte, signAlg.getJcaName());

        // 构造jwt参数
        Map<String, Object> header = new HashMap<>();
        header.put("alg", SignatureAlgorithm.HS256.getValue());
        header.put("typ", "JWT");

        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .claim("userName", userName)
                .claim("refreshTime", refreshTime)
                .claim("userAgent", ua)
                .claim("domainName", domain)
                .setExpiration(new Date(SecretConstant.EXPIRE + refreshTime)).setNotBefore(new Date(refreshTime))
                .signWith(signAlg, key);

        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token token
     * @return
     */
    public static String parseToken(String token) {
        Map<String, Object> retMap;
        if (Strings.isNullOrEmpty(token)) {
            throw new TokenException("Token is null");
        }

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SecretConstant.DATAKEY))
                .parseClaimsJws(token).getBody();

        retMap = new HashMap<>();
        //客户名称
        retMap.put("userName", claims.get("userName"));

        // 刷新时间
        retMap.put("refreshTime", claims.get("refreshTime"));

        //客户端浏览器信息
        retMap.put("userAgent", claims.get("userAgent"));

        retMap.put("domainName", claims.get("domainName"));
        //刷新JWT
        retMap.put("freshToken", generalToken((String) claims.get("userName"), (Long) claims.get("refreshTime"), (String) claims.get("userAgent"), (String) claims.get("domainName")));
        return retMap != null ? JSONObject.toJSONString(retMap) : null;

    }


    public static String getUserNameFromToken(String token) {
        String info = parseToken(token);
        JSONObject jsonObject = JSONObject.parseObject(info);
        return jsonObject.getString("userName");
    }


    /**
     * token
     *
     * @param token
     * @return
     */
    public static boolean validateToken(String token, Long refreshTime, String ua, String domain) {
        String info = parseToken(token);
        JSONObject o = JSONObject.parseObject(info);
        if (o.getString("userAgent").equals(ua) && o.getLong("refreshTime").equals(refreshTime) && o.getString("domainName").equals(domain)) {
            return true;
        } else {
            return false;
        }
    }

}
