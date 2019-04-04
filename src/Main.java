
import Classes.CCuenta;
import Classes.CPersona;
import Classes.CUsuario;
import Utils.ObjOutStream;
import static Utils.Utils.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /*File users = new File("usuarios.dat");

        ObjectOutputStream oos1 = abrirOOS(users);
        ObjOutStream ost1 = abrirOST(users);

        guardarUsuario(users, oos1, ost1, new CUsuario("root", "root"));

        cerrarOOS(oos1);
        cerrarOST(ost1);

        File personas = new File("personas.dat");

        ObjectOutputStream oos2 = abrirOOS(personas);
        ObjOutStream ost2 = abrirOST(personas);

        guardarPersona(personas, oos2, ost2, new CPersona("Administrador", "root", "root", "root"));

        cerrarOOS(oos1);
        cerrarOST(ost1);

         */
        Flow.AppFlow.Flow();

    }
}
