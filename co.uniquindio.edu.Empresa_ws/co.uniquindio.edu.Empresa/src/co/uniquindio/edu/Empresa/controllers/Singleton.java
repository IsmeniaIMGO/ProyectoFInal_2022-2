package co.uniquindio.edu.Empresa.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import co.uniquindio.edu.Empresa.application.Aplicacion;
import co.uniquindio.edu.Empresa.exceptions.ArticuloException;
import co.uniquindio.edu.Empresa.exceptions.PujaException;
import co.uniquindio.edu.Empresa.exceptions.UsuarioException;
import co.uniquindio.edu.Empresa.model.*;
import co.uniquindio.edu.Empresa.persistence.ArchivoUtil;
import co.uniquindio.edu.Empresa.persistence.Persistencia;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class Singleton {
	/**
	 * atributos
	 */
	Empresa empresa;
	private Aplicacion aplicacion;
	
	
	private static class SingletonHolder{
		private final static Singleton eInstance = new Singleton();
	}
	
	public static Singleton getInstance(){
		return SingletonHolder.eInstance;
	}
	
	/**
	 * metodo set de aplicacion
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion){
		this.aplicacion = aplicacion;
	}
	
	/**
	 * metodo get de empresa
	 * @return
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	
	//----------------Metodos para inicializar datos y manejo de persistencia------------------------
	
	public Singleton(){
		//1. inicializar datos y luego guardarlos en archivos
		
		iniciarSalvarDatosPrueba(); // solo se hace la primera vez que corre la aplicacion
		
		//2. cargar datos de los archivos
		
		cargarDatosDesdeArchivos(); 
		
		//3. guardar y obtener el recurso serializable binario
		guardarResourceBinario(); //solo se hace la primera vez que corre la aplicacion
		cargarResourceBinario();
		
		//4. guardar y obtener el recurso serializable XML
		guardarResourceXML(); //solo se hace la primera vez que corre la aplicacion
		cargarResourceXML();
		
		//verificar raiz
		if (empresa == null) {
			System.out.println("es null");
			inicializarDatos();
			
		guardarResourceXML(); //solo se hace la primera vez que corre la aplicacion
		}
	}

	/**
	 * 1. Metodo que llama a Inicializar los datos y guardar los archivos
	 */
	private void iniciarSalvarDatosPrueba() {
		inicializarDatos();
		
		try {
			guardarUsuarios(empresa.getListaUsuarios());
			guardarAnuncios(empresa.getListaAnuncios());
			guardarArticulos(empresa.getListaArticulos());
			//guardarPujas esta dentro del metodo crear pujas
			Persistencia.cargarDatosArchivos(empresa);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Metodo que permite inicializar datos
	 * para la empresa
	 */
	public void inicializarDatos(){
		empresa = new Empresa();	
		empresa.setNombre("Subastas Quindio");
		
		Usuario usuario = new Usuario();
		usuario.setId("1");
		usuario.setNombre("Marcela");
		usuario.setEdad(21);
		usuario.setTipoUsuario(TipoUsuario.ANUNCIANTE);
		
		this.empresa.getListaUsuarios().add(usuario);
		
		usuario = new Usuario();
		usuario.setId("2");
		usuario.setNombre("Manuel");
		usuario.setEdad(21);
		usuario.setTipoUsuario(TipoUsuario.COMPRADOR);
		
		this.empresa.getListaUsuarios().add(usuario);
		
		
		System.out.println("Empresa Inicializada "+ empresa.getNombre());
	}
	
	/**
	 * Metodo que guarda en un archivo los usuarios
	 * @param listaUsuarios
	 * @throws IOException
	 */
	public void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		Persistencia.guardarUsuarios(listaUsuarios);
	}
	
	/**
	 * Metodo que guarda en un archivo los articulos
	 * @param listaArticulos
	 * @throws IOException
	 */
	public void guardarArticulos(ArrayList<Articulo> listaArticulos) throws IOException {
		Persistencia.guardarArticulos(listaArticulos);
	}
	
	/**
	 * Metodo que guarda en un archivo los anuncios
	 * @param listaAnuncios
	 * @throws IOException
	 */
	public void guardarAnuncios(ArrayList<Anuncio> listaAnuncios) throws IOException {
		Persistencia.guardarAnuncios(listaAnuncios);
	}
	
	/**
	 * Metodo que guarda en un archivo las pujas
	 * @param listaPujas
	 * @param anuncio 
	 * @throws IOException
	 */
	public void guardarPujas(ArrayList<Puja> listaPujas)throws IOException {
		Persistencia.guardarPujas(listaPujas);
	}
	
	/**
	 * 2. Metodo que carga los datos de los archivos
	 */
	private void cargarDatosDesdeArchivos() {
		empresa = new Empresa();
		
		try {
			Persistencia.cargarDatosArchivos(empresa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3. Metodo que crea un archivo binario y lo guarda
	 */

	private void guardarResourceBinario() {
		Persistencia.guardarRecursoEmpresaBinario(empresa);
		
	}
	
	/**
	 * 3. metodo que carga la informacion de un archivo binario
	 */
	private void cargarResourceBinario() {
		Persistencia.cargarRecursoEmpresaBinario();
	}

	
	/**
	 * 4.Metodo que crea un archivo xml y lo guarda
	 */

	private void guardarResourceXML() {
		Persistencia.guardarRecursoEmpresaXML(empresa);
	}

	/**
	 * 4.Metodo que carga informacion de un archvivo xml
	 */
	private void cargarResourceXML() {
		Persistencia.cargarRecursoEmpresaXML();
		
	}

	/**
	 * Metodo que guarda en un archivo el registro de acciones que se realiza en la interfaz
	 * @param mensaje
	 * @param nivel
	 * @param accion
	 */
	public void guardarRegistroLog(String mensaje, int nivel, String accion) {
		Persistencia.guardaRegistroLog(mensaje, nivel, accion);
	}
	
	

	///---------------------------Metodos de cambio de ventanas--------------//
	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: Login
	 * @param ruta
	 */
	public void mostrarLogin(String ruta){
		aplicacion.mostrarLogin(ruta);
	}

	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: CrudUsuario
	 * @param ruta
	 */
	public void mostrarCrudUsuario(String ruta) {
		aplicacion.mostrarCrudUsuario(ruta);
	}

	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: CrudArticuloAnuncio
	 * para Anunciante
	 * @param ruta
	 */
	public void mostrarCrudArticuloAnuncioA(String ruta) {
		aplicacion.mostrarCrudArticuloAnuncioA(ruta);
	}
	
	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: CrudArticuloAnuncio
	 * para Comprador
	 * @param ruta
	 */
	public void mostrarCrudArticuloAnuncioC(String ruta) {
		aplicacion.mostrarCrudArticuloAnuncioC(ruta);
	}

	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: CrudPuja
	 * para Anunciante
	 * @param ruta
	 */
	public void MostrarCrudPujaA(String ruta) {
		aplicacion.mostrarCrudPujaA(ruta);
	}

	/**
	 * metodo que envia una ruta a aplicacion para que muestre la vista: CrudPuja
	 * para Comprador
	 * @param ruta
	 */
	public void MostrarCrudPujaC(String ruta) {
		aplicacion.mostrarCrudPujaC(ruta);
	}
	
	/**
	 * Metodo que envia una ruta a aplicacion para que me muestre la vista: Mensajes
	 * para anunciante
	 * @param string
	 */
	public void mostrarMensajes(String ruta) {
		aplicacion.mostrarMensajes(ruta);
		
	}
	//------------------------metodo de verificar usuarios para login--------------------

	public boolean verificarAnunciante(String usser, String contrasena) {
		return empresa.verificarAnunciante(usser, contrasena);
		
	}

	public boolean verificarComprador(String usser, String contrasena) {
		return empresa.verificarComprador(usser, contrasena);
	}


    //-------------------------metodo crud para USUARIO
	/**
	 * Metodo que le pide a empresa el metodo de crear usuario
	 * @param id
	 * @param nombre
	 * @param edad
	 * @param tipoUsuario
	 * @throws Exception
	 */
	public void crearUsuario(String id, String nombre, int edad, TipoUsuario tipoUsuario) throws Exception {
		empresa.crearUsuario(id, nombre, edad, tipoUsuario);
	}
	
	/**
	 * Metodo que me retorna la lista de usuarios de la empresa
	 * @return
	 */
	public ArrayList<Usuario> listaUsuarios() {
		return empresa.getListaUsuarios();
		
	}
	

	/**
	 * metodo que le pide a empresa el metodo de actualizar usuario
	 * @param id
	 * @param nuevoNombre
	 * @param nuevaEdad
	 * @param nuevoTipo
	 */
	public void actualizarUsuario(String id, String nuevoNombre, int nuevaEdad, TipoUsuario nuevoTipo){
		empresa.actualizarUsuario(id, nuevoNombre, nuevaEdad, nuevoTipo);
	}

	/**
	 * metodo que le pide a empresa el metodo de eliminar un usuario
	 * @param id
	 * @throws UsuarioException
	 */
	public void eliminarUsuario(String id) throws UsuarioException {
		empresa.eliminarUsuario(id);
	}

	//----------------------metodo crud para Articulo------------
	/**
	 * metodo que le pide a empresa el metodo de crear un articulo
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param tipoArticulo
	 * @throws Exception
	 */
	public void crearArticulo(String id, String nombre, String descripcion, TipoArticulo tipoArticulo) throws Exception {
			empresa.crearArticulo(id, nombre, descripcion, tipoArticulo);
	}
	
	/**
	 * metodo que me obtiene la lista de articulos de la empresa
	 * @return
	 */
	public ArrayList<Articulo> listaArticulos() {
		return empresa.getListaArticulos();
	}

	/**
	 * metodo que le pide a empresa el metodo de actualizar un articulo
	 * @param id
	 * @param nuevoNombre
	 * @param nuevaDescripcion
	 * @param nuevoTipoArticulo
	 * @throws Exception
	 */
	public void actualizarArticulo(String id, String nuevoNombre, String nuevaDescripcion,
			TipoArticulo nuevoTipoArticulo) throws Exception {
			empresa.actualizarArticulo(id, nuevoNombre, nuevaDescripcion, nuevoTipoArticulo);
	}

	/**
	 * metodo que le pide a la empresa el metodo de eliminar un articulo
	 * @param id
	 * @throws ArticuloException
	 */
	public void eliminarArticulo(String id) throws ArticuloException {
			empresa.eliminarArticulo(id);
	}
	
	//----------------------metodo crud para Anuncio------------

	/**
	 * metodo que le pide a empresa el metodo de crear un anuncio
	 * @param id
	 * @param fechaInicio
	 * @param fechaFin
	 * @param valorInicial
	 * @param articulo
	 * @param usuario
	 * @param listaPujas
	 * @throws Exception
	 */
	public void crearAnuncio(String id, String fechaInicio, String fechaFin, Double valorInicial, Articulo articulo,
			Usuario usuario, ArrayList<Puja> listaPujas) throws Exception {
			empresa.crearAnuncio(id, fechaInicio, fechaFin, valorInicial, articulo, usuario, listaPujas);
	}

	/**
	 * metodo que obtiene de la empresa la lista de anuncios
	 * @return
	 */
	public ArrayList<Anuncio> listaAnuncios() {
		return empresa.getListaAnuncios();
	}
	
	/**
	 * metodo que le pide a empresa el metodo de actualizar un anuncio
	 * @param id
	 * @param nuevaFechaInicio
	 * @param nuevaFechaFin
	 * @param nuevoValorInicial
	 * @param nuevoArticulo
	 * @param nuevoUsuario
	 */
	public void actualizarAnuncio(String id, String nuevaFechaInicio, String nuevaFechaFin, Double nuevoValorInicial,
			Articulo nuevoArticulo, Usuario nuevoUsuario) {
		empresa.actualizarAnuncio(id, nuevaFechaInicio, nuevaFechaFin, nuevoValorInicial, nuevoArticulo, nuevoUsuario);
		
	}

	/**
	 * metodo que le pide a empresa el metodo de eliminar un anuncio
	 * @param id
	 * @throws Exception
	 */
	public void eliminarAnuncio(String id) throws Exception {
			empresa.eliminarAnuncio(id);
	}

	//----------------------metodo crud para Puja------------
	/**
	 * metodo que para un anuncio en especifico me va crear una puja
	 * @param id
	 * @param valorApujar
	 * @param usuario
	 * @param anuncio
	 * @throws Exception
	 */
	public void crearPuja(String id, Double valorApujar, Usuario usuario, Anuncio anuncio) throws Exception {
		ArrayList<Anuncio> listaAnuncios = empresa.getListaAnuncios();
		for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i).equals(anuncio)) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				anuncioAux.crearPuja(id, valorApujar, usuario, anuncio);
				guardarPujas(anuncioAux.getListaPujas());
			}
		}
		
	}

	/**
	 * metodo que para un anuncio en especifico me va actualizar una puja
	 * @param id
	 * @param nuevoValorApujar
	 * @param nuevoUsuario
	 * @param nuevoAnuncio
	 * @throws Exception
	 */
	public void actualizarPuja(String id, Double nuevoValorApujar, Usuario nuevoUsuario, Anuncio nuevoAnuncio) throws Exception {
		ArrayList<Anuncio> listaAnuncios = empresa.getListaAnuncios();
		for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i).equals(nuevoAnuncio)) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				anuncioAux.actualizarPuja(id, nuevoValorApujar, nuevoUsuario, nuevoAnuncio);
				guardarPujas(anuncioAux.getListaPujas());
			}
		}
	}

	/**
	 * metodo que para un anuncio en especifico me va eliminar una puja
	 * @param id
	 * @param anuncio
	 * @throws Exception
	 */
	public void eliminarPuja(String id, Anuncio anuncio) throws Exception {
		ArrayList<Anuncio> listaAnuncios = empresa.getListaAnuncios();
    	
    	for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i)== anuncio) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				if (anuncioAux.getListaPujas().get(i).getId().equals(id)) {
					anuncioAux.eliminarPuja(id);
					guardarPujas(anuncioAux.getListaPujas());
				}
			}
		}
	}
	
	
	//-------------------otros metodos adicionales
    /**
     * metodod que me permite mostrar una ventana emergente para un mensaje
     * @param titulo
     * @param header
     * @param contenido
     * @param alertType
     */
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
	}

    /**
     * Metodo que me permite exportar los anuncios
     * en la ruta que el usuario elija
     * @throws Exception 
     */
	public void exportarAnuncios() throws Exception {
		String ruta = ArchivoUtil.mostrarVentanaDocumentos();
		
		ruta = ruta+"/archivoAnuncios.txt";
		ruta = ruta.replace('\\' , '/');
		
		Persistencia.exportarAnuncios(listaAnuncios(), ruta);
	}

	/**
	 * Metodo que me permite exportar las pujas
     * en la ruta que el usuario elija
	 * @throws Exception 
	 */
	public void exportarPujas() throws Exception {
		String ruta = ArchivoUtil.mostrarVentanaDocumentos();
		
		ruta = ruta+"/archivoPujas.txt";
		ruta = ruta.replace('\\' , '/');
		
		ArrayList<Anuncio> listaAnuncios = empresa.getListaAnuncios();
		for (int i = 0; i < listaAnuncios.size(); i++) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				Persistencia.exportarPujas(anuncioAux.getListaPujas(), ruta);
		}
		
		
	}

	/**
	 * Metodo que me permite acceder a la informacion de la puja ganadora
	 *  cuando se llegue al limite de la fecha
	 * @return
	 * @throws Exception
	 */
	public String informarGanador() throws Exception {
		String mensaje = "";
		
		for (Anuncio a: listaAnuncios()) {
			mensaje = empresa.informarPujaGanadora(a, a.getFechaInicio(), a.getFechaFinal());
		}
		return mensaje;
		
	}

	

}
