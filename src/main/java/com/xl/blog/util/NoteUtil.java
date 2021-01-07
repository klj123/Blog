package com.xl.blog.util;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * @author EDZ
 * 1.生成主键UUID
 * 2.将密码的明文经过MD5散列+base64序列化得到密文
 */
public class NoteUtil {
    /**
     * 1.生成主键UUID
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return uuidStr;
    }
    /**
     * 2.将密码的明文经过MD5散列+base64序列化得到密文
     */
    public static String md5(String src) throws NoSuchAlgorithmException {
        /**
         * 获取md5格式
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /**
         * 将传入的字符串解析成md5格式的字节数组
         */
        byte[] digest = md5.digest(src.getBytes());
        /**
         * 将字节数据转换为base64字符串
         */
        String s = Base64.byteArrayToBase64(digest);
        return s;
    }

    /**
     * 测试uuid和密码的输出结果
     * uuid是随机的；
     * 加密之后的密码输出
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String uuid = getUUID();
        System.out.println(uuid);
        String s = md5("123456");
        System.out.println(s);
    }
}
