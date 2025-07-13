/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import java.util.Stack;

/**
 *
 * @author HP
 */
public class PilaTareas {

    private Stack<Tarea> pila = new Stack<>();

    public void registrar(Tarea tarea) {
        pila.push(tarea);
    }

    public void mostrarHistorial() {
        System.out.println("Historial de tareas:");
        for (Tarea tarea : pila) {
            System.out.println("- " + tarea.getTarea());
        }
    }

}
