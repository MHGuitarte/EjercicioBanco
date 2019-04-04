package Interfaces;

import Classes.CCuenta;
import java.util.ArrayList;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public interface Administrable {
    
    public void ingresar(CCuenta cuenta, double cantidad);
    public void retirar(CCuenta cuenta, double cantidad);
    public void listarCuentas(ArrayList<CCuenta> listaCuenta);
    
}
