package model;


public class PaisDao {

    public static String idPais(String pais){
        String sql="select id from paises where descripcion='"+pais+"'";
        return sql;
    }
}
