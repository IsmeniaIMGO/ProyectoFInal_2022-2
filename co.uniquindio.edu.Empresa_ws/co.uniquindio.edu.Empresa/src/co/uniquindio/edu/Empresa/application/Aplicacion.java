package co.uniquindio.edu.Empresa.application;
	
import co.uniquindio.edu.Empresa.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Clase principal de la aplicacion
 * @author Ismenia Marcela Guevara Ortiz
 * @author Marcela Abonce Calle
 * @author Juan Manuel Gomez Ardila
 *
 */
public class Aplicacion extends Application {
	//atributos propios
	private Singleton singleton = Singleton.getInstance();
	private Stage Escena = new Stage();

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.Escena = primaryStage;
		this.Escena.setTitle("SUBASTAS QUINDIO");
		mostrarLogin("/co/uniquindio/edu/Empresa/views/Login.fxml");
	}
	
	
	/**
	 * metodo que me permitira mostrar la vista: Login para iniciar sesion
	 * @param ruta
	 */
	public void mostrarLogin(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			LoginController loginController = ventana.getController();
			loginController.setAplicacion(this);
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * metodo que me permitira mostrar la vista: CrudUsuario para crear un usuario
	 * @param ruta
	 */
	public void mostrarCrudUsuario(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			CrudUsuarioController crudUsuarioController = ventana.getController();
			crudUsuarioController.setAplicacion(this);
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * metodo que me permitira mostrar la vista: CrudArticuloAnuncio
	 * solo para el comprador, es decir que hay acciones inhabilitadas, 
	 * el comprador podra ver los anuncios disponibles
	 * @param ruta
	 */
	public void mostrarCrudArticuloAnuncioC(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			CrudArticuloAnuncioController crudArticuloAnuncioController = ventana.getController();
			crudArticuloAnuncioController.setAplicacion(this);
			
			crudArticuloAnuncioController.getTabCrudAA().getTabs().remove(1);
			crudArticuloAnuncioController.getTabCrudAA().getTabs().remove(0);
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que me permitira mostrar la vista: CrudArticuloAnuncio
	 * solo para el anunciante, es decir que hay acciones inhabilitadas,
	 * el anunciantes puede crear un articulo y crear un anuncio
	 * @param ruta
	 */
	public void mostrarCrudArticuloAnuncioA(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			CrudArticuloAnuncioController crudArticuloAnuncioController = ventana.getController();
			crudArticuloAnuncioController.setAplicacion(this);
			
			crudArticuloAnuncioController.getBtnIrAPuja().setDisable(true);
			crudArticuloAnuncioController.getTabCrudAA().getTabs().remove(2);
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * metodo que me permitira mostrar la vista: CrudPuja
	 * para el comprador, es decir que hay acciones inhabilitadas
	 * el comprador podra crear una puja
	 * @param ruta
	 */
	public void mostrarCrudPujaC(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			CrudPujaController crudPujaController = ventana.getController();
			crudPujaController.setAplicacion(this);
			
			crudPujaController.getTabPanePP().getTabs().remove(1);
			
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * metodo que me permitira mostrar la vista: CrudPuja
	 * para el Anunciante, es decir que hay acciones inhabilitadas
	 * el anunciantes podra ver la pujas relacionadas a su anuncio
	 * @param ruta
	 */
	public void mostrarCrudPujaA(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			CrudPujaController crudPujaController = ventana.getController();
			crudPujaController.setAplicacion(this);
			
			crudPujaController.getTabPanePP().getTabs().remove(0);
			
			
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Metodo que me permitira mostrar la vista: InformarGanador
	 * que me va a mostrar la informacion de la puja ganadora y a que
	 * anuncio corresponde
	 * @param ruta
	 */
	public void mostrarMensajes(String ruta) {
		try {
			FXMLLoader ventana = new FXMLLoader();
			ventana.setLocation(Aplicacion.class.getResource(ruta));
			
			AnchorPane diseño = (AnchorPane)ventana.load();
			InformarGanadorController informarGanadorController = ventana.getController();
			informarGanadorController.setAplicacion(this);
				
			Scene lugar = new Scene(diseño);
			Escena.setScene(lugar);
			Escena.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}


}
