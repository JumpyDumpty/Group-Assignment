package treeviz;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TreeWriter {
    public static void WriteNewTree(String filePath, String Unittype, String Diam, String Botname, String lon, String lat){

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
            out.newLine();
            out.write(Unittype + "," + Diam + "," + "MISS" + "," + Botname + "," + lon + "," + lat);







            out.close();
        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }
}
