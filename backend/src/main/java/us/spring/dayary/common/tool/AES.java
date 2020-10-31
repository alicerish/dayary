package us.spring.dayary.common.tool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AES {

    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
    private static final String AES = "AES";
    private static final String UTF_8 = "UTF-8";

    public static Map<String, Object> getIvKeySpec(String key) throws Exception {
        String iv = key;

        Key keySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);

        return new HashMap<>() {
            {
                put("iv", iv);
                put("keySpec", keySpec);
            }
        };
    }

    public static String encode(String str, String key) throws Exception {
        Map<String, Object> data = getIvKeySpec(key);

        String iv = (String) data.get("iv");
        Key keySpec = (Key) data.get("keySpec");

        Cipher c = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes(UTF_8));
        String enStr = Base64.getEncoder().encodeToString(encrypted);

        return enStr;
    }

    public static String decode(String str, String key) throws Exception {
        Map<String, Object> data = getIvKeySpec(key);

        String iv = (String) data.get("iv");
        Key keySpec = (Key) data.get("keySpec");

        Cipher c = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes(UTF_8)));

        byte[] byteStr = Base64.getDecoder().decode(str);

        return new String(c.doFinal(byteStr), UTF_8);
    }
}
