/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import java.util.HashMap;

/**
 *
 * @author HP
 */
public class UsuarioGestor {

    // sistema de autenticación y gestión de usuarios 
    private HashMap<String, ListaSimple> tareasPorUsuario = new HashMap();

    public void agregarTarea(String usuario, Tarea tarea) {
        tareasPorUsuario.putIfAbsent(usuario, new ListaSimple());
        tareasPorUsuario.get(usuario).agregar(tarea);
    }

    public ListaSimple obtenerTareas(String usuario) {
        return tareasPorUsuario.getOrDefault(usuario, new ListaSimple());
    }

    public boolean eliminarTarea(String usuario, String nombreTarea) {
        ListaSimple lista = tareasPorUsuario.get(usuario);
        if (lista != null) {
            return lista.eliminar(nombreTarea);
        }
        return false;
    }
}
