package co.uniquindio.edu.Empresa.services;

import co.uniquindio.edu.Empresa.model.*;

public interface ICrudPuja {
	
	public void crearPuja(String id, double valorApujar, Usuario usuario, Anuncio anuncio)throws Exception;
	
	public Puja buscarPuja(String id);
	
	public void eliminarPuja(String id)throws Exception;
	
	public void actualizarPuja(String id, double nuevoValorApujar, Usuario usuario, Anuncio nuevoAnuncio)throws Exception;
	
	public boolean existePuja(String id) throws NullPointerException;
}
