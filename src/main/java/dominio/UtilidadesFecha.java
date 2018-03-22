package dominio;

import java.util.Calendar;
import java.util.Date;

public class UtilidadesFecha {
	public static Date prestamoHasta(String isbn, Date fechaPrestamo, int cantidadDiasPrestamo) {
		if (sumatoriaDigitosIsbn(isbn) > 30) {
			fechaPrestamo = agregarDiasSinContarDomingos(fechaPrestamo, cantidadDiasPrestamo);
		} else {
			fechaPrestamo = null;
		}
		return fechaPrestamo;
	}

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
