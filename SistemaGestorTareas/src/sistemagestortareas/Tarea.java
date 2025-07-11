/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestortareas;

/**
 *
 * @author HP
 */
public class Tarea {

    private String tarea;
    private String descripcion;
    private int importancia;
    private boolean finalizada;

    public Tarea(String tarea, String descripcion, int importancia) {
        this.tarea = tarea;
        this.descripcion = descripcion;
        this.importancia = importancia;
        this.finalizada = false;
    }

    public String getTarea() {
        return tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImportancia() {
        return importancia;
    }

    public boolean TareaFinalizada() {
        return finalizada;
    }

    public void marcarFinalizada() {
        this.finalizada = true;
    }

}
