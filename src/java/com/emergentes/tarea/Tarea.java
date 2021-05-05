/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.tarea;

public class Tarea {
    private int id;
    private String tarea;
    private int prioridad;
    private int completado;
    public Tarea() {
        this.id = 0;
        this.tarea = "";
        this.prioridad = 0;
        this.completado = 0;
    }
    public int getId() {
        return id;
    }
    public String getTarea() {
        return tarea;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public int getCompletado() {
        return completado;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTarea(String tarea) {
        this.tarea = tarea;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    public void setCompletado(int completado) {
        this.completado = completado;
    }
}