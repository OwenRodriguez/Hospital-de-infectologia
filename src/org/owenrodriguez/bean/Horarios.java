
package org.owenrodriguez.bean;


public class Horarios {
   public int codHorario;
   public String horarioInicio;
   public String horarioSalida;
   public String lunes;
   public String martes;
   public String miercoles;
   public String jueves;
   public String viernes;

    public Horarios() {
    }

    public Horarios(int codHorario, String horarioInicio, String horarioSalida, String lunes, String martes, String miercoles, String jueves, String viernes) {
        this.codHorario = codHorario;
        this.horarioInicio = horarioInicio;
        this.horarioSalida = horarioSalida;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
    }

    public int getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(int codHorario) {
        this.codHorario = codHorario;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }
    
    

    
}
