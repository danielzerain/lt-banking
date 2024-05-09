package com.ltbanking.account;

import java.util.Random;

public class utils {
    private static String generateCode(int lengthGenerate) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lengthGenerate; i++) {
            int codeG = random.nextInt(10);
            sb.append(codeG);
        }
        return sb.toString();
    }


}
