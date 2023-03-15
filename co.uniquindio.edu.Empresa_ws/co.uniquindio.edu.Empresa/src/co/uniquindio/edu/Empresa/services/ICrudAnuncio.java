package co.uniquindio.edu.Empresa.services;

import java.util.ArrayList;

import co.uniquindio.edu.Empresa.model.*;

public interface ICrudAnuncio {
	public void crearAnuncio(String id, String fechaInicio, String fechaFin, double valorInicial, Articulo articulo, Usuario usuario, ArrayList<Puja> listaPujas)throws Exception;
	
	public Anuncio buscarAnuncio(String id);
	
	public void eliminarAnuncio(String id) throws Exception;
	
	public void actualizarAnuncio(String id, String nuevaFechaInicio, String nuevaFechaFin, double nuevoValorInicial, Articulo nuevoArticulo, Usuario nuevoUsuario);

	public boolean existeAnuncio(String id) throws NullPointerException;
}
