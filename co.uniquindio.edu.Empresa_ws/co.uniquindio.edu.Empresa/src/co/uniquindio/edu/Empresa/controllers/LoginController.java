package co.uniquindio.edu.Empresa.controllers;
import java.net.URL;

import java.util.ResourceBundle;

import co.uniquindio.edu.Empresa.application.Aplicacion;
import co.uniquindio.edu.Empresa.exceptions.UsuarioException;
import co.uniquindio.edu.Empresa.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	/**
	 * atributos
	 */
	Empresa empresa = Singleton.getInstance().getEmpresa();
	Singleton singleton = Singleton.getInstance();
	Aplicacion aplicacion;
	
	//Metodo set de aplicacion
	public void setAplicacion(Aplicacion aplicacion){
		this.aplicacion = aplicacion;
		singleton.setAplicacion(aplicacion);
	}


	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCrearCuenta"
    private Button btnCrearCuenta; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsser"
    private TextField txtUsser; // Value injected by FXMLLoader

    @FXML // fx:id="btnIngresar"
    private Button btnIngresar; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private TextField txtPassword; // Value injected by FXMLLoader

    @FXML
    void ingresarUsuario(ActionEvent event){
    	 verificarInicioSesion();
    }

	@FXML
    void crearUsuario(ActionEvent event) {
    	singleton.mostrarCrudUsuario("/co/uniquindio/edu/Empresa/views/CrudUsuario.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
    
    /**
     * Metodo que me permite verificar el inicio de sesion
     */
    private void verificarInicioSesion() {
 	    String usser = txtUsser.getText();
    		String contrasena = txtPassword.getText();
    		
 	   if (!usser.equals("") && !contrasena.equals("")){
 			//si el tipo de usuario es anunciante entonces me abre una ventana especial para anunciantes
 			if (singleton.verificarAnunciante(usser, contrasena)) {
 				singleton.mostrarCrudArticuloAnuncioA("/co/uniquindio/edu/Empresa/views/CrudArticuloAnuncio.fxml");
 				singleton.guardarRegistroLog("Usuario: "+usser+"-"+contrasena, 1, "iniciarSesion");
 				
 			//si el tipo de usuario es comprador entonces me abre una ventana especial para anunciantes
 			}else if (singleton.verificarComprador(usser, contrasena)) {
 				singleton.mostrarCrudArticuloAnuncioC("/co/uniquindio/edu/Empresa/views/CrudArticuloAnuncio.fxml");
 				singleton.guardarRegistroLog("Usuario: "+usser+"-"+contrasena, 1, "iniciarSesion");
 				
 			}else {
 			//si no cumple las anteriores opciones entonces ingreso algo mal
 				singleton.mostrarMensaje("Error Ingreso", "", "La informacion digitada no es correcta ", AlertType.ERROR);
 			}
 		}else {
 			//si intenta iniciar sesion pero los campos estan vacios
 			singleton.mostrarMensaje("Parametros Vacios", "", "por favor llene los campos vacios", AlertType.WARNING);
 		}
 	
 	}
    
}

	

