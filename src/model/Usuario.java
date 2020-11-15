package model;

/**
 *
 * @author rodrigo_dev
 */
public class Usuario {
    
    private String id, nombre, apellido, userName, userPassord, email, sexo, direccion, create_at, update_at,server;
    
    private int edad, idCiudad, idEstado, idGrupo, idPais;

    public int getIdPais() {
        return idPais;
    }

   

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassord() {
        return userPassord;
    }

    public void setUserPassord(String userPassord) {
        this.userPassord = userPassord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public Usuario() {
    }

     public Usuario(String id, String nombre, String apellido, String userName, String userPassord, String email, String sexo, String direccion, String create_at, String update_at, String server, int edad, int idCiudad, int idEstado, int idGrupo, int idPais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.userName = userName;
        this.userPassord = userPassord;
        this.email = email;
        this.sexo = sexo;
        this.direccion = direccion;
        this.create_at = create_at;
        this.update_at = update_at;
        this.server = server;
        this.edad = edad;
        this.idCiudad = idCiudad;
        this.idEstado = idEstado;
        this.idGrupo = idGrupo;
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", userName=" + userName + ", userPassord=" + userPassord + ", email=" + email + ", sexo=" + sexo + ", direccion=" + direccion + ", create_at=" + create_at + ", update_at=" + update_at + ", server=" + server + ", edad=" + edad + ", idCiudad=" + idCiudad + ", idEstado=" + idEstado + ", idGrupo=" + idGrupo + ", idPais=" + idPais + '}';
    }

    
    
    
}
