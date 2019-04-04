package Flow;

import Classes.CCuenta;
import Classes.CPersona;
import Classes.CUsuario;
import Utils.ObjOutStream;
import Utils.Utils;
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
public class AppFlow {

    public static void Flow() throws IOException {
//Declaración de variables, estructuras dinámicas, etc.
        //ArrayList con la lista de usuarios cargada del fichero fUsuarios.
        File fUsuarios = new File("usuarios.dat");
        ArrayList<CUsuario> listaUsuarios;
        if (fUsuarios.exists()) {
            ObjectInputStream oisUsuarios = abrirOIS(fUsuarios);
            listaUsuarios = leerUsuarios(fUsuarios, oisUsuarios);
            cerrarOIS(oisUsuarios);
        } else {
            System.out.println("Creando archivo usuarios...");
            fUsuarios.createNewFile();
            listaUsuarios = new ArrayList<>();
        }

        //Objeto usuario que va a comparase con lo que introduzca el usuario y DNI obtenido
        String dniUser = "";

        //Cuentas y lista de cuentas
        File fCuentas = new File("cuentas.dat");
        ArrayList<CCuenta> listaCuentas;
        if (fCuentas.exists()) {
            ObjectInputStream oisCuentas = abrirOIS(fCuentas);
            listaCuentas = leerCuentas(fCuentas, oisCuentas);
            cerrarOIS(oisCuentas);
        } else {
            System.out.println("Creando archivo cuentas...");
            fCuentas.createNewFile();
            listaCuentas = new ArrayList<>();
        }
        //Personas (cliente y empleado de prueba)
        File fPersonas = new File("personas.dat");
        ArrayList<CPersona> listaPersonas;
        if (fPersonas.exists()) {
            ObjectInputStream oisPersonas = abrirOIS(fPersonas);
            listaPersonas = leerPersonas(fPersonas, oisPersonas);
            cerrarOIS(oisPersonas);
        } else {
            System.out.println("Creando archivo personas...");
            fPersonas.createNewFile();
            listaPersonas = new ArrayList<>();
        }

        //Órdenes que recibirá el programa
        String orden = "";

        //String que almacena el tipo de usuario ingresado y CPersona que alamacena los datos con los que trabajaremos
        CPersona persona = new CPersona();
        String tipoPersona = "";

        //Comienzo del funcionamiento del programa
        System.out.println("Inserte un usuario");
        String user = Utils.leerString();
        System.out.println("Inserte una contraseña");
        String pass = Utils.leerString();

        for (int i = 0; i < listaUsuarios.size(); i++) {

            CUsuario u = listaUsuarios.get(i);

            if (u.getDni().equals(user) && u.getPass().equals(pass)) {
                dniUser = user;
                System.out.println("Usuario correcto, buscando cuentas coincidentes...");
                i = listaUsuarios.size();
            }

        }

        for (int i = 0; i < listaPersonas.size(); i++) {
            CPersona p = listaPersonas.get(i);
            if (p.getDni().equals(dniUser)) {

                persona.setNombre(p.getNombre());
                persona.setApellidos(p.getApellidos());
                persona.setDni(p.getDni());
                persona.setTipo(p.getTipo());

                tipoPersona = p.getTipo();

                System.out.println("Cuenta cargada. Bienvenido, " + persona.getNombre() + ".\n");

                i = listaPersonas.size();
            }
        }

        if (tipoPersona.equals("cliente")) {
//MENÚ DEL CLIENTE
            while (!orden.toLowerCase().equals("fin")) {

                System.out.println(persona.toString());

                orden = Utils.leerString();

                switch (orden) {

                    case ("1"): {
                        mostrarCuentasCliente(persona, listaCuentas);
                        break;
                    }

                    case ("fin"): {
                        System.exit(0);
                        break;
                    }

                    default: {
                        System.out.println("No se reconoce la órden. Inténtelo de nuevo.");
                        break;
                    }
                }
            }
        } else if (tipoPersona.equals("empleado")) {
//MENÚ DEL EMPLEADO
            while (!orden.toLowerCase().equals("fin")) {

                System.out.println(persona.toString());

                orden = Utils.leerString();

                switch (orden) {

                    case ("1"): {
                        mostrarCuentasEmpleado(listaCuentas);
                        break;
                    }

                    case ("2"): {
                        ingresar(listaCuentas);
                        break;
                    }

                    case ("3"): {
                        retirar(listaCuentas);
                        break;
                    }

                    case ("fin"): {
                        System.out.println("Guardando cambios...");
                        /*//Se guardan posibles cambios en usuarios.dat
                        ObjectOutputStream oosUsuarios = abrirOOS(fUsuarios);
                        ObjOutStream ostUsuarios = abrirOST(fUsuarios);
                        for (CUsuario u : listaUsuarios) {
                            guardarUsuario(fUsuarios, oosUsuarios, ostUsuarios, u);
                        }
                        cerrarOOS(oosUsuarios);
                        cerrarOST(ostUsuarios);
                         */
                        //Se guardan posibles cambios en cuentas.dat
                        ObjectOutputStream oosCuentas = abrirOOS(fCuentas);
                        ObjOutStream ostCuentas = abrirOST(fCuentas);
                        for (CCuenta c : listaCuentas) {
                            guardarCuenta(fCuentas, oosCuentas, ostCuentas, c);
                        }
                        cerrarOOS(oosCuentas);
                        cerrarOST(ostCuentas);
                        /*
                        //Se guardan posibles cambios en personas.dat
                        ObjectOutputStream oosPersonas = abrirOOS(fPersonas);
                        ObjOutStream ostPersonas = abrirOST(fPersonas);
                        for (CPersona p : listaPersonas) {
                            guardarPersona(fPersonas, oosPersonas, ostPersonas, p);
                        }
                        cerrarOOS(oosPersonas);
                        cerrarOST(ostPersonas);
                         */
                        System.exit(0);
                        break;
                    }

                    default: {
                        System.out.println("No se reconoce la órden. Inténtelo de nuevo.");
                        break;
                    }
                }

            }
        } else if (tipoPersona.equals("root")) {
//MENÚ DEL ADMINISTRADOR
            while (!orden.toLowerCase().equals("fin")) {

                System.out.println("\n" + persona.getNombre() + "\n");

                orden = Utils.leerString();

                switch (orden) {

                    case ("1"): {
                        //Mostrar todos los usuarios
                        mostrarUsuarios(listaUsuarios);
                        break;
                    }

                    case ("2"): {
                        //Mostrar todas las personas
                        mostrarPersonas(listaPersonas);
                        break;
                    }

                    case ("3"): {
                        //Mostrar todas las cuentas
                        mostrarCuentasEmpleado(listaCuentas);
                        break;
                    }
                    case ("4"): {
                        //Añadir usuario + persona
                        listaUsuarios.add(newUser());
                        listaPersonas.add(newPersona());
                        System.out.println("Usuario creado.");
                        break;
                    }
                    case ("5"): {
                        //Añadir cuenta
                        listaCuentas.add(newCuenta(listaPersonas));
                        System.out.println("Cuenta creada.");
                        break;
                    }
                    case ("6"): {
                        //Modificar usuario + persona
                        System.out.println("Indique el DNI de la persona a modificar");
                        String busq = leerString();
                        CUsuario c = new CUsuario();
                        CPersona p = new CPersona();
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            c = listaUsuarios.get(i);
                            if (busq.equals(c.getDni())) {
                                listaUsuarios.set(i, newUser());
                            }
                        }
                        for (int i = 0; i < listaPersonas.size(); i++) {
                            p = listaPersonas.get(i);
                            if (busq.equals(p.getDni())) {
                                listaPersonas.set(i, newPersona());
                            }
                        }
                        System.out.println("Usuario modificado.");
                        break;
                    }
                    case ("7"): {
                        //Modificar cuenta
                        CCuenta c = new CCuenta();
                        System.out.println("Indique el número de la cuenta a modificar");
                        String num = leerString();
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            c = listaCuentas.get(i);
                            if (num.equals(c.getNumCuenta())) {
                                listaCuentas.set(i, newCuenta(listaPersonas));
                            }
                        }
                        System.out.println("Cuenta modificada.");
                        break;
                    }
                    case ("8"): {
                        //Eliminar usuario + persona (+ posibles cuentas que tuviese) <- A ver cómo lo hacemos
                        System.out.println("Indique el DNI del usuario a borrar");
                        String dni = leerString();
                        CUsuario u = new CUsuario();
                        CPersona p = new CPersona();
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            u = listaUsuarios.get(i);
                            if (dni.equals(u.getDni())) {
                                listaUsuarios.remove(i);
                            }
                        }

                        for (int i = 0; i < listaPersonas.size(); i++) {
                            p = listaPersonas.get(i);
                            if (dni.equals(p.getDni())) {
                                listaPersonas.remove(i);
                            }
                        }
                        System.out.println("Usuario borrado.");
                        break;
                    }
                    case ("9"): {
                        //Eliminar cuenta
                        System.out.println("Indique el número de la cuenta a borrar");
                        String num = leerString();
                        CCuenta c = new CCuenta();
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            c = listaCuentas.get(i);
                            if (num.equals(c.getNumCuenta())) {
                                listaCuentas.remove(i);
                            }
                        }
                        System.out.println("Cuenta borrada.");
                        break;
                    }

