package model;

public class CiudadDao {
    public static String idCiudad(String ciudad){
        String sql="select id from ciudades where descripcion='"+ciudad+"'";
        return sql;
    }
    
}
