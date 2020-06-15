
package org.owenrodriguez.bean;


public class TelefonoMedicos {
    private int codTelefonoMedico;
    private String telefonoPersonal;
    private String telefonoTrabajo;
    private int codMedico;

    public TelefonoMedicos() {
    }

    public TelefonoMedicos(int codTelefonoMedico, String telefonoPersonal, String telefonoTrabajo, int codMedico) {
        this.codTelefonoMedico = codTelefonoMedico;
        this.telefonoPersonal = telefonoPersonal;
        this.telefonoTrabajo = telefonoTrabajo;
        this.codMedico = codMedico;
    }

    public int getCodTelefonoMedico() {
        return codTelefonoMedico;
    }

    public void setCodTelefonoMedico(int codTelefonoMedico) {
        this.codTelefonoMedico = codTelefonoMedico;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    
}
