/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sqlLite.sqlDB;

/**
 *
 * @author chilse
 */
public class testSql {

    public static void main(String[] args) {
        new testSql();
    }

    public testSql() {
        sqlDB test = new sqlDB("c:/NiHil/cpayDB.sqlite");

        if (!test.addUser("chilse2", false, true, 1)) {
            System.out.println(test.getError());
        }
        //test.updateAcc(2, "value");
        /*String[] user = test.getUser();
        for (int i = 0; i < user.length; i++) {
            System.out.println(user[i]);
        }*/
        //System.out.println(test.getUserIdByName("chilse2"));

    }
}
