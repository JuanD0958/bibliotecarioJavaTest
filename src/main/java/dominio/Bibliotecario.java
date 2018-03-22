package dominio;

import java.util.Date;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String EL_LIBRO_ES_PALINDROMO = "los libros palíndromos solo se pueden utilizar en la biblioteca";
	
	Date fechaActual = new java.util.Date();
	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public void prestar(String isbn, String nombreUsuario) throws PrestamoException {
		verificarLibro(isbn);
		Prestamo prestamo = new Prestamo(fechaActual,repositorioLibro.obtenerPorIsbn(isbn),UtilidadesFecha.prestamoHasta(isbn,fechaActual,15),nombreUsuario);		
		repositorioPrestamo.agregar(prestamo, nombreUsuario);
	}
	

	public boolean esPrestado(String isbn) {
		boolean esPrestado = false;
		if (repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn) != null) {
			esPrestado = true;
		}
		return esPrestado;
	}

	private static boolean esPalindromo(String isbn) {
		boolean esPalindromo = false;
		StringBuilder isbnToCompare = new StringBuilder(isbn);
		if (isbn.equalsIgnoreCase(isbnToCompare.reverse().toString())) {
			esPalindromo = true;
		}
		return esPalindromo;
	}
	
	private void verificarLibro(String isbn) throws PrestamoException{
		if (esPrestado(isbn)) {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		} else if (esPalindromo(isbn)) {
			throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
		}
	}
	


}
