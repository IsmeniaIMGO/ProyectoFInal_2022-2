package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import co.uniquindio.edu.Empresa.exceptions.*;
import co.uniquindio.edu.Empresa.services.*;

public class Empresa implements ICrudArticulo, ICrudAnuncio, ICrudUsuario, IPujaGanadora, Serializable{
	/**
	 * Atributos
	 */
	private String nombre;
	/**
	 * Relacion con otras clases
	 */
	private ArrayList<Articulo>listaArticulos = new ArrayList<Articulo>();
	private ArrayList<Anuncio>listaAnuncios = new ArrayList<Anuncio>();
	private ArrayList<Usuario>listaUsuarios = new ArrayList<Usuario>();
	
	/**
	 * Constructor vacio
	 */
	public Empresa() {
	}
	/**
	 * Constructor con atributos
	 * @param nombre
	 * @param listaArticulos
	 * @param listaAnuncios
	 * @param listaUsuarios
	 */
	public Empresa(String nombre, ArrayList<Articulo> listaArticulos, ArrayList<Anuncio> listaAnuncios,
			ArrayList<Usuario> listaUsuarios) {
		this.nombre = nombre;
		this.listaArticulos = listaArticulos;
		this.listaAnuncios = listaAnuncios;
		this.listaUsuarios = listaUsuarios;
	}
	
	/**
	 * Metodos Get de cada atributo
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Articulo> getListaArticulos() {
		return listaArticulos;
	}
	public ArrayList<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}
	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	/**
	 * Metodos Set de cada atributo
	 * @param 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
	public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	/**
	 * Metodo HashCode con atributo: nombre
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	/**
	 * Metodo equals con atributo: nombre
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", listaArticulos=" + listaArticulos + ", listaAnuncios=" + listaAnuncios
				+ ", listaUsuarios=" + listaUsuarios + "]";
	}
	
	//---------------------------------------------------USUARIO---------------------------------------------
	/**
	 * Metodo heredado del paquete de services, 
	 * que permite crear un usuario
	 * @return 
	 */
	@Override
	public Usuario crearUsuario(String id, String nombre, int edad, TipoUsuario tipoUsuario) throws Exception{
		if (id == null || id.equals(""))
			throw new NuloVacioException("el id del cliente es nulo o vacio");
		
		if(existeUsuario(id))
			throw new IdYaExisteException("Este ID ya se encuentra registrado");
		
		if(edad < 18)
			throw new EdadRequeridaException("No cumple la edad para registrarse");
		
		if(nombre.equals("") || tipoUsuario.equals(""))
			throw new ParametroVacioException("Alguno de los parámetros indicados es está vacío");
		
		Usuario usuario = new Usuario(id, nombre, edad, tipoUsuario);
		this.listaUsuarios.add(usuario);
	
		return usuario;
	}
	
	/**
	 * Metodo heredado del paquete de services, 
	 * que permite buscar un usuario
	 */
	@Override
	public Usuario buscarUsuario(String id)  {
		
		if(!id.equals("")){
			for(Usuario u : listaUsuarios){
				if(u != null && u.getId() != null && u.getId().equals(id))
					return u;
			}
		}
		return null;
	}
	
	/**
	 * Metodo heredado del paquete de services, 
	 * que permite eliminar un usuario
	 */
	@Override
	public void eliminarUsuario(String id) throws UsuarioException {
		if (!existeUsuario(id))
            throw new UsuarioException("El usuario con ID " + id + " no se encuentra registrado dentro de la empresa");

        for (Usuario u : listaUsuarios) {
            if (u.getId().equals(id)) {
                listaUsuarios.remove(u);
                break;
            }
        }		
	}
	
	/**
	 * Metodo heredado del paquete de services, 
	 * que permite actualizar un usuario
	 */
	@Override
	public void actualizarUsuario(String id, String nuevoNombre, int nuevaEdad, TipoUsuario nuevoTipo){
		if(!id.equals("")){
		
			for(Usuario u : listaUsuarios){
				if(u != null && u.getId() != null && u.getId().equals(id)){
					if(!nuevoNombre.equals("")) u.setNombre(nuevoNombre);
					if(nuevaEdad > 18) u.setEdad(nuevaEdad);
					if(nuevoTipo != null) u.setTipoUsuario(nuevoTipo);
				}
			}
		}
	}
	
