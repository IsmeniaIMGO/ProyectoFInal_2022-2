package co.uniquindio.edu.Empresa.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import co.uniquindio.edu.Empresa.model.*;

public class Persistencia {
	
	/**
	 * rutas donde se cean los archivos
	 */
	public static final String RUTA_ARCHIVO_USUARIOS = "src/co/uniquindio/edu/Empresa/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_ANUNCIOS = "src/co/uniquindio/edu/Empresa/resources/archivoAnuncios.txt";
	public static final String RUTA_ARCHIVO_ARTICULOS = "src/co/uniquindio/edu/Empresa/resources/archivoArticulos.txt";
	public static final String RUTA_ARCHIVO_PUJAS = "src/co/uniquindio/edu/Empresa/resources/archivoPujas.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/co/uniquindio/edu/Empresa/resources/Empresa_Log.txt";
	public static final String RUTA_ARCHIVO_MODELO_EMPRESA_BINARIO = "src/co/uniquindio/edu/Empresa/resources/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_EMPRESA_XML = "src/co/uniquindio/edu/Empresa/resources/model.xml";

	/**
	 * Metodo que me permite cargar datos de los archivos al sistema
	 * @param empresa
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void cargarDatosArchivos(Empresa empresa) throws FileNotFoundException, IOException {
		
		
		//cargar archivo de usuarios
		ArrayList<Usuario> usuariosCargados = cargarUsuarios();
		
		if(usuariosCargados.size() > 0)
			empresa.getListaUsuarios().addAll(usuariosCargados);

		//cargar archivos anuncios
		ArrayList<Anuncio> anunciosCargados = cargarAnuncios();
		
		if(anunciosCargados.size() > 0)
		empresa.getListaAnuncios().addAll(anunciosCargados);
	
		//cargar archivo articulo
		ArrayList<Articulo> articulosCargados = cargarArticulos();
		
		if(articulosCargados.size() > 0)
			empresa.getListaArticulos().addAll(articulosCargados);
		
		//cargar archivo pujas
		
		ArrayList<Puja> pujasCargadas = cargarPujas();
		//if (pujasCargadas.size()>0) {
			ArrayList<Anuncio> listaAnuncios = empresa.getListaAnuncios();
			
			for (int i = 0; i < listaAnuncios.size(); i++) {
				Anuncio anuncioAux = listaAnuncios.get(i);
				anuncioAux.getListaPujas().addAll(pujasCargadas);
			}
		//}
	}
	

	//-----------------------GUARDAR EN ARCHIVOS DE TEXTO
	/**
	 * Guarda en un archivo de texto todos la informacion de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		String contenido = "";
		
		for(Usuario usuario:listaUsuarios) 
		{
			contenido+= usuario.getId()+","+usuario.getNombre()+","+usuario.getEdad()+","+usuario.getTipoUsuario()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
	
	/**
	 * Guarda en un archivo de texto todos la informacion de todos los anuncios almacenados en el ArrayList
	 * @param listaAnuncios
	 * @throws IOException
	 */
	public static void guardarAnuncios(ArrayList<Anuncio> listaAnuncios) throws IOException {
		String contenido = "";
		
		for(Anuncio anuncio:listaAnuncios) 
		{
			contenido+= anuncio.getId()+","+anuncio.getFechaInicio()+","+anuncio.getFechaFinal()+","+anuncio.getValorInicial()+","+anuncio.getArticulo()+","+anuncio.getUsuario().getNombre()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIOS, contenido, false);
	}
	
	
	/**
	 * Guarda en un archivo de texto todos la informacion de los articulos almacenados en el ArrayList
	 * @param listaArticulos
	 * @throws IOException
	 */
	public static void guardarArticulos(ArrayList<Articulo> listaArticulos) throws IOException {
		String contenido = "";
		
		for(Articulo articulo:listaArticulos) 
		{
			contenido+= articulo.getId()+","+articulo.getNombre()+","+articulo.getDescripcion()+","+articulo.getTipoArticulo()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ARTICULOS, contenido, false);
	}
	
	
	/**
	 * Guarda en un archivo de texto todos la informacion de las pujas almacenadas en el ArrayList
	 * @param listaPujas
	 * @throws IOException
	 */
	public static void guardarPujas(ArrayList<Puja> listaPujas) throws IOException {
		String contenido = "";
		
		for(Puja puja:listaPujas) 
		{
			contenido+= puja.getId()+","+puja.getValorApujar()+","+puja.getUsuario().getNombre()+","+puja.getAnuncio().getArticulo()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS, contenido, false);
	}
	
	
//	--------------OBTENER INFORMACION DE ARCHIVOS DE TEXTO------------------------
	/**
	 * 
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Usuario usuario = new Usuario();
			usuario.setId(linea.split(",")[0]);
			usuario.setNombre(linea.split(",")[1]);
			usuario.setEdad(Integer.parseInt(linea.split(",")[2]));
			usuario.setTipoUsuario(TipoUsuario.valueOf(linea.split(",")[3]));
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	/**
	 * un Arraylist de anuncios con los datos obtenidos del archivo de texto indicado
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Anuncio> cargarAnuncios() throws FileNotFoundException, IOException{
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIOS);
		String linea="";
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anuncio anuncio = new Anuncio();
			anuncio.setId(linea.split(",")[0]);
			anuncio.setFechaInicio(linea.split(",")[1]);
			anuncio.setFechaFinal(linea.split(",")[2]);
			anuncio.setValorInicial(Double.parseDouble(linea.split(",")[3]));
			Articulo articulo = new Articulo(linea.split(",")[4]);
			anuncio.setArticulo(articulo);
			Usuario usuario = new Usuario(linea.split(",")[5]);
			anuncio.setUsuario(usuario);
			anuncios.add(anuncio);
		}
		return anuncios;
	}
	
	/**
	 * un Arraylist de articulos con los datos obtenidos del archivo de texto indicado
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Articulo> cargarArticulos() throws FileNotFoundException, IOException {
		ArrayList<Articulo> articulos =new ArrayList<Articulo>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ARTICULOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Articulo articulo = new Articulo();
			articulo.setId(linea.split(",")[0]);
			articulo.setNombre(linea.split(",")[1]);
			articulo.setDescripcion(linea.split(",")[2]);
			articulo.setTipoArticulo(TipoArticulo.valueOf(linea.split(",")[3]));
			articulos.add(articulo);
		}
		return articulos;
	}
	
	/**
	 * un Arraylist de pujas con los datos obtenidos del archivo de texto indicado
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Puja> cargarPujas() throws IOException{
		ArrayList<Puja> pujas =new ArrayList<Puja>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			
			Puja puja = new Puja();
			puja.setId(linea.split(",")[0]);
			puja.setValorApujar(Double.parseDouble(linea.split(",")[1]));
			Usuario usuario = new Usuario(linea.split(",")[2]);
			puja.setUsuario(usuario);
			Articulo articulo = new Articulo(linea.split(",")[3]);
			Anuncio anuncio = new Anuncio(articulo);
			puja.setAnuncio(anuncio);
			pujas.add(puja);
		}
		return pujas;
	}
	
///-------------------------LOG---------------

	/**
	 * Metodo que me permite guardar un registro de una accion
	 * @param mensajeLog
	 * @param nivel
	 * @param accion
	 */
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	//------------------------------------SERIALIZACION  y XML
	
