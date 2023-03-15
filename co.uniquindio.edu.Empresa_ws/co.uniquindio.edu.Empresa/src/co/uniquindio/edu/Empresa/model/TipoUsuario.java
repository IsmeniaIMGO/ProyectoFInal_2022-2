package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;

public enum TipoUsuario implements Serializable{
	/**
	 * tipos de TipoUsuario
	 */
	
	ANUNCIANTE(0), COMPRADOR(1);
	
	/**
	 * atributos
	 */
	private int numTipo;
	
	/**
	 * Constructor
	 * @param tipo
	 */
	private TipoUsuario(int tipo) {
		numTipo = tipo;
	}
	
	/**
	 * Metodo Get
	 * @return
	 */
	public int getNumTipo() {
		return numTipo;
	}
	
	
}
