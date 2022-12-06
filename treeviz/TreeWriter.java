package treeviz;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Write trees to files.
 */
public class TreeWriter {
    /**
     * Write trees to a csv file given tree info
     *
     * @param fileName Name of the file to read
     * @param Unittype DECID, CONFIR or MULTI
     * @param Diam Diameter of tree
     * @param Botname Botanical name of tree
     * @param lon Longitude of tree location
     * @param lat latitude of tree location
     */
    public static void WriteNewTree(String fileName, String Unittype, String Diam, String Botname, String lon, String lat){

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.newLine();
            out.write(Unittype + "," + Diam + "," + "MISS" + "," + Botname + "," + lon + "," + lat);







            out.close();
        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }
}
