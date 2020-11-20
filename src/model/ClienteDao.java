package model;

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
}
