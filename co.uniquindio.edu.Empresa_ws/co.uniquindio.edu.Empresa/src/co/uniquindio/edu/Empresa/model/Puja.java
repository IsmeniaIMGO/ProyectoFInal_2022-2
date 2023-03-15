package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;

public class Puja implements Serializable {

	/**
	 * Atributos
	 */
	private String id;
	private double valorApujar;
	/**
	 * Relacion con otras clases
	 */
	private Usuario usuario;
	private Anuncio anuncio;
	
	/**
	 * Constructor vacio
	 */
	public Puja() {
		super();
	}
	/**
	 * Constructor con atributos
	 * @param id
	 * @param valorApujar
	 * @param usuario
	 */
	public Puja(String id, double valorApujar, Usuario usuario, Anuncio anuncio) {
		super();
		this.id = id;
		this.valorApujar = valorApujar;
		this.usuario = usuario;
		this.anuncio = anuncio;
	}
	
	
	/**
	 * Metodos Get de cada atributo
	 * @return
	 */
	public String getId() {
		return id;
	}
	public double getValorApujar() {
		return valorApujar;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	/**
	 * Metodo Set de cada atributo
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void setValorApujar(double valorApujar) {
		this.valorApujar = valorApujar;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Puja other = (Puja) obj;
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
		return "Puja [id=" + id + ", valorApujar=" + valorApujar + ", usuario=" + usuario + "]";
	}
	
}
