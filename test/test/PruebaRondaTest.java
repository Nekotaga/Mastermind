package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import codigo.*;

@DisplayName("Test de Junit para probar los métodos crearByN y contarByN (este último implícitamente) la clase PruebaRonda a partir de su constructor")
class PruebaRondaTest {
	PruebaRonda ronda;
	ReferenciaFicha fichas[] = {
			new ReferenciaFicha(ReferenciaColor.ROJO),
			new ReferenciaFicha(ReferenciaColor.BURDEOS),
			new ReferenciaFicha(ReferenciaColor.AMARILLO),
			new ReferenciaFicha(ReferenciaColor.VERDE),
			new ReferenciaFicha(ReferenciaColor.LIMA),
			new ReferenciaFicha(ReferenciaColor.CELESTE),
			new ReferenciaFicha(ReferenciaColor.TEAL),
			new ReferenciaFicha(ReferenciaColor.AZUL)};
	ReferenciaFicha comb1[] = {fichas[0],fichas[1],fichas[2],fichas[3]};
	ReferenciaFicha comb2[] = {fichas[4],fichas[5],fichas[6],fichas[7]};
	ReferenciaFicha comb3[] = {fichas[3],fichas[2],fichas[1],fichas[0]};
	ReferenciaFicha comb4[] = {fichas[3],fichas[1],fichas[7],fichas[0],fichas[2]};
	ReferenciaFicha comb5[] = {fichas[5],fichas[5],fichas[7],fichas[0],fichas[3]};
	ReferenciaFicha comb6[] = {fichas[5],fichas[2],fichas[2],fichas[7],fichas[5]};
	
	@Test
	@DisplayName("Ninguna de las combinaciones es nula")
	void ningunaDeLasCombinacionesEsNula(){
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb1),new ReferenciaCombinacion(comb1));
		assertEquals(4,ronda.contadorNegras);
		assertEquals(0,ronda.contadorBlancas);
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb1),new ReferenciaCombinacion(comb2));
		assertEquals(0,ronda.contadorNegras);
		assertEquals(0,ronda.contadorBlancas);
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb1),new ReferenciaCombinacion(comb3));
		assertEquals(0,ronda.contadorNegras);
		assertEquals(4,ronda.contadorBlancas);
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb4),new ReferenciaCombinacion(comb5));
		assertEquals(2,ronda.contadorNegras);
		assertEquals(1,ronda.contadorBlancas);
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb4),new ReferenciaCombinacion(comb6));
		assertEquals(0,ronda.contadorNegras);
		assertEquals(2,ronda.contadorBlancas);
		ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb5),new ReferenciaCombinacion(comb6));
		assertEquals(1,ronda.contadorNegras);
		assertEquals(2,ronda.contadorBlancas);
	}
	@Test
	@DisplayName("Las combinaciones tienen longitudes distintas")
	void lasCombinacionesTinenLongitudesDistintas() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {		// Si ambas son nulas
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb1),new ReferenciaCombinacion(comb4));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {		// Si la propuesta es nula
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb2),new ReferenciaCombinacion(comb5));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {		// Si la original es nula
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb3),new ReferenciaCombinacion(comb6));
		});
	}
	@Test
	@DisplayName("Alguna de las dos combinaciones o ambas son nulas")
	void combinacionesNulas() {
		Assertions.assertThrows(NullPointerException.class, () -> {		// Si ambas son nulas
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(null),new ReferenciaCombinacion(null));
		});
		Assertions.assertThrows(NullPointerException.class, () -> {		// Si la propuesta es nula
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(comb5),new ReferenciaCombinacion(null));
		});
		Assertions.assertThrows(NullPointerException.class, () -> {		// Si la original es nula
			ronda = new PruebaRonda(0,new ReferenciaCombinacion(null),new ReferenciaCombinacion(comb3));
		});
	}
}