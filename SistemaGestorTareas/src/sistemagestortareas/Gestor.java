/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Gestor {

    private ListaSimple tareas = new ListaSimple();
    private ListaCircular tareasCirculares = new ListaCircular();
    private PilaTareas historial = new PilaTareas();

    public void agregarTarea(Tarea tarea) {
        tareas.agregar(tarea);
        tareasCirculares.agregar(tarea);
        historial.registrar(tarea);
    }

    public Nodo getListaTareas() {
        return tareas.getInicio();
    }

    public boolean eliminarTarea(String nombre) {
        return tareas.eliminar(nombre);
    }

    public void mostrarCircular() {
        tareasCirculares.mostrar();
    }

    public void mostrarHistorial() {
        historial.mostrarHistorial();
    }

}
