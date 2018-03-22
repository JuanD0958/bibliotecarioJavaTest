package dominio;

import java.util.Calendar;
import java.util.Date;

/**
 * CLASE UTILIDADES FECHA QUE CONTIENE MÉTODO PARA REALIZAR OPERACIONES CON
 * FECHAS.
 * 
 * @author juan.herrera
 *
 */
public class UtilidadesFecha {

	/**
	 * MÉTODO QUE RETONA UNA FECHA POSTERIOR A LA FECHA INGRESADA SI LA SUMA DE
	 * LOS DIGITOS DEL ISBN ES MAYOR A 30, SE SUMARAN LOS DÍAS INGRESADOS POR
	 * PARAMETRO A LA FECHA DE INICIO.
	 * 
	 * @param isbn
	 * @param fechaPrestamo
	 * @param cantidadDiasPrestamo
	 * @return
	 */
	public static Date prestamoHasta(String isbn, Date fechaPrestamo, int cantidadDiasPrestamo) {
		if (sumatoriaDigitosIsbn(isbn) > 30) {
			fechaPrestamo = agregarDiasSinContarDomingos(fechaPrestamo, cantidadDiasPrestamo);
		} else {
			fechaPrestamo = null;
		}
		return fechaPrestamo;
	}

	/**
	 * MÉTODO QUE SUMA LOS DIGITOS DE UNA CADENA.
	 * 
	 * @param isbn
	 * @return
	 */
	private static int sumatoriaDigitosIsbn(String isbn) {
		int valorTotal = 0;
		char[] arreglo = isbn.toCharArray();
		for (char caracter : arreglo) {
			if (Character.isDigit(caracter)) {
				valorTotal += Integer.parseInt(String.valueOf(caracter));
			}
		}
		return valorTotal;
	}

	/**
	 * MÉTODO QUE CUENTA UNA CANTIDAD DE DÍAS ESPECIFICADOS A UNA FECHA DE
	 * INICIO, EVITANDO TENER EN CUENTA LOS DOMINGOS.
	 * 
	 * @param fechaPrestamo
	 * @param cantidadDiasPrestamo
	 * @return
	 */
	private static Date agregarDiasSinContarDomingos(Date fechaPrestamo, int cantidadDiasPrestamo) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPrestamo);
		while (cantidadDiasPrestamo > 0) {
			if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				cantidadDiasPrestamo--;
			}
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}
}
