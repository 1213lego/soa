/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.IEstudianteService;
import model.IObservable;
import structure.Estudiante;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class ClientRmi {
    public static final String SERVER_IP= "127.0.0.1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        // TODO code application logic here
        IEstudianteService estudianteService = (IEstudianteService) Naming.lookup("//"+SERVER_IP+"/estudiante");
        IObservable  iObservable = new IObservable() {
            @Override
            public void onChange() {
                try {
                    estudianteService.getEstudiantes().forEach((estudiante -> System.out.println(estudiante)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        estudianteService.addListener(iObservable);
        Scanner sc = new Scanner(System.in);
        int input =-1;
        while (input!=0){
            System.out.println("Opciones");
            System.out.println("1 para guardar un estudiante");
            System.out.println("2 para Listar Estudiantes");
            input = sc.nextInt();
            switch (input){
                case 1: {
                    Estudiante estudiante = new Estudiante();
                    System.out.println("Ingrese el codigo");
                    estudiante.setCodigo(sc.next());
                    System.out.println("Ingrese el nombre");
                    estudiante.setNombre(sc.next());
                    System.out.println(estudianteService.guardar(estudiante));
                    break;
                }
                case 2: {
                    estudianteService.getEstudiantes().forEach((estudiante -> System.out.println(estudiante)));
                    break;
                }
                default: input=0;
            }
        }
    }
}
