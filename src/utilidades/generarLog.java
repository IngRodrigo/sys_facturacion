package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rodrigo_dev
 */
public class generarLog {

    public void ejecutarLog(String tipo, String mensaje){
        BufferedWriter bw = null;
    FileWriter fw = null;

    try {
                String data = "Resultado: "+tipo+" Detalle: "+mensaje+"\n";
                File file = new File("log.txt");
                // Si el archivo no existe, se crea!
                if (!file.exists()) {
                    file.createNewFile();
                }
                // flag true, indica adjuntar información al archivo.
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(data);
                System.out.println("información agregada!");
        
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    }
}