                    case ("fin"): {
                        System.out.println("Guardando cambios...");
                        //Se guardan posibles cambios en usuarios.dat
                        ObjectOutputStream oosUsuarios = abrirOOS(fUsuarios);
                        ObjOutStream ostUsuarios = abrirOST(fUsuarios);
                        CUsuario usuario = new CUsuario();
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            usuario = listaUsuarios.get(i);
                            guardarUsuario(fUsuarios, oosUsuarios, ostUsuarios, usuario);
                        }
                        cerrarOOS(oosUsuarios);
                        cerrarOST(ostUsuarios);
                        //Se guardan posibles cambios en cuentas.dat
                        ObjectOutputStream oosCuentas = abrirOOS(fCuentas);
                        ObjOutStream ostCuentas = abrirOST(fCuentas);
                        CCuenta c = new CCuenta();
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            c = listaCuentas.get(i);
                            guardarCuenta(fCuentas, oosCuentas, ostCuentas, c);
                        }
                        cerrarOOS(oosCuentas);
                        cerrarOST(ostCuentas);

                        //Se guardan posibles cambios en personas.dat
                        ObjectOutputStream oosPersonas = abrirOOS(fPersonas);
                        ObjOutStream ostPersonas = abrirOST(fPersonas);
                        CPersona p = new CPersona();
                        for (int i = 0; i < listaPersonas.size(); i++) {
                            p = listaPersonas.get(i);
                            guardarPersona(fPersonas, oosPersonas, ostPersonas, p);
                        }
                        cerrarOOS(oosPersonas);
                        cerrarOST(ostPersonas);

                        System.exit(0);
                        break;
                    }

                    default: {
                        System.out.println("No se reconoce la órden. Inténtelo de nuevo.");
                        break;
                    }
                }

            }
        }

    }

    public static void mostrarCuentasCliente(CPersona persona, ArrayList<CCuenta> listaCuentas) {

        for (int i = 0; i < listaCuentas.size(); i++) {

            CCuenta cuenta = listaCuentas.get(i);

            if (cuenta.getDni().equals(persona.getDni())) {

                System.out.println(listaCuentas.get(i).toString() + "\n");

            }

        }
    }

    public static void mostrarCuentasEmpleado(ArrayList<CCuenta> listaCuentas) {

        for (int i = 0; i < listaCuentas.size(); i++) {

            System.out.println(listaCuentas.get(i).toStringEmpleado() + "\n");

        }
    }

    public static void mostrarUsuarios(ArrayList<CUsuario> listaUsuarios) {

        for (CUsuario u : listaUsuarios) {
            System.out.println(u.toString());
        }

    }

    public static void mostrarPersonas(ArrayList<CPersona> listaPersonas) {

        for (CPersona p : listaPersonas) {
            System.out.println(p.toString());
        }

    }

    public static CUsuario newUser() {
        CUsuario u = new CUsuario();

        System.out.println("Indique el nombre de usuario (DNI)");
        u.setDni(leerString());
        System.out.println("Indique la contraseña de usuario");
        u.setPass(leerString());

        return u;
    }

    public static CPersona newPersona() {

        CPersona p = new CPersona();

        System.out.println("Indique el nombre de la persona");
        p.setNombre(leerString());

        System.out.println("Indique los apellidos de la persona");
        p.setApellidos(leerString());

        System.out.println("Indique el DNI de la persona");
        p.setDni(leerString());

        System.out.println("Indique el tipo de acceso de la persona (empleado o cliente)");
        String tipoCliente = leerString();
        if (tipoCliente.toLowerCase().equals("empleado")) {
            p.setTipo("empleado");
        } else if (tipoCliente.toLowerCase().equals("cliente")) {
            p.setTipo("cliente");
        } else {
            System.out.println("No se reconoce el tipo, se creará por defecto como cliente.");
            p.setTipo("cliente");
        }
        return p;
    }

    public static CCuenta newCuenta(ArrayList<CPersona> listaPersonas) {
//EL DNI SE DEVUELVE SIEMPRE COMO NULL, HAY QUE MIRARLO
        CCuenta c = new CCuenta();

        System.out.println("Indique el número de cuenta (XXXX-XXXX)");
        c.setNumCuenta(leerString());
        System.out.println("Indique la sucursal (Ciudad)");
        c.setSucursal(leerString());
        System.out.println("Indique el DNI del propietario");
        String dni = leerString();
        CPersona p = new CPersona();
        for (int i = 0; i < listaPersonas.size(); i++) {
            p = listaPersonas.get(i);
            if (dni.equals(p.getDni())) {
                c.setDni(p.getDni());
            }
        }
        System.out.println("Indique el saldo de la cuenta");
        c.setSaldo(leerFloat());

        return c;
    }

    public static void ingresar(ArrayList<CCuenta> listaCuentas) {
        double cantidad = 0;
        String numCuenta = "";
        System.out.println("Indique el nº de cuenta para realizar el ingreso");
        numCuenta = Utils.leerString();

        for (int i = 0; i < listaCuentas.size(); i++) {
            CCuenta c = listaCuentas.get(i);

            if (numCuenta.equals(c.getNumCuenta())) {
                System.out.println("Cuenta encontrada. Indique el importe del ingreso");
                cantidad = Utils.leerDouble();
                c.setSaldo(c.getSaldo() + cantidad);
                System.out.println("Operación realizada. El saldo actual es de " + c.getSaldo() + "€");
                i = listaCuentas.size();
            }
        }

    }

    public static void retirar(ArrayList<CCuenta> listaCuentas) {
        double cantidad = 0;
        String numCuenta = "";
        System.out.println("Indique el nº de cuenta para realizar el ingreso");
        numCuenta = Utils.leerString();

        for (int i = 0; i < listaCuentas.size(); i++) {
            CCuenta c = listaCuentas.get(i);

            if (numCuenta.equals(c.getNumCuenta())) {
                System.out.println("Cuenta encontrada. Indique el importe a retirar");
                cantidad = Utils.leerDouble();
                c.setSaldo(c.getSaldo() - cantidad);
                System.out.println("Operación realizada. El saldo actual es de " + c.getSaldo() + "€");
                i = listaCuentas.size();
            }
        }

    }

}