	/**
	 * Metodo heredado del paquete de services,
	 * que permite saber si el usuario existe dentro 
	 * de la lista de usuarios
	 */
	@Override
	public boolean existeUsuario(String id) throws NullPointerException {
		
		for (Usuario u : listaUsuarios) {
			if(u.getId().equals(id)) return true;
			
		}			
		return false;
	}
	
	//------------------------------------------------------------ANUNCIO-----------------------------------
	/**
	 * Metodo heredado del paquete services
	 * que me permite crear un anuncio
	 */
	@Override
	public void crearAnuncio(String id, String fechaInicio, String fechaFin, double valorInicial, Articulo articulo, Usuario usuario, ArrayList<Puja> listaPujas)throws Exception{
		if(existeAnuncio(id)) 
			throw new IdYaExisteException("este id ya se encuentra registrado");
		if(id == null)
			throw new NuloVacioException("el campo del ID se encuentra vacio");
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicioDate = date.parse(fechaInicio);
		Date fechaFinDate = date.parse(fechaFin);
		
		if (fechaFinDate.before(fechaInicioDate)) {
			throw new FechaFinalBeforeException("La fecha de terminacion no puede ser anterior a la fecha de inicio");
		}
		
		if(fechaInicio == null || fechaFin == null || valorInicial == 0 || articulo == null)
			throw new NuloVacioException("Diligencie todos los campos");
			
		Anuncio anuncio = new Anuncio(id, fechaInicio, valorInicial, fechaFin, articulo, usuario, listaPujas);
		this.listaAnuncios.add(anuncio);
		
		
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite buscar un anuncio
	 */
	@Override
	public Anuncio buscarAnuncio(String id) {
		if(!id.equals("")){
			for(Anuncio a: listaAnuncios){
				if(a != null && a.getId() != null && a.getId().equals(id))
					return a;
			}
		}
		return null;
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite eliminar un anuncio
	 * @return 
	 */
	@Override
	public void eliminarAnuncio(String id) throws Exception{
		
		if(!existeAnuncio(id))
			throw new AnuncioException("no existe ningun anuncio con id "+id+" registrado en la empresa");
		for(Anuncio a: listaAnuncios) {
			if(a.getId().equals(id))
				listaAnuncios.remove(a);
			break;
		}
	}
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite actualizar un anuncio
	 */
	@Override
	public void actualizarAnuncio(String id, String nuevaFechaInicio, String nuevaFechaFin, double nuevoValorInicial, Articulo nuevoArticulo, Usuario nuevoUsuario) {
		if(!id.equals("")){
			
			for(Anuncio a : listaAnuncios){
				if(a != null && a.getId() != null && a.getId().equals(id)){
					if(!nuevaFechaInicio.equals(""))
						a.setFechaInicio(nuevaFechaInicio);
					if(!nuevaFechaFin.equals(""))
						a.setFechaFinal(nuevaFechaFin);
					if(nuevoValorInicial != 0)
						a.setValorInicial(nuevoValorInicial);
					if(nuevoArticulo != null)
						a.setArticulo(nuevoArticulo);;
					if(nuevoUsuario != null)
						a.setUsuario(nuevoUsuario);
				}
			}
		}
	}
	
	/**
	 * Metodo heredado del paquete de services,
	 * que permite saber si el anuncio existe dentro 
	 * de la lista de anuncios
	 */
	@Override
	public boolean existeAnuncio(String id) throws NullPointerException {
		for (Anuncio a : listaAnuncios) {
			if(a.getId().equals(id)) return true;
			
		}			
		return false;
	}
	
	//----------------------------------------------------ARTICULO-----------------------------------
	/**
	 * Metodo heredado del paquete services
	 * que me permite crear un articulo
	 */
	@Override
	public void crearArticulo(String id, String nombre, String descripcion, TipoArticulo tipoArticulo)  throws Exception{
		if (id == null || id.equals(""))
			throw new NuloVacioException("el id del Articulo es nulo o vacio");
		
		if(existeArticulo(id))
			throw new IdYaExisteException("Este ID ya se encuentra registrado");
			
		if(nombre.equals("") || descripcion.equals("") || tipoArticulo.equals(""))
			throw new ParametroVacioException("Alguno de los parámetros indicados es está vacío");
		
		Articulo articulo = new Articulo(id, nombre, descripcion, tipoArticulo);
		this.listaArticulos.add(articulo);
		
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite buscar un articulo
	 */
	@Override
	public Articulo buscarArticulo(String id) {
		if(!id.equals("")){
			for(Articulo a: listaArticulos){
				if(a != null && a.getId() != null && a.getId().equals(id))
					return a;
			}
		}
		return null;
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite eliminar un articulo
	 */
	@Override
	public void eliminarArticulo(String id) throws ArticuloException {
		if (!existeArticulo(id))
            throw new ArticuloException("El articulo con ID " + id + " no se encuentra registrado dentro de la empresa");

        for (Articulo a : listaArticulos) {
            if (a.getId().equals(id)) {
                listaArticulos.remove(a);
                break;
            }
        }
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite actualizar un articulo
	 */
	@Override
	public void actualizarArticulo(String id, String nuevoNombre, String nuevaDescripcion, TipoArticulo nuevoTipoArticulo) throws Exception{
		if(!id.equals("")){
			
			for(Articulo a : listaArticulos){
				if(a != null && a.getId() != null && a.getId().equals(id)){
					if(!nuevoNombre.equals("")) a.setNombre(nuevoNombre);
					if(!nuevaDescripcion.equals("")) a.setDescripcion(nuevaDescripcion);
					if(nuevoTipoArticulo != null) a.setTipoArticulo(nuevoTipoArticulo);
				}
			}
		}
	}
	
	/**
	 * Metodo heredado del paquete de services,
	 * que permite saber si el Articulo existe dentro 
	 * de la lista de articulos
	 */
	@Override
	public boolean existeArticulo(String id) throws NullPointerException {
		for (Articulo a : listaArticulos) {
			if(a.getId().equals(id)) return true;
			
		}			
		return false;
	}
	
	//-----------verificacion de usuarios
	
	/**
    * Metodo que verifica que si un usuario es tipo anunciante
    * @param usser
    * @param contrasena
    * @return
    */
	public boolean verificarAnunciante(String usser, String contrasena) {
		Usuario usuario = buscarUsuario(usser);
		
		if ((usuario != null) && (usuario.getNombre().equals(contrasena)) && (usuario.getTipoUsuario() == TipoUsuario.ANUNCIANTE)) {
			return true;
		}
    	
		return false;
	}

	/**
	 * Metodo que verifica si un usuario es comprador
	 * @param usser
	 * @param contrasena
	 * @return
	 */
	public boolean verificarComprador(String usser, String contrasena) {
		Usuario usuario = buscarUsuario(usser);
		
		if ((usuario != null) && (usuario.getNombre().equals(contrasena)) && (usuario.getTipoUsuario() == TipoUsuario.COMPRADOR)) {
			return true;
		}
		return false;
	}
		 
	/**
	 * Metodo heredado del paquete services
	 * que me permite informar al sistema y al anunciante cual fue la puja ganadora
	 * @throws ParseException 
	 */
	@Override
	public String informarPujaGanadora(Anuncio a, String fechaInicio, String fechaFinal) throws Exception {
		String mensaje = "";
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicioDate = date.parse(fechaInicio);
		Date fechaFinDate = date.parse(fechaFinal);
		
		if (fechaInicioDate.equals(fechaFinDate)) {
				mensaje = "El anuncio: "+ a.getId()+" para el articulo: "+a.getArticulo().getNombre()+ " con valor inicial: "+a.getValorInicial()+ 
						" creado por :"+ a.getUsuario().getNombre()+ " Tiene una puja ganadora con valor final de: "+
						a.pujaValorMayor()+" y el usuario ganador es: "+a.pujaUsuarioGanador();
		}else {
			mensaje = "El anuncio: "+ a.getId()+" para el articulo: "+a.getArticulo().getNombre()+ " con valor inicial: "+a.getValorInicial()+ 
					" creado por :"+ a.getUsuario().getNombre()+ " aun no ha terminado.";
		}
		
		return mensaje;
	}
	
}
