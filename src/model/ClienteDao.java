package model;

import utilidades.Globales;

public class ClienteDao {

    public static String listarClientes() {

        String sql = "SELECT\n"
                + "	c.documento,\n"
                + "	CONCAT(c.nombre, \" \", c.apellido) AS nombre,\n"
                + "	c.razon_social,\n"
                + "	ciu.descripcion AS ciudad,\n"
                + "	p.descripcion as pais,\n"
                + "	c.telefono_movil,\n"
                + "	t.descripcion as 'Tipo_DOC',\n"
                + "	c.id,\n"
                + "	c.idUser\n"
                + "FROM\n"
                + "	clientes AS c\n"
                + "INNER JOIN ciudades AS ciu ON c.idCiudad = ciu.id\n"
                + "INNER JOIN paises AS p ON c.idPais = p.id\n"
                + "INNER JOIN tipos_documentos as t on c.idTipoDocumento=t.id";
        return sql;
    }

    public static String cliente(String documento) {
        String sql = " SELECT\n"
                + "	*\n"
                + "FROM\n"
                + "	clientes where documento='"+documento+"'";
        return sql;
    }
    
    public static String insertarCliente(Cliente cliente, String usuario){

        String sql="insert into clientes (documento, razon_social, nombre, apellido, idCiudad, idPais, direccion, telefono, telefono_movil, idTipoDocumento, create_at, update_at, idUser, ipServidor)"
                + " values ('"+cliente.getDocumento()+"', '"+cliente.getRazon_social()+"', '"+cliente.getNombre()+"', '"+cliente.getApellido()+"', '"+cliente.getIdCiudad()+"', "
                + "'"+cliente.getIdPais()+"', '"+cliente.getDireccion()+"', '"+cliente.getTelefono()+"', '"+cliente.getTelefono_movil()+"', '"+cliente.getIdTipoDocumento()+"', '"+Globales.fechaActual()+"', '"+Globales.fechaActual()+"', "
                + "'"+usuario+"', '"+Globales.capturarIP()+"')";
        return sql;
    }
}
