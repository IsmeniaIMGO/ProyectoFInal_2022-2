package co.uniquindio.edu.Empresa.services;
import co.uniquindio.edu.Empresa.exceptions.*;
import co.uniquindio.edu.Empresa.model.*;

public interface ICrudUsuario {
	
	public Usuario crearUsuario(String id, String nombre, int edad, TipoUsuario tipoUsuario) throws Exception;
	
	public Usuario buscarUsuario(String id);
	
	public void eliminarUsuario(String id) throws UsuarioException;
	
	public void actualizarUsuario(String id, String nuevoNombre, int nuevaEdad, TipoUsuario nuevoTipo);
	
	public boolean existeUsuario(String id) throws NullPointerException;
}
