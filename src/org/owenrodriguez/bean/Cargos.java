
package org.owenrodriguez.bean;


public class Cargos {
    public int codigoCargo;
    public String nombreCargo;

    public Cargos() {
    }

    public Cargos(int codigoCargo, String nombreCargo) {
        this.codigoCargo = codigoCargo;
        this.nombreCargo = nombreCargo;
    }

    public int getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }
    
    
}
