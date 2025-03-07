// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.jwt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

public class AESenc {
   private static final String ALGO = "AES";

   public AESenc() {
   }

   public static String encrypt(String data, byte[] keyValue) {
      try {
         byte[] input = data.getBytes("UTF-8");
         Deflater compresser = new Deflater();
         compresser.setInput(input);
         compresser.finish();
         byte[] output = new byte[input.length];
         int compressedDataLength = compresser.deflate(output, 0, output.length, 3);
         compresser.end();
         Key key = generateKey(keyValue);
         Cipher c = Cipher.getInstance("AES");
         c.init(1, key);
         byte[] encVal = c.doFinal(Arrays.copyOf(output, compressedDataLength));
         return Base64.getUrlEncoder().encodeToString(encVal);
      } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException var10) {
         Logger.getLogger(AESenc.class.getName()).log(Level.SEVERE, (String)null, var10);
      } catch (Exception var11) {
         Logger.getLogger(AESenc.class.getName()).log(Level.SEVERE, (String)null, var11);
      }

      return "";
   }

   public static String decrypt(String encryptedData, byte[] keyValue) {
      try {
         Key key = generateKey(keyValue);
         Cipher c = Cipher.getInstance("AES");
         c.init(2, key);
         byte[] decordedValue = Base64.getUrlDecoder().decode(encryptedData);
         byte[] decValue = c.doFinal(decordedValue);
         Inflater decompresser = new Inflater();
         decompresser.setInput(decValue);
         byte[] result = new byte[decValue.length * 3];
         int resultLength = decompresser.inflate(result);
         decompresser.end();
         new String(result, 0, resultLength, "UTF-8");
         return new String(result, 0, resultLength, "UTF-8");
      } catch (InvalidKeyException var10) {
         Logger.getLogger(AESenc.class.getName()).log(Level.SEVERE, (String)null, var10);
      } catch (Exception var11) {
         Logger.getLogger(AESenc.class.getName()).log(Level.SEVERE, (String)null, var11);
      }

      return "";
   }

   private static Key generateKey(byte[] keyValue) throws Exception {
      return new SecretKeySpec(keyValue, "AES");
   }
}
