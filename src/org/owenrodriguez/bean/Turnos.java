
package org.owenrodriguez.bean;


public class Turnos {
      public int codigoTurno;
       public String fechaturno;
       public String fechaCita;
       public String valorCita;
       public String codigoMedicoEspecialidad;

    public Turnos() {
    }

    public Turnos(int codigoTurno, String fechaturno, String fechaCita, String valorCita, String codigoMedicoEspecialidad) {
        this.codigoTurno = codigoTurno;
        this.fechaturno = fechaturno;
        this.fechaCita = fechaCita;
        this.valorCita = valorCita;
        this.codigoMedicoEspecialidad = codigoMedicoEspecialidad;
    }

    public int getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(int codigoTurno) {
        this.codigoTurno = codigoTurno;
    }

    public String getFechaturno() {
        return fechaturno;
    }

    public void setFechaturno(String fechaturno) {
        this.fechaturno = fechaturno;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getValorCita() {
        return valorCita;
    }

    public void setValorCita(String valorCita) {
        this.valorCita = valorCita;
    }

    public String getCodigoMedicoEspecialidad() {
        return codigoMedicoEspecialidad;
    }

    public void setCodigoMedicoEspecialidad(String codigoMedicoEspecialidad) {
        this.codigoMedicoEspecialidad = codigoMedicoEspecialidad;
    }
       
       
    
}
