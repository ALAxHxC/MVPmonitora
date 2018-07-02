package ponny.org.monitora.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

import com.scottyab.aescrypt.AESCrypt;


public class CipherMonitora  {
    static String secret="e4b749e81288302501f897996a364797";
         // static  String iv="ed82a7c7b7a202dc";
        private static CipherMonitora instance = null;

    protected CipherMonitora() {

    }

    public static CipherMonitora getInstance() {

        if (instance == null) {
            instance = new CipherMonitora();
        }
        return instance;
    }

    public String encrypt(String message){
        try {

            String decryptMsg = AESCrypt.encrypt(secret, message);
            System.out.print(decryptMsg);
            return  decryptMsg;
        }catch (GeneralSecurityException e){
            //handle error
            e.printStackTrace();
           return null;
        }
    }

    public String decrypt(String encrypted) {
        try {
            String decryptMsg = AESCrypt.decrypt(secret, encrypted);
            System.out.print(decryptMsg);
            return  decryptMsg;
        }catch (GeneralSecurityException e){
            //handle erro
            e.printStackTrace();
            return null;
        }
    }
}
