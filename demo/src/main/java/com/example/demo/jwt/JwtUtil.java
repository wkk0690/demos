package com.example.demo.jwt;

import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import com.alibaba.fastjson.JSONObject;


public class JwtUtil {

//	@Value("${spring.profiles.active}")
//    private String profiles;

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64("demo");
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成subject信息
     *
     * @return
     */
    public static String generalSubject(String userId) {
        JSONObject jo = new JSONObject();
        jo.put("userId", userId);
        return jo.toJSONString();
    }

    /**
     * JWT解析工具类
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static String paseYLJWT(String jwt) throws Exception {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("demo".getBytes())
                .parseClaimsJws(jwt);
        System.out.println(claims.getBody());
        return claims.getBody().toString();
    }

    public static void main(String[] args) throws Exception {
        //生成一个token,登陆成功给客户端
        JwtUtil jwt = new JwtUtil();
        String token = jwt.createJWT("1", "name", 10000);
        System.out.println(token);


        Thread.sleep(2000);

        //这是客户端调用接口传过来的一个token,用这个方法校验校验
        Claims jwtStr = jwt.parseJWT(token);

        System.out.println(jwtStr);
    }
}
