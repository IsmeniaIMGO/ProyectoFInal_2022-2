package co.uniquindio.edu.Empresa.exceptions;

public class ParametroVacioException extends Exception {
	public ParametroVacioException(String s) {
        super(s);
    }

	public ParametroVacioException() {
        super();
    }
}
