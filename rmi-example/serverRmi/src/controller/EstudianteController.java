package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EstudianteServiceImpl;
import model.IObservable;
import structure.Estudiante;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class EstudianteController implements Initializable, IObservable {

    private EstudianteServiceImpl estudianteService;
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TableView<Estudiante> tvEstudiantes;

    @FXML
    private TableColumn<Estudiante, String> tcCodigo;

    @FXML
    private TableColumn<Estudiante, String> tcNombre;
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            estudianteService = EstudianteServiceImpl.getInstance();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setUpTable();
        loadEstudiantes();
        estudianteService.addListener(this);
    }
    @FXML
    void onClickBtnGuardar(ActionEvent event) throws RemoteException {
        Estudiante estudiante = new Estudiante(txtCodigo.getText(),txtNombre.getText());
        estudianteService.guardar(estudiante);
    }
    private void setUpTable() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    }
    private void loadEstudiantes() {
        estudiantes.clear();
        estudiantes.addAll(estudianteService.getEstudiantes());
        tvEstudiantes.setItems(estudiantes);
    }
    @Override
    public void onChange() {
        loadEstudiantes();
    }
}
