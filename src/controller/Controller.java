package controller;

import model.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Conexion;
import model.Producto;
import model.ProductoDao;
import view.MenuPrincipalView;
import view.AccesoView;
import view.FacturacionView;
import view.MantenimientoView;

/**
 *
 * @author rodrigo_dev
 */
public class Controller implements ActionListener, FocusListener {

    /**
     * **********************************
     * Vistas    
    ***********************************
     */
    AccesoView loginVista = new AccesoView();
    MenuPrincipalView menu= new MenuPrincipalView();
    FacturacionView facturacion= new FacturacionView();
    MantenimientoView mantenimiento= new MantenimientoView();
    /**
     * **********************************
     * Modelos    
    ***********************************
     */
    Conexion conexion = new Conexion();
    Usuario usuario;
    /**
     * **********************************
     * DEFINICION DE VARIABLES DE CLASE    
    ***********************************
     */
    ResultSet resultSet;
    ArrayList<Usuario>listaUsuario;

    public Controller() {
        iniciarSistema();

        listener();
        try {
            cargarCombo("login");
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
        facturacion.txt_codigo_cliente.addFocusListener(this);
        
        mantenimiento._productos_btn_guardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == loginVista.btnAcceder) {
            login();
        }
        /*************************************************
        Eventos del menu principal
        *************************************************/
        if(evento.getSource()==menu.btnMantenimiento){
            mantenimiento.setVisible(true);
            mantenimiento.setTitle("Mantenimiento de datos");
            mantenimiento.setLocationRelativeTo(null);
        }
        if(evento.getSource()==menu.btnFacturacion){
            facturacion.setVisible(true);
            facturacion.setLocationRelativeTo(null);
            facturacion.setTitle("Facturación");
        }
        
        if(evento.getSource()==mantenimiento._productos_btn_guardar){
           validarProducto();
        }
    }

    private void login() {
        
        String user = loginVista.jComboBox1.getSelectedItem().toString();
        String password = loginVista.txt_password_login.getText();
        //this.resultSet=
        if(user.length()>0 && password.length()>0){
            if(validarUsuario(user, password).isEmpty()){
                JOptionPane.showMessageDialog(null, "No se encontaron coincidencias");
            }else{
                irAlMenu();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos.", "Error", 2);
        }
    }

    private void irAlMenu() {
        this.loginVista.setVisible(false);
        JOptionPane.showMessageDialog(null, "Acceso correcto al sistema.");
        menu.lblBienvenido.setText("Bienvenido: "+this.usuario.getNombre());
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
       // System.out.println("usuario = " + this.usuario.toString());
    }
    
    private void cargarCombo(String formulario) throws SQLException {
        if (formulario.equals("login")) {
            String sql = "select * from usuarios";
            this.resultSet = conexion.consultaSelect(sql);
            while (resultSet.next()) {
                loginVista.jComboBox1.removeAllItems();
                loginVista.jComboBox1.addItem(resultSet.getString("userName"));
            }
            conexion.closeConexion();
        }
    }

    private ArrayList<Usuario> validarUsuario(String user, String pws) {
        this.listaUsuario=new ArrayList<>();
        
        String sql="SELECT * FROM `usuarios` where userName='"+user+"' and userPassword='"+pws+"'";
        try {
            this.resultSet=conexion.consultaSelect(sql);
            while (resultSet.next()) {                
                this.usuario=new Usuario();
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
            System.out.println("Execption al intentar traer los usuarios: "+e);
            }
        return this.listaUsuario;
    }

    @Override
    public void focusGained(FocusEvent fe) {    
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(fe.getSource()==facturacion.txt_codigo_cliente){
            String codigo=facturacion.txt_codigo_cliente.getText();
            try {
                traerCliente(codigo);
            } catch (Exception e) {
                System.out.println("Exception al traer el cliente = " + e);
            }
        }
 
    }

    private void traerCliente(String codigo) throws SQLException {
        String resultado="";
        String sql="select * from clientes where documento="+codigo;
        resultSet=conexion.consultaSelect(sql);
        
        if(resultSet!=null){
            while(resultSet.next()){
                resultado=resultSet.getString("razon_social");
                
            }
            facturacion.txt_nombre_cliente.setText(resultado);
            conexion.closeConexion();
        }else{
            JOptionPane.showMessageDialog(null, "No se encuentra el cliente", "Advertencia", 2);
        }
    }

    private void validarProducto() {
        double precioIpuesto=0;
        String codigo=mantenimiento._productos_txt_codigo.getText();
        String descripcion=mantenimiento._productos_txt_descripcion.getText();
        double precio=Double.parseDouble(mantenimiento._productos_txt_precio.getText());
        String impuesto="1";
        if(impuesto.equals("1")){
            precioIpuesto=((precio*10)*100);
        }
        int cantidad=Integer.parseInt(mantenimiento._productos_txt_cantidad.getText());
        int proveedor=Integer.parseInt("1");
        
        Producto producto= new Producto();
        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setPreciodescuento(precioIpuesto);
        producto.setImpuesto(Integer.parseInt(impuesto));
        producto.setCantidad(cantidad);
        producto.setIdProveedor(proveedor);
        
        
       // System.out.println(ProductoDao.insertarProducto(producto, this.usuario.getId()));
        String insert= ProductoDao.insertarProducto(producto, this.usuario.getId());
        Connection con=conexion.openConexion();
        if(conexion.insertarRegistro(insert)){
            JOptionPane.showMessageDialog(null, "Registro insertado con exito", "OK", 1);
        }
       
    }

    
}
