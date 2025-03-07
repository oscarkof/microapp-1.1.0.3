// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.gui.solicitud;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

class NewMain {
   NewMain() {
   }

   public static void main(String[] args) throws UnsupportedEncodingException {
      try {
         String inputString = "rn7mYWByDMePC_jocAeVLOVgauPyLUpswwqesmt3PpNiIPjtsrNwEJt9PDs0JuX5_F53zfSWFa6Z5Xh_dy8bWvze_Vntq3hMmzV0hokR9GCbSXS9a0PdrVPejfzvkVTzeGYzY3SMPJhpK0N6_zKuzsnFCzERFmS6ZdOQDnzTbWmzfppShG5dWMGO8go5FjzRPyNewiDmv_nBn4tq0ReHe7giFRdvyBQwTICY5S5bMtVHy2ur6DVm37_QfrHOHW9o88haIhv6kPRMfh9MwYjNTvZHw82L85lp6DRGOUiSJpWG8TY19AGFokhQ8AuE3nB_QtayO92bmpQDRnhopioax3WzCBo-BupJ6IW7ZCup_iDv8NexA3jF8MjdIi9TvxeHqjrq8GM76PNkA6vKlp7eqZCzee9GFN1gVlMSUCNwMkGjPQd4taf1WBpiXlegK9Eo0bTmJ4ANVR8IJyMYY1bEJSpps0j569mPl27oSlbeZdiNqE0jkqVyh-YAGYpEejy9iwYg9YUz00yYz2t6ZtP87Jro2tiG7ny57MbFhT_5aKqE2oTHzRfx_seTX-YcC3h5t6w7EfgZrGhBigaw893_s1mSWA14bMKe5DiOnDi8F0fRHzORZDgLmNfXSRz9lRZXb5pp_O1Pp0WksUC9Bo3bb7Qg5-ckmRWISGikbEq-LGs9rZJEHTO2AW68t1MwUth7wTMhpXxhW-edCFA5FUOEkafBkhB1tY7i4rBHl2kvYTVzxaWAxBZR-qPxNz7Ii11d40bzx3OA_BX5N5T7yWJ_AD7ToktU72kXM8nRZc6pxWQQ59p2UKJM56X4Ydr38gQRXpHpBrKrn6aEQXbQNQ2RyeziNNgbHk9lbngXS9lnwE65SWiqEOGhefH4ZcYjTSNsr6f4nIT2MYXZb6OmzEhD1CrdnVfSfLSDKtpBf30VDB6lL4jV6NJ_4LlHX4vwLJXaxpMkqhbffCp9IeXYO-E1-Si_KB50DGowYpSJYh36M3h126nnhSHpyDw6X7Zsf83HI6VJuTDSgF6Z500FHc1YoU-qrjNGE-AWw2PeppMHtejNzyhdTvIaVx3gPpTE9K-YfQk3IYr2cmuj6uLvjCszD72dDdaOINkURdspoir0I2be_WIh68DZGyJGf5qsAdvVNnyAMp42iFuT0pWaRmTDmwByPC7hoqOGOaJdtm41OKjqI2cBrY5gz93huZchEgIyOJgRyK5q495ZanY8Do2-x6oCHZ3q2CdQoBZym-R7A0VoSxLzuXPT0EnNPiN4IYjPpCH_b1mOJYWt-kXjwxU-p9USE3anSS4MX2jMKz5QWTu9nQ3WjiDZFEXbKaIq9CNmrm9fV1PGljC3BBBUAg6hRTZ8gDKeNohbk9KVmkZkw5urz-s027Irn_4WOYOTlqP-pS-I1ejSf-C5R1-L8CyV2s4ngn3erLqLUI78kI19w4HqJ6Wpl2FWqzxNImwq1JOkC5ZYLPmaU0PTsSM3cvjcNauENCRtYI5M9i-yC4tmegILSiTBrn_E8TGhWjs2CRVcu5FYfmX2agQEfhl1rKCtNTNDbNUylE6y2-bDe_PMi3DWPNQEk2t4S_Dam2HPFpmL-pXjEWLf5tboslXEf1ACl4QDWMkM7dqmnwgF526OlP65SWiqEOGhefH4ZcYjTSNs1zpXSjgO_mbAZ_IRFq2mMHdlWYVaY867AFWSNGwN7POlupUlCQKYg-iZ-FKN2OHrq4Q0JG1gjkz2L7ILi2Z6AgiKDWBmzswNzu_VRIAfA8dLyXRw-bVlwg8qwt5aR3GsqhAS6BNYkfBsQvO7_7rkEhZIV8sfUsWX_aU7y5YEtp46AHokdgWySoLOsQdHEAcl";
         byte[] input = inputString.getBytes("UTF-8");
         Deflater compresser = new Deflater();
         compresser.setInput(input);
         compresser.finish();
         byte[] output = new byte[inputString.length()];
         int compressedDataLength = compresser.deflate(output, 0, output.length, 3);
         compresser.end();
         System.out.println("The Compressed String Output: " + new String(output) + "Total datos comprimidos " + compressedDataLength);
         Inflater decompresser = new Inflater();
         decompresser.setInput(output, 0, compressedDataLength);
         byte[] result = new byte[inputString.length()];
         int resultLength = decompresser.inflate(result);
         decompresser.end();
         System.out.println("Total datos descomprimidos " + resultLength);
         String outputString = new String(result, 0, resultLength, "UTF-8");
         System.out.println("\n\n input -->> " + inputString);
         System.out.println("output -->> " + outputString);
         System.out.println("The Compre: " + Base64.getEncoder().encodeToString(Arrays.copyOf(output, compressedDataLength)));
      } catch (UnsupportedEncodingException var10) {
         var10.printStackTrace();
      } catch (DataFormatException var11) {
         var11.printStackTrace();
      }

   }
}
