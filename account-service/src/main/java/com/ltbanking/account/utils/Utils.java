package com.ltbanking.account.utils;

import java.util.Random;

public  class Utils {
    public static String generateCode(int lengthGenerate) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lengthGenerate; i++) {
            int codeG = random.nextInt(10);
            sb.append(codeG);
        }
        return sb.toString();
    }


}
