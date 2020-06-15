
package org.owenrodriguez.bean;


public class RegistrarUsuarios {
    private int codUsuario;
    private String usuarioLogin;
    private String usuarioContrasena;
    private String usuarioEstado;
    private String usuarioFecha;
    private String usuarioHora;

    public RegistrarUsuarios(int codUsuario, String usuarioLogin, String usuarioContrasena, String usuarioEstado, String usuarioFecha, String usuarioHora) {
        this.codUsuario = codUsuario;
        this.usuarioLogin = usuarioLogin;
        this.usuarioContrasena = usuarioContrasena;
        this.usuarioEstado = usuarioEstado;
        this.usuarioFecha = usuarioFecha;
        this.usuarioHora = usuarioHora;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    public String getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(String usuarioEstado) {
        this.usuarioEstado = usuarioEstado;
    }

    public String getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(String usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    public String getUsuarioHora() {
        return usuarioHora;
    }

    public void setUsuarioHora(String usuarioHora) {
        this.usuarioHora = usuarioHora;
    }
    
    
    
}
