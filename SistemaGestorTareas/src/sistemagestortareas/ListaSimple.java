/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

/**
 *
 * @author HP
 */
public class ListaSimple {

    private Nodo inicio;

    public void agregar(Tarea tarea) {
        Nodo nuevo = new Nodo(tarea);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public Nodo getInicio() {
        return inicio;
    }

    public boolean eliminar(String tarea) {
        if (inicio == null) {
            return false;
        }
        if (inicio.getTarea().getTarea().equalsIgnoreCase(tarea)) {
            inicio = inicio.getSiguiente();
            return true;
        }
        Nodo actual = inicio;
        while (actual.getSiguiente() != null
                && !actual.getSiguiente().getTarea().getTarea().equalsIgnoreCase(tarea)) {
            actual = actual.getSiguiente();

        }
        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return true;
        }
        return false;

    }
}
