package co.uniquindio.edu.Empresa.services;

import co.uniquindio.edu.Empresa.exceptions.*;
import co.uniquindio.edu.Empresa.model.*;

public interface ICrudArticulo {
	public void crearArticulo(String id, String nombre, String descripcion, TipoArticulo tipoArticulo)  throws Exception;
	
	public Articulo buscarArticulo(String id);
	
	public void eliminarArticulo(String id) throws ArticuloException;
	
	public void actualizarArticulo(String id, String nuevoNombre, String nuevaDescripcion, TipoArticulo nuevoTipo) throws Exception;
	
	public boolean existeArticulo(String id) throws NullPointerException;
}
