/*
 * Copyright (C) 2017 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of the Java Security project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package security;


import com.google.common.io.BaseEncoding;
import com.google.common.primitives.Bytes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * FOSJCrypt hashing sample with plain Java. Uses a salt, configures the number of iterations and calculates the hash
 * value.
 * <p/>
 * Uses Google Guava to hex the hash in a readable format.
 * This API  uses the same algorithm of FOSUserBundle(default)
 * @author Zain ELabidine
 */
public class FOSJCrypt {
    private static final String ALGORITHM = "SHA-512";
    private static final int ITERATIONS = 5000;
    private static final int SALT_SIZE = 32;


    /**
     * Private constructor.
     */
    private FOSJCrypt() {
    }

//    public static void main(String[] args) {
//        String password = "0000";
//        String gs=generateSalt();
//        try {
//
//            //byte[] salt = generateSalt();
//            //BaseEncoding.base64().encode(saltfromdatabase)
//            log.info("Password {}. MyHash algorithm {}, iterations {}, salt {}", password, ALGORITHM, ITERATIONS,
//                    BaseEncoding.base64().encode(gs.getBytes("UTF-8")));
//            byte[] hash = encodePassword(password, gs);
//            boolean correct = verifyPassword(hash, password, gs);
//
//            log.info("Entered password is correct: {}", correct);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
//            log.error(ex.getMessage(), ex);
//        }
//    }
    public static Sha512 crypt(String clairPass) {
        String gs=generateSalt();
        try {
            byte[] hash = encodePassword(clairPass, gs);
            return passwordToPersist(hash, clairPass, gs);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        String saltBase64_encoded=BaseEncoding.base64().encode(salt);
        String saltPlusDot= saltBase64_encoded.replace("+",".");
        return saltPlusDot.substring(0,saltPlusDot.length()-1);
    }
    private static byte[] encodePassword(String password,String salt) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        String mergedPasswordAndSalt =mergePasswordAndSalt(password, salt);
        MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
        byte[] hash = digester.digest(mergedPasswordAndSalt .getBytes("UTF-8"));
        for (int i = 1; i < ITERATIONS; ++i) {
            hash = digester.digest(Bytes.concat(hash, mergedPasswordAndSalt.getBytes("UTF-8")));
        }
        return hash;
    }
    private static String mergePasswordAndSalt(String pass, String salt) {
        if (salt == null) {
            return salt;
        }
        String cg="{";String cd="}";
        return pass+cg+salt+cd;
    }
    public static   boolean verifyPassword(byte[] originalHash, String password, String salt) throws
            NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] comparisonHash = encodePassword(password, salt);
        return comparePasswords(originalHash, comparisonHash);
    }
    private static Sha512 passwordToPersist(byte[] originalHash, String password, String salt) throws
            NoSuchAlgorithmException, UnsupportedEncodingException {
        Sha512 sh=new Sha512();
//        byte[] comparisonHash = encodePassword(password, salt);
        sh.setSalt(salt);sh.setHash(BaseEncoding.base64().encode(originalHash));
        return sh;
    }
    private static boolean comparePasswords(byte[] originalHash, byte[] comparisonHash) {
        int diff = originalHash.length ^ comparisonHash.length;
        for (int i = 0; i < originalHash.length && i < comparisonHash.length; i++) {
            diff |= originalHash[i] ^ comparisonHash[i];
        }

        return diff == 0;
    }

    public static boolean checkPassword(String saltReadyNotgenerated, String s) {
//Cette méthode prend salt de la base de donnés, genere un hash fur et à mesure, prend hash de la base de donnés et vérifie si ils sont égaux ou pas
        return false;
    }
}
