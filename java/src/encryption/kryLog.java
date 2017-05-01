/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

/**
 *
 * @author CHILSE
 */
public class kryLog {

    private boolean exSec = true;

    public String kryLog(String[] num1, String[] num2, String[] num3, String[] num4) {

        String fullKey = "";

        for (String num21 : num2) {
            fullKey += num21;
        }

        if (exSec) {
            fullKey = rota(fullKey);
        }

        for (String num11 : num1) {
            fullKey += num11;
        }

        if (exSec) {
            fullKey = rota(fullKey);
        }

        for (String num41 : num4) {
            fullKey += num41;
        }

        if (exSec) {
            fullKey = rota(fullKey);
        }

        for (String num31 : num3) {
            fullKey += num31;
        }

        char[] keyChar;

        if (exSec) {
            keyChar = rota(fullKey).toCharArray();
        } else {
            keyChar = fullKey.toCharArray();
        }

        int litte = 0;
        int big = 0;
        int ente = 0;

        for (int x = 0; x < keyChar.length; x++) {
            if (keyChar[x] >= 48 && keyChar[x] <= 57) {
                ente++;
            } else if (keyChar[x] >= 97 && keyChar[x] <= 122) {
                litte++;
            } else if (keyChar[x] >= 65 && keyChar[x] <= 90) {
                big++;
            }
        }

        String key = "";

        int temp = 0;
        for (int keyCount = 0; keyCount < 100; keyCount++) {
            if (temp + litte < keyChar.length) {
                temp += litte;
            } else {
                temp = temp - keyChar.length + litte;
            }
            key += keyChar[temp];
        }

        if (exSec) {
            key = rota(key);
        }

        for (int keyCount = 0; keyCount < 100; keyCount++) {
            if (temp + big < keyChar.length) {
                temp += big;
            } else {
                temp = temp - keyChar.length + big;
            }
            key += keyChar[temp];
        }

        if (exSec) {
            key = rota(key);
        }

        for (int keyCount = 0; keyCount < 100; keyCount++) {
            if (temp + ente < keyChar.length) {
                temp += ente;
            } else {
                temp = temp - keyChar.length + ente;
            }
            key += keyChar[temp];
        }
        if (exSec) {
            return rota(key);
        } else {
            return key;
        }
    }

    private String rota(String text) {
        char[] input;
        input = text.toCharArray();
        String output = "";
        char temp;
        for (int i = 0; i < input.length / 2; i++) {
            if (i % 2 == 1) {
                temp = input[i];
                input[i] = input[input.length - 1 - i];
                input[input.length - 1 - i] = temp;
            }
        }

        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 1) {
                temp = input[i - 1];
                input[i - 1] = input[i];
                input[i] = temp;
            }
        }

        for (int i = input.length - 1; i >= 0; i--) {
            output += input[i];
        }

        return output;
    }
}
