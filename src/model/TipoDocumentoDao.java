/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rodrigo_dev
 */
public class TipoDocumentoDao {
    
   public static String idTipoDocumento(String documento){
       String sql="select id from tipos_documentos where descripcion='"+documento+"'";
       return sql;
   }
   
   public static String listaTipoDocumentos(){
       String sql="select * from sys_facturacion.tipos_documentos order by descripcion asc";
       return sql;
   }
}
