package co.uniquindio.edu.Empresa.services;

import co.uniquindio.edu.Empresa.model.Anuncio;

public interface IPujaGanadora {
	public String informarPujaGanadora(Anuncio a, String fechaInicio, String fechaFinal) throws  Exception;

}
