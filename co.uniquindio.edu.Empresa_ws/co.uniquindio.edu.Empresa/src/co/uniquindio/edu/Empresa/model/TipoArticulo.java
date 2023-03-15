package co.uniquindio.edu.Empresa.model;

import java.io.Serializable;

public enum TipoArticulo implements Serializable{
	/**
	 * tipos de TipoArticulo
	 */
	TECNOLOGIA(0), HOGAR(1), DEPORTES(2), VEHICULOS(3), BIENRAIZ(4);
	
	/**
	 * atributos
	 */
	private int numTipo;
	
	/**
	 * Constructor
	 * @param tipo
	 */
	private TipoArticulo(int tipo) {
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
