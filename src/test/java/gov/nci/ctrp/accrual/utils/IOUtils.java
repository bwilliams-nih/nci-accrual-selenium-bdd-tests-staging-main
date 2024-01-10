package gov.nci.ctrp.accrual.utils;

import com.sun.jna.WString;

import java.io.*;

public class IOUtils {

    public static String copyFile(String inputFile, String outputFile) {
        File in = new File(inputFile).getAbsoluteFile();
        File out = new File(outputFile).getAbsoluteFile();
        FileInputStream fis = null;
        try {
            Thread.sleep(3000);
            fis = new FileInputStream(in);
            FileOutputStream fos = new FileOutputStream(out);
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }


}
