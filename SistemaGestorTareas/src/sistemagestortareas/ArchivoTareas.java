/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ArchivoTareas {

    // gestionar una base de datos eficiente para gestionar la información 
    public static void guardar(String usuario, ListaSimple lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usuario + "_tareas.txt"))) {
            Nodo actual = lista.getInicio();
            while (actual != null) {
                Tarea t = actual.getTarea();
                String linea = t.getTarea() + ";" + t.getDescripcion() + ";" + t.getImportancia() + ";" + t.TareaFinalizada();
                writer.write(linea);
                writer.newLine();
                actual = actual.getSiguiente();
            }

        } catch (IOException e) {
            System.err.println("Error al guardar tareas: " + e.getMessage());
        }

    }

    public static ListaSimple cargar(String usuario) {
        ListaSimple lista = new ListaSimple();
        File archivo = new File(usuario + "_tareas.txt");
        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                Tarea tarea = new Tarea(partes[0], partes[1], Integer.parseInt(partes[2]));
                if (Boolean.parseBoolean(partes[3])) {
                    tarea.marcarFinalizada();
                }
                lista.agregar(tarea);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar tareas: " + e.getMessage());
        }

        return lista;
    }
}
