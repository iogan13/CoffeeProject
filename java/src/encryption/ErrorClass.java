/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CHILSE
 */
public class ErrorClass extends JFrame {

    /**
     *
     * @param e
     */
    public void exceptioninfo(Exception e) {
        StackTraceElement[] elements = e.getStackTrace();
        String text = e.toString();
        error(text);
    }

    /**
     *
     * @param errortext
     */
    public void error(String errortext) {
        JOptionPane.showMessageDialog(this,
                errortext,
                "Exception was thrown",
                JOptionPane.ERROR_MESSAGE);
    }

}
