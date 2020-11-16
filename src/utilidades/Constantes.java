package utilidades;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 *
 * @author rodrigo_dev
 */
public class Constantes {

    public static String capturarIP() {
        String ipCapturado = "";
        try {

            InetAddress IP = InetAddress.getLocalHost();
            ipCapturado = IP.getHostAddress();
            //System.out.println("Mi ip local es = "+IP.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipCapturado;
    }
    
    public static String fechaActual(){
        String fecha="";
        Calendar calendario=Calendar.getInstance();
          
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH+1);
        int anio=calendario.get(Calendar.YEAR);
        
        String hora = String.format("%02d", calendario.get(Calendar.HOUR_OF_DAY));
        String minuto = String.format("%02d", calendario.get(Calendar.MINUTE));
        String segundo=String.format("%02d", calendario.get(Calendar.SECOND));
        
        fecha=anio+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
        
        return fecha;
    }
}
