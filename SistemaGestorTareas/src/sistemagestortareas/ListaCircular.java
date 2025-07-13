/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

/**
 *
 * @author HP
 */
public class ListaCircular {

    private Nodo ultimo;

    public void agregar(Tarea tarea) {
        Nodo nuevo = new Nodo(tarea);
        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.setSiguiente(ultimo);
        } else {
            nuevo.setSiguiente(ultimo.getSiguiente());
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
    }

    public void mostrar() {
        if (ultimo == null) {
            return;
        }
        Nodo actual = ultimo.getSiguiente();
        do {
            System.out.println("- " + actual.getTarea().getTarea());
            actual = actual.getSiguiente();
        } while (actual != ultimo.getSiguiente());
    }

}
