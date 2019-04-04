package Classes;

import java.io.Serializable;
/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class CCuenta implements Serializable {

    //Declaración de variables
    private String numCuenta, dni, sucursal;
    private double saldo;

    //Constructores
    public CCuenta() {
    }

    public CCuenta(String numCuenta, String dni, String sucursal, double saldo) {
        this.numCuenta = numCuenta;
        this.dni = dni;
        this.sucursal = sucursal;
        this.saldo = saldo;
    }

    //Getter & Setter
    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    //Overriden Methods
    @Override
    public String toString() {
        return "Nº Cuenta: " + numCuenta + "\nSucursal: " + sucursal + "\nSaldo: " + saldo;
    }

    public String toStringEmpleado() {
        return "Nº Cuenta: " + numCuenta + "\nSucursal: " + sucursal + "\nSaldo: " + saldo + "\nDNI propietario: " + dni;
    }

}
