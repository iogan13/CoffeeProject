/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import encryption.crypByByte;

/**
 *
 * @author chilse
 */
public class testCryp {

    public static void main(String[] args) {
        new testCryp();
    }

    public testCryp() {

        String value = "chilse2:00.00";
        crypByByte crypt = new crypByByte(null, 128);
        crypt.setupAlgorythm(true, true, true);
        crypt.updateContent(value.getBytes());
        byte[] crp = crypt.startEncryption();

        crypt.updateContent(crp);
        //crypt.setupAlgorythm(true, false, false);

        byte[] crpE = crypt.startDecryption();
        System.out.println(value + "\n" + new String(crp) + "\n" + new String(crpE));
    }
}
