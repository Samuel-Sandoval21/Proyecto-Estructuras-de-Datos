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

    private List<Tarea> listaTareas = new ArrayList<>();

    public void agregarTarea(Tarea t) {
        listaTareas.add(t);
    }

    public boolean eliminarTarea(String nombre) {
        for (Tarea t : listaTareas) {
            if (t.getTarea().equalsIgnoreCase(nombre)) {
                listaTareas.remove(t);
                return true;
            }
        }
        return false;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

}
