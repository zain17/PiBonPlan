/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

//import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

/**
 *
 * @author aminos
 */
public class Html2PDF {

    public static void convert(String html, String destination) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String tmpfile = "tmp" + LocalDate.now()+Math.random() + ".html";
        PrintWriter writer = new PrintWriter(tmpfile, "UTF-8");
        writer.println(html);
        writer.close();
        System.out.println(destination + " " + tmpfile);
        Process p = Runtime.getRuntime().exec("wkhtmltopdf " + tmpfile + " " + destination);
        //System.out.println(p.exitValue());
        while (p.isAlive());
        Runtime.getRuntime().exec("rm " + tmpfile);
    }
}
