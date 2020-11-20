package utilidades;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Conexion;

/**
 *
 * @author rodrigo_dev
 */
public class Globales {

    DefaultTableModel modelo = new DefaultTableModel();

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

    public static String fechaActual() {
        String fecha = "";
        Calendar calendario = Calendar.getInstance();

        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);

        String hora = String.format("%02d", calendario.get(Calendar.HOUR_OF_DAY));
        String minuto = String.format("%02d", calendario.get(Calendar.MINUTE));
        String segundo = String.format("%02d", calendario.get(Calendar.SECOND));

        fecha = anio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;

        return fecha;
    }

    public static String columnasTablas(String tabla) {
        return "SHOW COLUMNS FROM " + tabla;
    }

    public void cargarTabla(ResultSet resultado, JTable tabla, String tablaBd) {

        /*Conexion conexion= new Conexion();
         try {
         ResultSet resultadoColumnas=conexion.consultaSelect(Globales.columnasTablas(tablaBd));
         while (resultadoColumnas.next()) {                
         modelo.addColumn(resultadoColumnas.getString("Field"));
         }
       
         } catch (Exception e) {
         System.out.println("e = " + e);
         }*/
        if (tablaBd.equals("clientes")) {
            this.modelo.addColumn("DOCUMENTO");
            this.modelo.addColumn("TIPO");
            this.modelo.addColumn("NOMBRE");
            this.modelo.addColumn("RAZON");
            this.modelo.addColumn("CIUDAD");
            this.modelo.addColumn("PAIS");
            this.modelo.addColumn("TEL/CEL");

            tabla.setModel(modelo);

            try {

                String[] registros = new String[7];
                while (resultado.next()) {
                    registros[0] = resultado.getString("documento");
                    registros[1] = resultado.getString("Tipo_DOC");
                    registros[2] = resultado.getString("nombre");
                    registros[3] = resultado.getString("razon_social");
                    registros[4] = resultado.getString("ciudad");
                    registros[5] = resultado.getString("pais");
                    registros[6] = resultado.getString("telefono_movil");
                    this.modelo.addRow(registros);
                }
                tabla.setModel(modelo);
            } catch (Exception e) {

                System.out.println("e = " + e);
            }
        }

    }
}
