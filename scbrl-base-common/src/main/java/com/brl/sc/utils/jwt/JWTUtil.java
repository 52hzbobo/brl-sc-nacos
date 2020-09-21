package com.brl.sc.utils.jwt;

import com.brl.sc.enums.result.ResultCodeEnum;
import com.brl.sc.exception.BusinessException;
import com.brl.sc.utils.string.AESUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {
    // Token 明文 例：AppToken:17088008800
    public static String ClaimsToken = "ClaimsToken";
    // Token 盐值 例：z3T61L
    public static String ClaimsSalt = "ClaimsSalt";
    // Token 过期时间戳(秒)
    public static String ClaimsExp = "exp";

    //http://www.bluestep.cc/demos/tools/endecode/sha256.html
    private static String secret = "7d995aa0a3f6c493977d36c57da0a8dea3c691323b2eaf2212f8cee1960144ac";
    // 有效期
    public static Long expirationTimeInSecond = 86400L * 7;

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error("token解析错误", e);
            //throw new IllegalArgumentException("Token invalided.");
            return null ;
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private static Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private static Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + expirationTimeInSecond * 1000);
    }

    /**
     * 为指定生成token
     *
     * @param key
     * @return salt
     */
    public static String generateToken(String key) {
         Map<String, Object> claims = new HashMap<>();
         claims.put(ClaimsToken,key);
        /**
         * 盐值（业务逻辑使用,AES秘钥值）
         * 解析出accountToken得到Claims,判断是否一致
         * salt 不一致则提示：用户已经在其他设备登录
         */
        try {
            claims.put(ClaimsSalt, AESUtils.generateSecret());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException(ResultCodeEnum.ERROR);
        }
        return generateToken(claims);
    }

    /**
     * 为指定生成token
     *
     * @param claims 用户信息
     * @return token
     */
    public static String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date();
        Date expirationTime = getExpirationTime();
        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                // 你也可以改用你喜欢的算法
                // 支持的算法详见：https://github.com/jwtk/jjwt#features
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public static Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public static void main(String[] args) {
        String token = generateToken("AppToken:17088008800");
        System.err.println(token);
        Claims claims = getClaimsFromToken(token);
        System.err.println(claims.get(ClaimsToken));
        System.err.println(claims.get(ClaimsSalt));
    }


}
