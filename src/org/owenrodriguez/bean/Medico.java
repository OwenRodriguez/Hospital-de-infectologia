
package org.owenrodriguez.bean;


public class Medico {
 private int codMedico;
 private int licenciaMedica;
 private String nombre;
 private String apellidos;
 private String horaEntrada;
 private String horaSalida;
 private int turnoMaximo;
 private String sexo;

    public Medico() {
    }

    public Medico(int codMedico, int licenciaMedica, String nombre, String apellidos, String horaEntrada, String horaSalida, int turnoMaximo, String sexo) {
        this.codMedico = codMedico;
        this.licenciaMedica = licenciaMedica;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.turnoMaximo = turnoMaximo;
        this.sexo = sexo;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    public int getLicenciaMedica() {
        return licenciaMedica;
    }

    public void setLicenciaMedica(int licenciaMedica) {
        this.licenciaMedica = licenciaMedica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getTurnoMaximo() {
        return turnoMaximo;
    }

    public void setTurnoMaximo(int turnoMaximo) {
        this.turnoMaximo = turnoMaximo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
   

    @Override
    public String toString() {
        return getCodMedico() + "  |  " + getNombre() + ", "+ getApellidos();
    }
 
    
  
}
 

