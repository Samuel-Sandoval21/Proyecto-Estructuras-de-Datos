/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author HP
 */
public class Interfaz {

    private Gestor gestor = new Gestor();

    public void mostrarMenu() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(null,
                    "1. Agregar tarea\n "
                    + "2. Mostrar tareas\n"
                    + "3. Tareas completadas\n"
                    + "4. Buscar tareas\n"
                    + "5. Marcar tarea como finalizada\n"
                    + "6. Eliminar tarea\n"
                    + "7. Salir",
                    "Sistema Gestor de Tareas", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) {
                break;
            }

            switch (opcion) {
                case "1" ->
                    leerEntradaUsuario();
                case "2" ->
                    mostrarTareas();
                case "3" ->
                    listaTareasCompletadas();
                case "4" ->
                    buscarTareas();
                case "5" ->
                    marcarTarea();
                case "6" ->
                    eliminarTarea();
                case "7" ->
                    JOptionPane.showMessageDialog(null, "¡Hasta pronto!");
                default ->
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
            }
        } while (!"6".equals(opcion));
    }

    public void leerEntradaUsuario() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea:");
        String descripcion = JOptionPane.showInputDialog("Descripcion:");
        int importancia = Integer.parseInt(JOptionPane.showInputDialog("Importancia (1-5):"));

        Tarea tarea = new Tarea(nombre, descripcion, importancia);
        gestor.agregarTarea(tarea);
    }

    public void mostrarTareas() {
        StringBuilder sb = new StringBuilder("Tareas: \n");
        for (Tarea t : gestor.getListaTareas()) {
            sb.append("- ").append(t.getTarea())
                    .append(" [").append(t.TareaFinalizada() ? "Finalizada" : "Pendiente").append("]\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void listaTareasCompletadas() {
        StringBuilder sb = new StringBuilder("Tareas finalizadas: \n");
        for (Tarea t : gestor.getListaTareas()) {
            if (t.TareaFinalizada()) {
                sb.append("- ").append(t.getTarea()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void buscarTareas() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea a buscar:");
        for (Tarea t : gestor.getListaTareas()) {
            if (t.getTarea().equalsIgnoreCase(nombre)) {
                String info = "Descripcion: " + t.getDescripcion() + "\n"
                        + "Importancia: " + t.getImportancia() + "\n"
                        + "Estado: " + (t.TareaFinalizada() ? "Finalizada" : "Pendiente");
                JOptionPane.showMessageDialog(null, info);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
    }

    public void marcarTarea() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea a marcar como finalizada");
        for (Tarea t : gestor.getListaTareas()) {
            if (t.getTarea().equalsIgnoreCase(nombre)) {
                t.marcarFinalizada();
                JOptionPane.showMessageDialog(null, "Tarea marcada como finalizada.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
    }

    public void eliminarTarea() {
        String nombre = JOptionPane.showInputDialog("Nombre de la tarea a eliminar:");
        if (nombre == null || nombre.isEmpty()) {
            return;
        }

        boolean eliminada = gestor.eliminarTarea(nombre);
        if (eliminada) {
            JOptionPane.showMessageDialog(null, "Tarea eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
        }
    }

}
