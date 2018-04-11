/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aminos
 */
public class Html2PDFText {
   
    public static void main(String[] args) {
        try {
            Html2PDF.convert("<h1>Hi</h1><p>hello</p>", "/home/aminos/a.pdf");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Html2PDFText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Html2PDFText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
