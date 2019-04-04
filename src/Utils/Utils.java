package Utils;

import Classes.CCuenta;
import Classes.CPersona;
import Classes.CUsuario;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class Utils {

    public static String leerString() {
        try {
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Entrada no obtenida. Se devolverá String vacío");
            return "";
        }

    }

    public static float leerFloat() {
        try {
            return Float.parseFloat(leerString());
        } catch (NumberFormatException e) {
            System.err.println("Entrada no obtenida. Se devolverá Float vacío");
            return 0.0f;
        }
    }

    public static int leerInt() {
        try {
            return Integer.parseInt(leerString());
        } catch (NumberFormatException e) {
            System.err.println("Entrada no obtenida. Se devolverá int vacío.");
            return 0;
        }
    }

    public static double leerDouble() {
        try {
            return Double.parseDouble(leerString());
        } catch (NumberFormatException e) {
            System.err.println("Entrada no obtenida. Se devolverá double vacío.");
            return 0;
        }
    }

    public static File newFile() {
        File f;
        System.out.println("Nombre del archivo (el archivo se creará en el directorio raíz de la aplicación)");
        String s = leerString();
        try {
            f = new File(s + ".dat");
            if (!f.exists()) {
                f.createNewFile();
                return f;
            } else {
                return f;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
//Manipulación básica de Archivos

    public static ObjectOutputStream abrirOOS(File f) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(out);

        return oos;
    }

    public static ObjOutStream abrirOST(File f) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(f, true);
        ObjOutStream ost = new ObjOutStream(out);

        return ost;
    }

    public static ObjectInputStream abrirOIS(File f) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(in);
        return ois;

    }

    public static void cerrarOOS(ObjectOutputStream oos) throws IOException {
        oos.close();
    }

    public static void cerrarOST(ObjOutStream ost) throws IOException {
        ost.close();

    }

    public static void cerrarOIS(ObjectInputStream ois) throws IOException {
        ois.close();
    }
//Fin de manipulación básica de Archivos

//Escritura de Usuario nuevo
    public static void guardarUsuario(File f, ObjectOutputStream out, ObjOutStream ost, CUsuario u) throws IOException {

        if (f.exists()) {

            ost.writeUnshared(u);

        } else {

            out.writeObject(u);
        }

    }
//Lectura del fichero usuarios.dat

    public static ArrayList<CUsuario> leerUsuarios(File f, ObjectInputStream ois) throws IOException {

        ArrayList<CUsuario> listaUsuarios = new ArrayList<>();

        while (true) {
            try {
                CUsuario u = (CUsuario) ois.readObject();
                listaUsuarios.add(u);
            } catch (Exception e) {
                System.out.println("Archivo leído");
                ois.close();
                break;
            }
        }

        return listaUsuarios;

    }
//Escritura de Cuenta nueva

    public static void guardarCuenta(File f, ObjectOutputStream out, ObjOutStream ost, CCuenta u) throws IOException {

        if (f.exists()) {

            ost.writeUnshared(u);

        } else {

            out.writeObject(u);
        }

    }
//Lectura del fichero cuentas.dat

    public static ArrayList<CCuenta> leerCuentas(File f, ObjectInputStream ois) throws IOException {

        ArrayList<CCuenta> listaCuentas = new ArrayList<>();

        while (true) {
            try {
                CCuenta u = (CCuenta) ois.readObject();
                listaCuentas.add(u);
            } catch (Exception e) {
                System.out.println("Archivo leído");
                ois.close();
                break;
            }
        }

        return listaCuentas;

    }

    //Escritura de Cuenta nueva
    public static void guardarPersona(File f, ObjectOutputStream out, ObjOutStream ost, CPersona u) throws IOException {

        if (f.exists()) {

            ost.writeUnshared(u);

        } else {

            out.writeObject(u);
        }

    }
//Lectura del fichero cuentas.dat

    public static ArrayList<CPersona> leerPersonas(File f, ObjectInputStream ois) throws IOException {

        ArrayList<CPersona> listaPersonas = new ArrayList<>();

        while (true) {
            try {
                CPersona u = (CPersona) ois.readObject();
                listaPersonas.add(u);
            } catch (Exception e) {
                System.out.println("Archivo leído");
                ois.close();
                break;
            }
        }

        return listaPersonas;

    }

    /*
    public static void guardar(File f, Object obj) throws FileNotFoundException, IOException {
        //ESTE MÉTODO NO ES PARA USARSE, SINO PARA COPIARSE CUANDO SE DEBA GUARDAR
        //UNA LISTA DE OBJETOS DE UN ARCHIVO.DAT
        if (!f.exists()) {
            FileOutputStream stream = new FileOutputStream(f);
            ObjectOutputStream output = null;
            try {
                output = new ObjectOutputStream(stream);
                output.writeObject(obj);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }
        } else {
            FileOutputStream stream = new FileOutputStream(f);
            ObjOutStream output = null;
            try {
                output = new ObjOutStream(stream);
                output.writeUnshared(obj);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }
        }

    }

    public static ArrayList<Object> abrirLeer(File f) throws FileNotFoundException, IOException {
        //ESTE MÉTODO NO ES PARA USARSE, SINO PARA COPIARSE CUANDO SE DEBA OBTENER
        //UNA LISTA DE OBJETOS DE UN ARCHIVO.DAT

        ArrayList<Object> listas = new ArrayList<>();

        FileInputStream stream = new FileInputStream(f);
        ObjectInputStream input = null;
        try {

            input = new ObjectInputStream(stream);

            Object aux = input.readObject();

            while (aux != null) {
                if (aux instanceof Object) {
                    listas.add(aux);
                }
                aux = input.readObject();
            }
            input.close();
        } catch (EOFException e) {
            System.out.println("Fin de fichero");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } finally {
            input.close();
            return listas;
        }
    }
     */
}
