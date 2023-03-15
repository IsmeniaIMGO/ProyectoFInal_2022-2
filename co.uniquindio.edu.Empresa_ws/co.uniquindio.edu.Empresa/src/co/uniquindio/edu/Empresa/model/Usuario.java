package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;

public class Usuario implements Serializable {
	/**
	 * Atributos
	 */
	private String id;
	private String nombre;
	private int edad;
	/**
	 * relacion con otras clases
	 */
	private TipoUsuario tipoUsuario;
	private Anuncio anuncio;
	/**
	 * Constructor vacio
	 */
	public Usuario() {
		super();
	}
	/**
	 * Constructor con atributos
	 * @param id
	 * @param nombre
	 * @param edad
	 * @param tipoUsuario
	 * @param anuncio
	 */
	public Usuario(String id, String nombre, int edad, TipoUsuario tipoUsuario, Anuncio anuncio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.tipoUsuario = tipoUsuario;
		this.anuncio = anuncio;
	}
	/**
	 * Constructor con todos los atributos menos el atributo anunci
	 * @param id
	 * @param nombre
	 * @param edad
	 * @param tipoUsuario
	 */
	public Usuario(String id, String nombre, int edad, TipoUsuario tipoUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.tipoUsuario = tipoUsuario;
	}
	
	
	/**
	 * constructor solo con atributo nombre para archivo persistencia
	 * @param nombre
	 * @param tipoUsuario
	 */
	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
	}
	/**
	 * Metodos Get para cada atributo
	 * @return
	 */
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	/**
	 * Metodos Set para cada atributo
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	/**
	 * Metodo hashCode con atributo: id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/**
	 * Metodo equals con atributo: id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return nombre;
	}
//	@Override
//	public String toString() {
//		return "Usuario [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", tipoUsuario=" + tipoUsuario;
//	}
//	
	
	
	
}
