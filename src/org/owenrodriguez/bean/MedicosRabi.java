
package org.owenrodriguez.bean;

public class MedicosRabi {
    
    private String colegiado;
    private String nombreMedico;
    private String apellidosMedico;
    private String email;
    private int extension;

    public MedicosRabi() {
    }

    public MedicosRabi(String colegiado, String nombreMedico, String apellidosMedico, String email, int extension) {
        this.colegiado = colegiado;
        this.nombreMedico = nombreMedico;
        this.apellidosMedico = apellidosMedico;
        this.email = email;
        this.extension = extension;
    }

    public String getColegiado() {
        return colegiado;
    }

    public void setColegiado(String colegiado) {
        this.colegiado = colegiado;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidosMedico() {
        return apellidosMedico;
    }

    public void setApellidosMedico(String apellidosMedico) {
        this.apellidosMedico = apellidosMedico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

   
}
