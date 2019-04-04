package Classes;

import java.io.Serializable;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class CUsuario implements Comparable<CUsuario>, Serializable {

    //Declaración de variables
    private String dni, pass;

    //Constructores
    public CUsuario() {
    }

    public CUsuario(String dni, String pass) {
        this.dni = dni;
        this.pass = pass;

    }

    //Getter & Setter
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int compareTo(CUsuario o) {
        try {
            return (this.dni.compareTo(o.dni));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "DNI: " + dni + "\nPass: " + pass;
    }

}
