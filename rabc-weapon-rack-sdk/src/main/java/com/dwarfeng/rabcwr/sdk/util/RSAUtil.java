package com.dwarfeng.rabcwr.sdk.util;

import com.dwarfeng.subgrade.sdk.util.Constants;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密/解密工具。
 */
public class RSAUtil {

    /**
     * 生成密钥对。
     *
     * @return 随机密钥对。
     */
    public static KeyPair getKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    /**
     * 获取指定的密钥对中的Base64编码的公钥。
     *
     * @param keyPair 指定的密钥对。
     * @return 指定的密钥对中的公钥。
     */
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 获取指定的密钥对中的Base64编码的私钥。
     *
     * @param keyPair 指定的密钥对。
     * @return 指定的密钥对中的私钥。
     */
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象。
     *
     * @param pubStr Base64编码后的公钥。
     * @return 转换成的PublicKey对象。
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public static PublicKey string2PublicKey(String pubStr) throws InvalidKeySpecException, IOException {
        try {
            byte[] keyBytes = base642Byte(pubStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    /**
     * @param priStr
     * @return
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public static PrivateKey string2PrivateKey(String priStr) throws InvalidKeySpecException, IOException {
        try {
            byte[] keyBytes = base642Byte(priStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    public static String publicEncrypt(String plaintext, String pubStr) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, string2PublicKey(pubStr));
            return byte2Base64(cipher.doFinal(plaintext.getBytes(Constants.DEFAULT_CHARSET)));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    public static String publicDecrypt(String base64Ciphertext, String pubStr) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, string2PublicKey(pubStr));
            return new String(cipher.doFinal(base642Byte(base64Ciphertext)), Constants.DEFAULT_CHARSET);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    public static String privateEncrypt(String plaintext, String priStr) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, string2PrivateKey(priStr));
            return byte2Base64(cipher.doFinal(plaintext.getBytes(Constants.DEFAULT_CHARSET)));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    public static String privateDecrypt(String base64Ciphertext, String priStr) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, string2PrivateKey(priStr));
            return new String(cipher.doFinal(base642Byte(base64Ciphertext)), Constants.DEFAULT_CHARSET);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ignored) {
            //由于RSA算法是存在的，所以一定不会抛出该异常。
            return null;
        }
    }

    public static String byte2Base64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    private RSAUtil() {
        throw new IllegalStateException("禁止实例化");
    }
}