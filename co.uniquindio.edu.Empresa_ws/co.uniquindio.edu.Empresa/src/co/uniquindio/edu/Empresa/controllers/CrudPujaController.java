package co.uniquindio.edu.Empresa.controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.uniquindio.edu.Empresa.application.Aplicacion;
import co.uniquindio.edu.Empresa.exceptions.PujaException;
import co.uniquindio.edu.Empresa.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CrudPujaController {
	
	/**
	 * atributos
	 */
	Singleton singleton = Singleton.getInstance();
	Aplicacion aplicacion;
	Empresa empresa = singleton.empresa;
	
	//Metodo set de aplicacion
	public void setAplicacion(Aplicacion aplicacion){
		this.aplicacion = aplicacion;
		singleton.setAplicacion(aplicacion);
	}
	
	//instancia de una lista para la tableview
	private ObservableList<Puja> vistaListaPujaCrud = FXCollections.observableArrayList();
	private ObservableList<Anuncio> vistaListaAnuncios = FXCollections.observableArrayList();
	private ObservableList<Usuario> vistaListaUsuarios = FXCollections.observableArrayList();


	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cboxAnuncioPuja"
    private ComboBox<Anuncio> cboxAnuncioVista; // Value injected by FXMLLoader

    @FXML // fx:id="cboxUsuarios"
    private ComboBox<Usuario> cboxUsuarios; // Value injected by FXMLLoader

    @FXML // fx:id="btnActualizarPuja"
    private Button btnActualizarPuja; // Value injected by FXMLLoader

    @FXML // fx:id="btnSalirVerPuja"
    private Button btnExportarPujas; // Value injected by FXMLLoader

    @FXML // fx:id="col_ValorApujar"
    private TableColumn<Puja, Double> col_ValorApujar; // Value injected by FXMLLoader

    @FXML // fx:id="col_Usuario"
    private TableColumn<Puja, String> col_Usuario; // Value injected by FXMLLoader

    @FXML // fx:id="col_IdPuja"
    private TableColumn<Puja, String> col_IdPuja; // Value injected by FXMLLoader

    @FXML // fx:id="tabVerPuja"
    private Tab tabVerPuja; // Value injected by FXMLLoader

    @FXML // fx:id="btnVolverAnuncioVista"
    private Button btnVolverAnuncioVista; // Value injected by FXMLLoader

    @FXML // fx:id="txtValorApujar"
    private TextField txtValorApujar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCrearPuja"
    private Button btnCrearPuja; // Value injected by FXMLLoader

    @FXML // fx:id="btnVolverAnuncioCrud"
    private Button btnVolverAnuncioCrud; // Value injected by FXMLLoader

    @FXML // fx:id="cboxAnuncios"
    private ComboBox<Anuncio> cboxAnuncioCrud; // Value injected by FXMLLoader

    @FXML // fx:id="tabCrearPuja"
    private Tab tabCrearPuja; // Value injected by FXMLLoader

    @FXML // fx:id="tabPanePP"
    private TabPane tabPanePP; // Value injected by FXMLLoader

    @FXML // fx:id="colAnuncioPuja"
    private TableColumn<Puja, String> colAnuncioPuja; // Value injected by FXMLLoader

    @FXML // fx:id="txtIdPuja"
    private TextField txtIdPuja; // Value injected by FXMLLoader

    @FXML // fx:id="btnEliminarPuja"
    private Button btnEliminarPuja; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Puja, String> col_IdPujaVista;
    
    @FXML
    private TableColumn<Puja, String> col_UsuarioVista;
    
    @FXML
    private TableColumn<Puja, Double> col_ValorApujarVista;
   
    @FXML
    private TableView<Puja> tblPujasVista;
    
    @FXML
    private Button btnBuscarListaPujas;
    
    @FXML
    private Button btnBuscarPuja;
    
    @FXML
    void CrearPuja(ActionEvent event) throws Exception {
    	String usser = (String) JOptionPane.showInputDialog(null, "Ingrese su Usuario","Verificacion de Usuario", 1, null, null, "");
		String contrasena = (String) JOptionPane.showInputDialog(null, "Ingrese su Contraseña","Verificacion de Usuario", 1, null, null, "");
    	
		if (singleton.verificarComprador(usser, contrasena)) {
	    	crearPuja();
	    	observarDatos();
	    	limpiarCampos();
        	singleton.guardarRegistroLog("Usuario: "+usser+"-"+contrasena, 1, "Creo una puja");
		}else {
			singleton.mostrarMensaje("Error", "Error al crear puja", "", AlertType.WARNING);
		}

    }

	@FXML
    void ActualizarPuja(ActionEvent event) throws Exception {
    	actualizarPuja();
    	observarDatos();
    	limpiarCampos();
    }

	@FXML
    void EliminarPuja(ActionEvent event) throws Exception {
    	String usser = (String) JOptionPane.showInputDialog(null, "Ingrese su Usuario","Verificacion de Usuario", 1, null, null, "");
		String contrasena = (String) JOptionPane.showInputDialog(null, "Ingrese su Contraseña","Verificacion de Usuario", 1, null, null, "");
    	
		if (singleton.verificarComprador(usser, contrasena)) {
			eliminarPuja();
			observarDatos();
			limpiarCampos();
        	singleton.guardarRegistroLog("Usuario: "+usser+"-"+contrasena, 1, "elimino una puja");
		}else {
			singleton.mostrarMensaje("Error", "Error al eliminar puja", "", AlertType.WARNING);
		}

    }
	
	@FXML
    void buscarPuja(ActionEvent event) throws PujaException {
    	buscarPuja();
    }

    @FXML
    void exportarPujas(ActionEvent event) throws Exception {
    	String usser = (String) JOptionPane.showInputDialog(null, "Ingrese su Usuario","Verificacion de Usuario", 1, null, null, "");
		String contrasena = (String) JOptionPane.showInputDialog(null, "Ingrese su Contraseña","Verificacion de Usuario", 1, null, null, "");
    	
		if (singleton.verificarAnunciante(usser, contrasena)) {
	    	singleton.exportarPujas();
	    	singleton.mostrarMensaje("Exportar", "", "El archivo: Pujas, ha sido guardado con exito", AlertType.CONFIRMATION);
			singleton.guardarRegistroLog("Un archivo Pujas ha sido exportado por el usuario: "+ contrasena, 1, "Exportar: ");
		}else {
			singleton.mostrarMensaje("Error", "Error al exportar", "", AlertType.WARNING);
	}
    }

    @FXML
    void volverAnuncioC(ActionEvent event) {
    	singleton.mostrarCrudArticuloAnuncioC("/co/uniquindio/edu/Empresa/views/CrudArticuloAnuncio.fxml");
    }
    
    @FXML
    void volverAnuncioA(ActionEvent event) {
    	singleton.mostrarCrudArticuloAnuncioA("/co/uniquindio/edu/Empresa/views/CrudArticuloAnuncio.fxml");
    }
    
    @FXML
    void buscarListaPujas(ActionEvent event) throws PujaException {
    	buscarListaPuja();
    	observarDatos();
    }
    
	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws PujaException {
    	limpiarCampos();
    	observarDatos();
    }

	public Tab getTabVerPuja() {
		return tabVerPuja;
	}

	public Tab getTabCrearPuja() {
		return tabCrearPuja;
	}

	public TabPane getTabPanePP() {
		return tabPanePP;
	}
    
	/////------------------METODOS CRUD -----------/////////////////////
	/**
	 * metodo que me permite crear una puja , obteniendo
	 * la informacion de los campos de texto y con la logica de la clase Anuncio
	 * @throws Exception
	 */
	private void crearPuja() throws Exception {
		String id = txtIdPuja.getText();
		Double valorApujar = Double.parseDouble(txtValorApujar.getText());
		Usuario usuario = cboxUsuarios.getValue();
		Anuncio anuncio = cboxAnuncioCrud.getValue();
		
		singleton.crearPuja(id, valorApujar, usuario, anuncio);
		//el guardar puja ya esta dentro del crear puja
    	singleton.mostrarMensaje("Operacion exitosa", "Puja creada",
    			"La informacion de su puja es la siguiente: \n"+
    			"Id: "+id+ "\n" +"\n"+anuncio+"\n"+usuario+"\n"+"valor a pujar:"+valorApujar, AlertType.INFORMATION);
    	
    	singleton.guardarRegistroLog("Puja creada", 1, "crearPuja");

	}
	
	/**
	 * metodo que me permite actualizar una puja ya creado, obteniendo
	 * la informacion de los campos de texto y con la logica de la clase Empresa
	 * @throws Exception
	 */
    private void actualizarPuja() throws Exception {
    	String id = txtIdPuja.getText();
		Double nuevoValorApujar = Double.parseDouble(txtValorApujar.getText());
		Usuario nuevoUsuario = cboxUsuarios.getValue();
		Anuncio nuevoAnuncio = cboxAnuncioCrud.getValue();
		
		singleton.actualizarPuja(id, nuevoValorApujar, nuevoUsuario, nuevoAnuncio);
    	singleton.mostrarMensaje("Operacion exitosa", "Puja actualizada", "operacion exitosa", AlertType.INFORMATION);
    	singleton.guardarRegistroLog("Puja actualizada", 1, "actualizarPuja");
	}
    
    
    /**
     * metodo que me permite eliminar un usuario ya creado,
	 * obteniendo su id y con la logica de la clase Empresa
     * @throws Exception 
     */
    private void eliminarPuja() throws Exception {
    	String id = txtIdPuja.getText();
    	Anuncio anuncio = cboxAnuncioCrud.getValue();
    	
    	singleton.eliminarPuja(id, anuncio);
	}
    
    
    /**
     * metodo que me permite buscar una puja ya creado,
	 * obteniendo su id y el anuncio al que correpsonde y
	 * con la logica de la clase Empresa
     * @throws PujaException
     */
    private void buscarPuja() throws PujaException{
    	String id = txtIdPuja.getText();
    	Anuncio anuncio = cboxAnuncioCrud.getValue();
    	
    	ArrayList<Anuncio> listaAnuncios = singleton.empresa.getListaAnuncios();
    	
    	for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i)== anuncio) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				for(Puja p: anuncioAux.getListaPujas()){
					if (p.getId().equals(id)) {
						txtValorApujar.setText(String.valueOf(p.getValorApujar()));
						cboxUsuarios.setValue(p.getUsuario());
					}
				}
			}
		}
    }
    
    /**
     * Metodo que me permite buscar una lista de pujas para un anuncio
     */
    private void buscarListaPuja() {
    	
    	Anuncio anuncio = cboxAnuncioVista.getValue();
		ArrayList<Anuncio> listaAnuncios= singleton.empresa.getListaAnuncios();
	
		for(Anuncio a: listaAnuncios){
			if (a.equals(anuncio)) {
				vistaListaPujaCrud.setAll(a.getListaPujas());
			}
			
		}	
	}
	////////////-------------OTROS METODOS--------------
    
    /**
     * Metodo que me permite colocar los valores 
     * de una lista en la tabla que el usuario va a observar
     * @throws PujaException 
     */
	private void observarDatos() throws PujaException {
		//para tab: crear puja
    	vistaListaAnuncios.setAll(singleton.listaAnuncios());
    	cboxAnuncioCrud.setItems(vistaListaAnuncios);
    	
    	vistaListaUsuarios.setAll(singleton.listaUsuarios());
    	cboxUsuarios.setItems(vistaListaUsuarios);
	
    	//para tab: ver pujas
		col_IdPujaVista.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_ValorApujarVista.setCellValueFactory(new PropertyValueFactory<>("valorApujar"));
		col_UsuarioVista.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		cboxAnuncioVista.setItems(vistaListaAnuncios);
		
		tblPujasVista.setItems(vistaListaPujaCrud);
		
	}
	
	/**
	 * metodod que me permite limpiar los campos de texto
	 * y asignar informacion para el usuario
	 */
	private void limpiarCampos() {
		//para crud de puja
		txtIdPuja.setText("");
		txtIdPuja.setPromptText("Ingrese un nuevo id");
		txtValorApujar.setText("");
		txtValorApujar.setPromptText("Ingrese el valor a pujar");
		cboxUsuarios.setValue(null);
		cboxAnuncioCrud.setValue(null);
		
		tblPujasVista.getSelectionModel().clearSelection();
	}
	
	
}