	/**
	 * Metodo que me carga al sistema la estructura de la empresa del archivo bianrio
	 * @return
	 */
	public static Empresa cargarRecursoEmpresaBinario() {
		
		Empresa empresa = null;
		
		try {
			empresa = (Empresa)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_EMPRESA_BINARIO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresa;
	}
	
	
	/**
	 * Metodo que me guarda la estructura de la empresa en un archivo binario
	 * @param empresa
	 */
	public static void guardarRecursoEmpresaBinario(Empresa empresa) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_EMPRESA_BINARIO, empresa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * metodo que me carga al sistema la estructura de la empres de un archivo xml
	 * @return
	 */
	public static Empresa cargarRecursoEmpresaXML() {
		
		Empresa empresa = null;
		
		try {
			empresa = (Empresa)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_EMPRESA_XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresa;

	}

	
	/**
	 * metodo que me guarda la estructura de la empresa en un archivo xml
	 * @param empresa
	 */
	public static void guardarRecursoEmpresaXML(Empresa empresa) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_EMPRESA_XML, empresa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

///-------------------Exportar archivos---------------------------
	/**
	 * Metodo que me permite exporta los anuncios en una archivo en la ruta que el usuario haya elegido
	 * @param listaAnuncios
	 * @param ruta
	 * @throws Exception
	 */
	public static void exportarAnuncios(ArrayList<Anuncio> listaAnuncios, String ruta) throws Exception {
		String contenido = "";
		
		for(Anuncio anuncio:listaAnuncios) 
		{
			contenido+= anuncio.getId()+","+anuncio.getFechaInicio()+","+anuncio.getFechaFinal()+","+anuncio.getValorInicial()+","+anuncio.getArticulo()+","+anuncio.getUsuario().getNombre()+"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, false);
	}


	/**
	 * Metodo que me permite exporta las pujas en una archivo en la ruta que el usuario haya elegido
	 * @param listaPujas
	 * @param ruta
	 * @throws Exception
	 */
	public static void exportarPujas(ArrayList<Puja> listaPujas, String ruta) throws Exception {
		String contenido = "";
		
		for(Puja puja:listaPujas) 
		{
			contenido+= puja.getId()+","+puja.getValorApujar()+","+puja.getUsuario().getNombre()+","+puja.getAnuncio().getArticulo()+"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, false);
		
	}



	
}
