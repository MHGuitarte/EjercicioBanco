package Classes;

import Interfaces.Administrable;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class CPersona implements Administrable, Serializable {

    //Declaración de variables
    private String nombre, apellidos, dni, tipo;

    //Constructores
    public CPersona() {
    }

    public CPersona(String nombre, String apellidos, String dni, String tipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipo = tipo;
    }

    //Getter & Setter
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Overriden Methods
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nApellidos: " + apellidos + "\nDNI: " + dni + "\nPrivilegios: " + tipo;
    }

    //Implemented Methods
    @Override
    public void ingresar(CCuenta cuenta, double cantidad) {
        double saldo = cuenta.getSaldo();
        cuenta.setSaldo(saldo + cantidad);
    }

    @Override
    public void retirar(CCuenta cuenta, double cantidad) {
        double saldo = cuenta.getSaldo();
        cuenta.setSaldo(saldo - cantidad);
    }

    
    
    @Override
    public void listarCuentas(ArrayList<CCuenta> listaCuenta) {
        for (int i = 0; i < listaCuenta.size(); i++) {

            System.out.println("Número de cuenta: " + listaCuenta.get(i).getNumCuenta() + "\n"
                    + "DNI: " + listaCuenta.get(i).getDni() + "\n"
                    + "Sucursal: " + listaCuenta.get(i).getSucursal() + "\n"
                    + "Saldo: " + listaCuenta.get(i).getSaldo() + "\n \n");

        }
    }

}
