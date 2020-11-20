package controller;

import java.awt.Color;
import java.awt.Frame;
import model.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Cliente;
import model.ClienteDao;
import model.Conexion;
import model.Producto;
import model.ProductoDao;
import utilidades.Globales;
import view.MenuPrincipalView;
import view.AccesoView;
import view.ClientesOperaciones;
import view.FacturacionView;
import view.MantenimientoView;

/**
 *
 * @author rodrigo_dev
 */
public class Controller implements ActionListener, FocusListener, MouseListener {

    /**
     * **********************************
     * Vistas **********************************
     */
    AccesoView loginVista = new AccesoView();
    MenuPrincipalView menu = new MenuPrincipalView();
    FacturacionView facturacion = new FacturacionView();
    MantenimientoView mantenimiento = new MantenimientoView();
    ClientesOperaciones clientesVista = new ClientesOperaciones();
    /**
     * **********************************
     * Modelos **********************************
     */
    Conexion conexion = new Conexion();
    Usuario usuario;
    /**
     * **********************************
     * DEFINICION DE VARIABLES DE CLASE **********************************
     */
    ResultSet resultSet;
    ArrayList<Usuario> listaUsuario;
    ArrayList<Cliente> listaClientes;

    public Controller() {
        iniciarSistema();

        listener();
        try {
            cargarCombo("login", loginVista.jComboBox1);
        } catch (Exception e) {
            System.out.println("Execption al intentar cargar el combo usuario login");
        }

    }

    public void iniciarSistema() {
        loginVista.setVisible(true);
        loginVista.setLocationRelativeTo(null);

    }

    private void listener() {
        loginVista.btnAcceder.addActionListener(this);
        menu.btnMantenimiento.addActionListener(this);
        menu.btnFacturacion.addActionListener(this);
        menu._menu_btn_clientes.addActionListener(this);
        facturacion.txt_codigo_cliente.addFocusListener(this);

        mantenimiento._productos_btn_guardar.addActionListener(this);

        clientesVista._clientes_btn_todos.addActionListener(this);
        clientesVista._clientes_btn_guardar.addActionListener(this);
        clientesVista._clientes_tabla.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == loginVista.btnAcceder) {
            login();
        }
        /**
         * ***********************************************
         * Eventos del menu principal
         * ***********************************************
         */
        if (evento.getSource() == menu.btnMantenimiento) {
            mantenimiento.setVisible(true);
            mantenimiento.setTitle("Mantenimiento de datos");
            mantenimiento.setLocationRelativeTo(null);
        }
        if (evento.getSource() == menu.btnFacturacion) {
            facturacion.setVisible(true);
            facturacion.setLocationRelativeTo(null);
            facturacion.setTitle("Facturación");
        }

        if (evento.getSource() == menu._menu_btn_clientes) {
            mostrarFormularioClientes();
        }

        if (evento.getSource() == mantenimiento._productos_btn_guardar) {
            validarEInsertarProducto();
        }

