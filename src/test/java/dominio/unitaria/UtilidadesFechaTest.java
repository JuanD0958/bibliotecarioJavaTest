package dominio.unitaria;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import dominio.UtilidadesFecha;

public class UtilidadesFechaTest {

	@Test
	public void testPrestamoHasta() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String SfechaInicio = "26-05-2017";
		String SfechaFinEsperada = "12-06-2017";
		try {
			Date fechaInicioPrestamo = formatter.parse(SfechaInicio);
			Date fechaFinPrestamoEsperada = formatter.parse(SfechaFinEsperada);
			Date fechaFinRetornada = UtilidadesFecha.prestamoHasta("A874B69Q", fechaInicioPrestamo, 15);
			Assert.assertEquals(fechaFinPrestamoEsperada, fechaFinRetornada);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testPrestamoHastaM(){
		testPrestamoHasta("26-05-2017", "12-06-2017");
		testPrestamoHasta("27-05-2017", "13-06-2017");
		testPrestamoHasta("24-05-2017", "09-06-2017");
		testPrestamoHasta("22-03-2018", "07-04-2018");
	}
	
	
	public void testPrestamoHasta(String fechaInicio, String fechaHasta){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String SfechaInicio = fechaInicio;
		String SfechaFinEsperada = fechaHasta;
		try {
			Date fechaInicioPrestamo = formatter.parse(SfechaInicio);
			Date fechaFinPrestamoEsperada = formatter.parse(SfechaFinEsperada);
			Date fechaFinRetornada = UtilidadesFecha.prestamoHasta("A874B69Q", fechaInicioPrestamo, 15);
			Assert.assertEquals(fechaFinPrestamoEsperada, fechaFinRetornada);
		} catch (Exception e) {
		}
	}

}