package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;

public class Articulo implements Serializable{
	/**
	 * Atributos
	 */
	private String id;
	private String nombre;
	private String descripcion;
	/**
	 * relacion con otras clases
	 */
	private TipoArticulo tipoArticulo;
	/**
	 * Constructor vacio
	 */
	public Articulo() {
		super();
	}
	/**
	 * Constructor con atributos
	 * @param id
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param tipoArticulo
	 */
	public Articulo(String id, String nombre, String descripcion, TipoArticulo tipoArticulo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoArticulo = tipoArticulo;
	}

	/**
	 * constructor solo con atributo nombre para archivo persistencia
	 * @param nombre
	 */
	public Articulo(String nombre) {
		super();
		this.nombre = nombre;
	}
	/**
	 * Metodos Get de cada atributo
	 * @return
	 */
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public TipoArticulo getTipoArticulo() {
		return tipoArticulo;
	}
	
	/**
	 * Metodos Set de cada atributo
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setTipoArticulo(TipoArticulo tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}
	/**
	 * Metodo Hashcode con atributo: id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/**
	 * Metodo Equals con atributo: id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
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
		return nombre + ", tipoArticulo: " + tipoArticulo;
	}
	
//	@Override
//	public String toString() {
//		return "Articulo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipoArticulo="
//				+ tipoArticulo + "]";
//	}



}
