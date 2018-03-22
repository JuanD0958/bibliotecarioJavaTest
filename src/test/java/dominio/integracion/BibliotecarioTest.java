package dominio.integracion;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import persistencia.sistema.SistemaDePersistencia;
import testdatabuilder.LibroTestDataBuilder;

public class BibliotecarioTest {

	private static final String CRONICA_DE_UNA_MUERTA_ANUNCIADA = "Cronica de una muerta anunciada";

	private SistemaDePersistencia sistemaPersistencia;

	private RepositorioLibro repositorioLibros;
	private RepositorioPrestamo repositorioPrestamo;

	@Before
	public void setUp() {

		sistemaPersistencia = new SistemaDePersistencia();

		repositorioLibros = sistemaPersistencia.obtenerRepositorioLibros();
		repositorioPrestamo = sistemaPersistencia.obtenerRepositorioPrestamos();

		sistemaPersistencia.iniciar();
	}

	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}

	@Test
	public void prestarLibroTest() {
		// arrange
		Libro libro = new LibroTestDataBuilder().conTitulo(CRONICA_DE_UNA_MUERTA_ANUNCIADA).build();
		repositorioLibros.agregar(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, repositorioPrestamo);
		// act
		blibliotecario.prestar(libro.getIsbn(), "Juan");
		// assert
		Assert.assertTrue(blibliotecario.esPrestado(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtener(libro.getIsbn()).getNombreUsuario());
		Assert.assertNull(repositorioPrestamo.obtener(libro.getIsbn()).getFechaEntregaMaxima());

	}

	@Test
	public void prestarLibroConFechaLimiteTest() {
		Libro libro = new LibroTestDataBuilder().conIsbn("ab787878").build();
		repositorioLibros.agregar(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, repositorioPrestamo);
		// act
		blibliotecario.prestar(libro.getIsbn(), "Juan");
		// assert
		Assert.assertTrue(blibliotecario.esPrestado(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtener(libro.getIsbn()).getFechaEntregaMaxima());

	}

	@Test
	public void prestarLibroNoDisponibleTest() {
		// arrange
		Libro libro = new LibroTestDataBuilder().conTitulo(CRONICA_DE_UNA_MUERTA_ANUNCIADA).build();
		repositorioLibros.agregar(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, repositorioPrestamo);
		// act
		blibliotecario.prestar(libro.getIsbn(), "Juan");
		try {
			blibliotecario.prestar(libro.getIsbn(), "Juan");
			fail();
		} catch (PrestamoException e) {
			// assert
			Assert.assertEquals(Bibliotecario.EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE, e.getMessage());
		}
	}

	@Test
	public void prestarLibroPalindromoTest() {
		Libro libro = new LibroTestDataBuilder().conIsbn("ab12c21ba").build();
		repositorioLibros.agregar(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, repositorioPrestamo);
		try {
			blibliotecario.prestar(libro.getIsbn(), "Juan");
			fail();
		} catch (PrestamoException e) {
			Assert.assertEquals(Bibliotecario.EL_LIBRO_ES_PALINDROMO, e.getMessage());
		}
	}
}
