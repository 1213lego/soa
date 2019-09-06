/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import structure.Estudiante;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface IEstudianteService extends Remote{
    boolean guardar(Estudiante estudiante)throws RemoteException;
    Estudiante buscar(String codigo)throws RemoteException;
    ArrayList<Estudiante> getEstudiantes() throws RemoteException;
    void removeListener(IObservable iObservable) throws RemoteException;
    void addListener(IObservable iObservable) throws RemoteException;
}
