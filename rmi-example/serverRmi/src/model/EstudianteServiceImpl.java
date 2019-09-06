/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import structure.Estudiante;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class EstudianteServiceImpl extends UnicastRemoteObject implements IEstudianteService {
    private static EstudianteServiceImpl instance;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<IObservable> listeners;
    private EstudianteServiceImpl() throws RemoteException{
        estudiantes= new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static EstudianteServiceImpl getInstance() throws RemoteException {
        if(instance==null){
            instance = new EstudianteServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean guardar(Estudiante estudiante) throws RemoteException {
        System.out.println("Guardando "+estudiante);
        boolean result = estudiantes.add(estudiante);
        notifyListeners();
        return result;
    }

    @Override
    public Estudiante buscar(String codigo) throws RemoteException {
        for (Estudiante estudiante: estudiantes) {
            if(estudiante.getCodigo().equals(codigo)){
                return estudiante;
            }
        }
        return null;
    }

    public ArrayList<Estudiante> getEstudiantes(){
        return estudiantes;
    }
    public void addListener(IObservable iObservable)
    {
        System.out.println("add");
        listeners.add(iObservable);
    }
    public void removeListener(IObservable iObservable){
        listeners.remove(iObservable);
    }
    private void notifyListeners() throws RemoteException {
        for (IObservable iObservable: listeners) {
            iObservable.onChange();
        }
    }
}