        /**
         * ***********************************************
         * Eventos del formulario clientes operaciones
         * ***********************************************
         */
        if (evento.getSource() == clientesVista._clientes_btn_todos) {
            cargarTablaCliente();
        }
        if (evento.getSource() == clientesVista._clientes_btn_guardar) {
            validarEInsertarClientes();
        }
    }

    private void login() {

        String user = loginVista.jComboBox1.getSelectedItem().toString();
        String password = loginVista.txt_password_login.getText();
        //this.resultSet=
        if (user.length() > 0 && password.length() > 0) {
            if (validarUsuario(user, password).isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontaron coincidencias");
            } else {
                irAlMenu();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos.", "Error", 2);
        }
    }

    private void irAlMenu() {
        this.loginVista.setVisible(false);
        JOptionPane.showMessageDialog(null, "Acceso correcto al sistema.");
        menu.lblBienvenido.setText("Bienvenido: " + this.usuario.getNombre());
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.setExtendedState(Frame.MAXIMIZED_BOTH);
        menu._menu_fecha.setText("FECHA ACTUAL: " + Globales.fechaActual().substring(0, 10));
        // System.out.println("usuario = " + this.usuario.toString());
    }

    private void cargarCombo(String formulario, JComboBox combo) throws SQLException {
        if (formulario.equals("login")) {
            String sql = "select * from usuarios";
            this.resultSet = conexion.consultaSelect(sql);
            combo.removeAllItems();
            while (resultSet.next()) {
                combo.addItem(resultSet.getString("userName"));
            }
            conexion.closeConexion();
        }

        if (formulario.equals("ciudad")) {
            String sql = "Select * from ciudades";
            try {
                this.resultSet = conexion.consultaSelect(sql);
                combo.removeAllItems();
                while (resultSet.next()) {
                    combo.addItem(resultSet.getString("descripcion"));
                }
                conexion.closeConexion();
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
    }

    private ArrayList<Usuario> validarUsuario(String user, String pws) {
        this.listaUsuario = new ArrayList<>();

        String sql = "SELECT * FROM `usuarios` where userName='" + user + "' and userPassword='" + pws + "'";
        try {
            this.resultSet = conexion.consultaSelect(sql);
            while (resultSet.next()) {
                this.usuario = new Usuario();
                this.usuario.setId(resultSet.getString("id"));
                this.usuario.setNombre(resultSet.getString("nombre"));
                this.usuario.setApellido(resultSet.getString("apellido"));
                this.usuario.setUserName(resultSet.getString("userName"));
                this.usuario.setUserPassord(resultSet.getString("userPassword"));
                this.usuario.setEmail(resultSet.getString("email"));
                this.usuario.setEdad(resultSet.getInt("edad"));
                this.usuario.setSexo(resultSet.getString("sexo"));
                this.usuario.setDireccion(resultSet.getString("direccion"));
                this.usuario.setIdCiudad(resultSet.getInt("idCiudad"));
                this.usuario.setIdPais(resultSet.getInt("idPais"));
                this.usuario.setIdEstado(resultSet.getInt("idEstado"));
                this.usuario.setIdGrupo(resultSet.getInt("idGrupo"));
                this.usuario.setCreate_at(resultSet.getString("create_at"));
                this.usuario.setUpdate_at(resultSet.getString("update_at"));
                this.usuario.setServer(resultSet.getString("server"));
                this.listaUsuario.add(this.usuario);
            }
            conexion.closeConexion();
        } catch (Exception e) {
            System.out.println("Execption al intentar traer los usuarios: " + e);
        }
        return this.listaUsuario;
    }

    @Override
    public void focusGained(FocusEvent fe) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == facturacion.txt_codigo_cliente) {
            String codigo = facturacion.txt_codigo_cliente.getText();
            try {
                if (codigo.length() > 0) {
                    traerCliente(codigo);
                } else {
                    int respuestaUser = JOptionPane.showConfirmDialog(null, "¿Desea crearlo?", "CLIENTE NO ENCONTRADO", 1);
                    System.out.println("respuestaUser = " + respuestaUser);
                    if (respuestaUser == 0) {
                        mostrarFormularioClientes();
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception al traer el cliente = " + e);
            }
        }

    }

    private void traerCliente(String codigo) throws SQLException {
        String resultado = "";
        String sql = "select * from clientes where documento=" + codigo;
        resultSet = conexion.consultaSelect(sql);

        if (resultSet != null) {
            while (resultSet.next()) {
                resultado = resultSet.getString("razon_social");

            }
            if (resultado.length() > 0) {
                facturacion.txt_nombre_cliente.setText(resultado);
            } else {
                int respuestaUser = JOptionPane.showConfirmDialog(null, "¿Desea crearlo?", "CLIENTE NO ENCONTRADO", 1);
                System.out.println("respuestaUser = " + respuestaUser);
                if (respuestaUser == 0) {
                    mostrarFormularioClientes();
                }
            }

            conexion.closeConexion();
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra el cliente", "Advertencia", 2);
        }
    }

    private void validarEInsertarProducto() {
        double precioIpuesto = 0;
        String codigo = mantenimiento._productos_txt_codigo.getText();
        String descripcion = mantenimiento._productos_txt_descripcion.getText();
        double precio = Double.parseDouble(mantenimiento._productos_txt_precio.getText());
        String impuesto = "1";
        if (impuesto.equals("1")) {
            precioIpuesto = ((precio * 10) / 100);
        }
        int cantidad = Integer.parseInt(mantenimiento._productos_txt_cantidad.getText());
        int proveedor = Integer.parseInt("1");

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setPreciodescuento(precioIpuesto);
        producto.setImpuesto(Integer.parseInt(impuesto));
        producto.setCantidad(cantidad);
        producto.setIdProveedor(proveedor);

        System.out.println(ProductoDao.insertarProducto(producto, this.usuario.getId()));
        String insert = ProductoDao.insertarProducto(producto, this.usuario.getId());

        if (conexion.insertarRegistro(insert)) {
            JOptionPane.showMessageDialog(null, "Registro insertado con exito", "OK", 1);
            conexion.closeConexion();
        }

    }

    private void validarEInsertarClientes() {
        if (clientesVista._clientes_documento.getText().length() > 0) {
            if (clientesVista._clientes_razon.getText().length() > 0) {
                if (clientesVista._clientes_nombre.getText().length() > 0) {

                } else {
                    JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio", "ERROR", 2);
                    clientesVista._clientes_nombre.setBackground(Color.RED);
                    clientesVista._clientes_nombre.setForeground(Color.WHITE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo razon social no puede estar vacio", "ERROR", 2);
                clientesVista._clientes_razon.setBackground(Color.RED);
                clientesVista._clientes_razon.setForeground(Color.WHITE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "El campo documento no puede estar vacio", "ERROR", 2);
            clientesVista._clientes_documento.setBackground(Color.RED);
            clientesVista._clientes_documento.setForeground(Color.WHITE);
        }
        Cliente cliente = new Cliente();

    }

    private void cargarTablaCliente() {
        Globales go = new Globales();
        String sql = ClienteDao.listarClientes();

        try {
            resultSet = conexion.consultaSelect(sql);
            go.cargarTabla(resultSet, clientesVista._clientes_tabla, "cxlientes");
            conexion.closeConexion();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

    }

    @Override
    public void mouseClicked(MouseEvent eventoMosue) {
        if (eventoMosue.getSource() == clientesVista._clientes_tabla) {
            //clientesVista._clientes_tabla.setGridColor(Color.yellow);
//           System.out.println("Filas tablas: "+clientesVista._clientes_tabla.getValueAt(clientesVista._clientes_tabla.getSelectedRow(), 0));
            String documento = clientesVista._clientes_tabla.getValueAt(clientesVista._clientes_tabla.getSelectedRow(), 0).toString();

            try {
                resultSet = conexion.consultaSelect(ClienteDao.cliente(documento));
                while (resultSet.next()) {
                    clientesVista._clientes_documento.setText(resultSet.getString("documento"));
                }
                conexion.closeConexion();
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    private void mostrarFormularioClientes() {
        clientesVista.setVisible(true);
        clientesVista.setTitle("Clientes");
        clientesVista.setLocationRelativeTo(null);
        try {
            cargarCombo("ciudad", clientesVista._clientes_combo_ciudad);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
