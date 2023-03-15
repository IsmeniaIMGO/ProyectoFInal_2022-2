package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import co.uniquindio.edu.Empresa.exceptions.*;
import co.uniquindio.edu.Empresa.services.*;

public class Anuncio implements ICrudPuja, Serializable{
	/**
	 * Atributos
	 */
	private String id;
	private String fechaInicio;
	private double valorInicial;
	private String fechaFinal;
	
	
	/**
	 * relacion con otras clases
	 */
	private Articulo articulo;
	private Usuario usuario;
	private ArrayList<Puja>listaPujas = new ArrayList<Puja>();
	
	
	/**
	 * constructor vacio
	 */
	public Anuncio() {
		super();
	}
	
	
	/**
	 * Constructor con atributos
	 * @param id
	 * @param fechaInicio
	 * @param valorInicial
	 * @param fechaFinal
	 * @param articulo
	 * @param usuario
	 * @param listaPujas
	 */
	public Anuncio(String id, String fechaInicio, double valorInicial, String fechaFinal, Articulo articulo,
			Usuario usuario, ArrayList<Puja> listaPujas) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.valorInicial = valorInicial;
		this.fechaFinal = fechaFinal;
		this.articulo = articulo;
		this.usuario = usuario;
		this.listaPujas = listaPujas;
	}
	
	
	/**
	 * constructor solo con el articulo para que archivo de persistencia solo reciba este atributo
	 * @param articulo
	 */
	public Anuncio(Articulo articulo) {
		super();
		this.articulo = articulo;
	}


	/**
	 * Metodos Get de cada atributo
	 * @return
	 */
	public String getId() {
		return id;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public double getValorInicial() {
		return valorInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public ArrayList<Puja> getListaPujas() {
		return listaPujas;
	}
	
	
	/**
	 * Metodos Set de cada atributo
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setListaPujas(ArrayList<Puja> listaPujas) {
		this.listaPujas = listaPujas;
	}
	
	
	/**
	 * Metodo HashCode con atributo: id
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
		Anuncio other = (Anuncio) obj;
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
		return "Anuncio: " +id + ", articulo: " + articulo;
	}
	
//	@Override
//	public String toString() {
//		return "Anuncio [id=" + id + ", fechaInicio=" + fechaInicio + ", valorInicial=" + valorInicial + ", fechaFinal="
//				+ fechaFinal + ", articulo=" + articulo + ", usuario=" + usuario + ", listaPujas=" + listaPujas + "]";
//	}


	/**
	 * Metodo heredado del paquete services
	 * que me permite crear una puja
	 */
	@Override
	public void crearPuja(String id, double valorApujar, Usuario usuario, Anuncio anuncio)throws Exception {
		if (id == null || id.equals(""))
			throw new NuloVacioException("el id de la Puja es nulo o vacio");
		
		if(existePuja(id))
			throw new IdYaExisteException("Este ID ya se encuentra registrado");
		
		if (valorApujar<valorInicial) {
			throw new ValorAPujarMenorException("El valor a pujar no puede ser menor al valor inicial del anuncio");
		}
	
		if(usuario == null || valorApujar == 0 || anuncio == null)
			throw new ParametroVacioException("Alguno de los parametros indicados es esta vacio");
		
		Puja puja = new Puja(id, valorApujar, usuario, anuncio);
		anuncio.listaPujas.add(puja);
	}
	
	

	/**
	 * Metodo heredado del paquete services
	 * que me permite buscar una puja
	 */
	@Override
	public Puja buscarPuja(String id) {
		if(!id.equals("")){
			for(Puja p : listaPujas){
				if(p != null && p.getId() != null && p.getId().equals(id))
					return p;
			}
		}
		return null;
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite eliminar una puja
	 */
	@Override
	public void eliminarPuja(String id) throws PujaException {
		if (!existePuja(id))
            throw new PujaException("La puja con ID " + id + " no se encuentra registrada dentro de la empresa");
		
        for (Puja p : listaPujas) {
            if (p.getId().equals(id)) {
                listaPujas.remove(p);
                break;
            }
        }	
	}
	
	
	/**
	 * Metodo heredado del paquete services
	 * que me permite actualizar una puja
	 */
	@Override
	public void actualizarPuja(String id, double nuevoValorApujar, Usuario nuevoUsuario, Anuncio nuevoAnuncio)throws Exception {
		if(!id.equals("")){
			
			for(Puja p : listaPujas){
				if(p != null && p.getId() != null && p.getId().equals(id)){
					if(nuevoValorApujar != 0 && usuario != null && nuevoAnuncio != null) {
						p.setValorApujar(nuevoValorApujar);
						p.setUsuario(nuevoUsuario);
						p.setAnuncio(nuevoAnuncio);
					}
				}
			}
		}
	}

	/**
	 * Metodo heredado del paquete de services,
	 * que permite saber si la puja existe dentro 
	 * de la lista de pujas
	 */
	@Override
	public boolean existePuja(String id) throws NullPointerException {
		for(Puja p: listaPujas) {
			if(p.getId().equals(id)) return true;
		}
		return false;
	}


//----------------------METODOS PARA INFORMAR PUJA GANADORA---------------

	/**
	 * metodo que busca valor de la puja ganadora
	 * @return valor
	 */
	public Double pujaValorMayor() {
		Double valor = 0.0;
		ArrayList<Double> valores = new ArrayList<Double>();
		
		for(Puja p: this.listaPujas){
			valores.add(p.getValorApujar());
			valor = Collections.max(valores);
		}
		
		return valor;
	}
	
	
	/**
	 * Metodo que busca el usuario de la puja ganadora
	 * @return nombre de usuario
	 */
	public String pujaUsuarioGanador() {
		String usuario = "";
		Double valor = 0.0;
		ArrayList<Double> valores = new ArrayList<Double>();
		
		for(Puja p: this.listaPujas){
			valores.add(p.getValorApujar());
			valor = Collections.max(valores);
		}
		
		
		for (Puja p: this.listaPujas) {
			if (p.getValorApujar()==valor) {
				usuario = p.getUsuario().getNombre();
			}
		}
		return usuario;
	}



	
	
}
