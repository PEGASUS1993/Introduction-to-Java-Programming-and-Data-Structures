package chapter38;

import java.io.*;
import java.util.*;

public class Exercise38_5 {
    /**Process the HTTP Get request*/
    public String[] getNameScore(String ssn, String course) throws Exception {
        BufferedReader in = null;
        
        boolean found = false;
        
        in = new BufferedReader(new FileReader(
                "c:\\exercise\\" + course + ".txt"));
        
        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, "#\n\r\t");
            
            String name = st.nextToken();
            String ssnFromFile = st.nextToken();
            String score = st.nextToken();
            
            System.out.println(name + " " + ssnFromFile + " " + score);
            
            if (ssn.equals(ssnFromFile)) {
                String[] result = new String[2];
                result[0] = name;
                result[1] = score;
                return result;
            } else
                return null;
        }
        
        return null;
    }
}
