package model;

import utilidades.Globales;

public class ProductoDao {

    public static String insertarProducto(Producto producto, String usuario) {

        String codigo = producto.getCodigo();
        String descripcion = producto.getDescripcion();
        String precio = String.valueOf(producto.getPrecio());
        String precioConImpuesto = String.valueOf(producto.getPreciodescuento());

        String impuesto = String.valueOf(producto.getImpuesto());
        int cantidad = producto.getCantidad();
        int proveedor = producto.getIdProveedor();

        String create_at = Globales.fechaActual();
        String update_at = Globales.fechaActual();

        String ip = Globales.capturarIP();
        String idUsuario = usuario;

        String insert = "insert into productos (codigo, descripcion, precio_neto, precio_impuesto, idImpuesto, cantidad, idProveedor, create_at, update_at, ipCreacion, idUsuario) "
                + "values('" + codigo + "', '" + descripcion + "', '" + precio + "', '" + precioConImpuesto + "', '" + impuesto + "', '" + cantidad + "', '" + proveedor + "', '" + create_at + "', '" + update_at + "', '" + ip + "', '" + idUsuario + "')";

        return insert;
    }
    
    
    public static String producto(String codigo){
        String sql="SELECT * FROM sys_facturacion.productos where codigo='"+codigo+"'";
        
        return sql;
    }
}
