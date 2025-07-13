/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import javax.swing.*;
import java.util.List;
import sistemagestortareas.Gestor;
import sistemagestortareas.Nodo;
import sistemagestortareas.Tarea;

/**
 *
 * @author HP
 */
public class Interfaz {

    private String usuarioActivo;
    private UsuarioGestor usuarioGestor = new UsuarioGestor();

    public void iniciarSesion() {
        Usuario usuario = new Usuario("Samuel", "2101");

        String nombre = JOptionPane.showInputDialog("Usuario:");
        String password = JOptionPane.showInputDialog("Contraseña:");

        if (usuario.getNombre().equals(nombre) && usuario.verificarContraseña(password)) {
            usuarioActivo = nombre;

            ListaSimple cargadas = ArchivoTareas.cargar(usuarioActivo);
            for (Nodo actual = cargadas.getInicio(); actual != null; actual = actual.getSiguiente()) {
                usuarioGestor.agregarTarea(usuarioActivo, actual.getTarea());
            }
            JOptionPane.showMessageDialog(null, "¡Bienvenido " + nombre + "!");
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Acceso denegado.");
        }

    }

    public void mostrarMenu() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(null,
                    "Usuario: " + usuarioActivo + "\n\n"
                    + "1. Agregar tarea\n "
                    + "2. Mostrar tareas\n"
                    + "3. Tareas completadas\n"
                    + "4. Buscar tareas\n"
                    + "5. Marcar tarea como finalizada\n"
                    + "6. Eliminar tarea\n"
                    + "7. Mostrar tareas recursivamente\n"
                    + "8. Mostrar tareas circulares\n"
                    + "9. Mostrar historial (pila)\n"
                    + "10. Salir", "Sistema Gestor de Tareas", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) {
                break;
            }

            switch (opcion) {
                case "1" ->
                    leerEntradaUsuario();
                case "2" ->
                    mostrarTareasIterativas();
                case "3" ->
                    listaTareasCompletadas();
                case "4" -> {
                    String nombre = JOptionPane.showInputDialog("Nombre de la tarea a buscar:");
                    buscarTareaRecursiva(usuarioGestor.obtenerTareas(usuarioActivo).getInicio(), nombre);

                }

                case "5" ->
                    marcarTarea();
                case "6" ->
                    eliminarTarea();
                case "7" ->
                    mostrarRecursivo(usuarioGestor.obtenerTareas(usuarioActivo).getInicio());
                case "8" ->
                    System.out.println("Circular aún no integrada por usuario");
                case "9" ->
                    System.out.println("Historial aún no integrado por usuario");
                case "10" ->
                    JOptionPane.showMessageDialog(null, "¡Hasta pronto!");
                default ->
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (!"10".equals(opcion));
    }

    public void leerEntradaUsuario() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea:");
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        int importancia = Integer.parseInt(JOptionPane.showInputDialog("Importancia (1-5):"));

        Tarea tarea = new Tarea(nombre, descripcion, importancia);
        usuarioGestor.agregarTarea(usuarioActivo, tarea);
        ArchivoTareas.guardar(usuarioActivo, usuarioGestor.obtenerTareas(usuarioActivo));
    }

    public void mostrarTareasIterativas() {
        StringBuilder sb = new StringBuilder("Tareas:\n");
        Nodo actual = usuarioGestor.obtenerTareas(usuarioActivo).getInicio();
        while (actual != null) {
            Tarea t = actual.getTarea();
            sb.append("- ").append(t.getTarea())
                    .append(" [").append(t.TareaFinalizada() ? "Finalizada" : "Pendiente").append("]\n");
            actual = actual.getSiguiente();
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void listaTareasCompletadas() {
        StringBuilder sb = new StringBuilder("Tareas finalizadas:\n");
        Nodo actual = usuarioGestor.obtenerTareas(usuarioActivo).getInicio();
        while (actual != null) {
            Tarea t = actual.getTarea();
            if (t.TareaFinalizada()) {
                sb.append("- ").append(t.getTarea()).append("\n");
            }
            actual = actual.getSiguiente();
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void mostrarRecursivo(Nodo actual) {
        if (actual == null) {
            return;
        }
        Tarea t = actual.getTarea();
        System.out.println("- " + t.getTarea() + " [" + (t.TareaFinalizada() ? "Finalizada" : "Pendiente") + "]");
        mostrarRecursivo(actual.getSiguiente());
    }

    public void buscarTareaRecursiva(Nodo actual, String nombreBuscado) {
        if (actual == null) {
            JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
            return;
        }
        Tarea t = actual.getTarea();
        if (t.getTarea().equalsIgnoreCase(nombreBuscado)) {
            String info = "Descripción: " + t.getDescripcion()
                    + "\nImportancia: " + t.getImportancia()
                    + "\nEstado: " + (t.TareaFinalizada() ? "Finalizada" : "Pendiente");
            JOptionPane.showMessageDialog(null, info);
            return;
        }
        buscarTareaRecursiva(actual.getSiguiente(), nombreBuscado);

    }

    public void marcarTarea() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea a marcar como finalizada:");
        Nodo actual = usuarioGestor.obtenerTareas(usuarioActivo).getInicio();
        while (actual != null) {
            Tarea t = actual.getTarea();
            if (t.getTarea().equalsIgnoreCase(nombre)) {
                t.marcarFinalizada();
                JOptionPane.showMessageDialog(null, "Tarea marcada como finalizada.");
                ArchivoTareas.guardar(usuarioActivo, usuarioGestor.obtenerTareas(usuarioActivo));
                return;
            }
            actual = actual.getSiguiente();

        }
        JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
    }

    public void eliminarTarea() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea a eliminar:");
        if (nombre == null || nombre.isEmpty()) {
            return;
        }

        boolean eliminada = usuarioGestor.eliminarTarea(usuarioActivo, nombre);
        if (eliminada) {
            JOptionPane.showMessageDialog(null, "Tarea eliminada correctamente.");
            ArchivoTareas.guardar(usuarioActivo, usuarioGestor.obtenerTareas(usuarioActivo));
        } else {
            JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
        }
    }
}
