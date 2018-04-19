package mastermind;

import java.util.LinkedList;
import java.util.ListIterator;

public class Tablero implements DibujableTablero{
	
	//Variables
	private byte maxRondas;
	private Combinacion combinacionOriginal;
	private LinkedList<Ronda> rondas;
	
	//Constructor
	Tablero(Dificultad dificultad){
		maxRondas=dificultad.getNumIntentos();
		crearTablero();
	}
	
	//Getters y Setters
	protected LinkedList<Ronda> getRondas(){
		return rondas;
	}
	protected Combinacion getCombinacionOriginal() {
		return combinacionOriginal;
	}protected void setCombinacionOriginal(Combinacion combinacionOriginal) {
		this.combinacionOriginal=combinacionOriginal;
	}
	
	//Métodos
	private void crearTablero() {
		rondas = new LinkedList<Ronda>();
	}
	protected void agregarRonda(Ronda ronda) {
		rondas.add(ronda);
	}
	
	//Interfaz
	public void mostrarTableroPropio() {
		Color color=Color.NEGRO;
		Ficha cOriginal[]=combinacionOriginal.getCombinacion();
		Ficha cPropuesta[];
		Ficha cByN[];
		Ronda ronda;
		ListIterator<Ronda> it;
		System.out.println();
		for (byte i=0;i<Constantes.ALTURA;i++) {
			if (i==1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),maxRondas,Constantes.RESET);
			else
				System.out.print("          ");
			for (Ficha fco:cOriginal) {
				if (combinacionOriginal.equals(rondas.getLast().getCombinacionPropuesta())||rondas.getLast().getContadorRonda()==maxRondas-1) {
					color=fco.getColorFicha();
					System.out.printf("%s      %s  ",color.getColorFondo(),Constantes.RESET);
				}else
					System.out.printf("%s      %s  ",color.getColorFondo(),Constantes.RESET);
			}
			System.out.println();
		}
		System.out.printf("\n ");
		for (byte i=0;i<(cOriginal.length+2)*3+(3*(cOriginal.length)/2)+1;i++)
			if (i%2==0)
				System.out.printf("%s  %s",Color.BLANCO.getColorFondo(),Constantes.RESET);
			else
				System.out.printf("%s  %s",Color.NEGRO.getColorFondo(),Constantes.RESET);
		System.out.println("\n");
		for (it = rondas.listIterator(rondas.size());it.hasPrevious();) {
			ronda=it.previous();
			for (byte i=0;i<Constantes.ALTURA;i++) {
				cPropuesta=ronda.getCombinacionPropuesta().getCombinacion();
				cByN=ronda.getResultadoByN().getCombinacion();
				if (i==1)
					System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),(ronda.getContadorRonda()+1),Constantes.RESET);
				else
					System.out.print("          ");
				for (Ficha f:cPropuesta)
					System.out.printf("%s      %s  ",f.getColorFicha().getColorFondo(),Constantes.RESET);
				System.out.print(" ");
				for (byte j=0;j<(cByN.length)/2;j++) {
					if (i==0) {
						if ((cByN.length)%2!=0&&cByN[j+(cByN.length/2)+1]!=null) {
							color=cByN[j+(cByN.length/2)+1].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else if ((cByN.length)%2==0&&cByN[j+(cByN.length/2)]!=null) {
							color=cByN[j+(cByN.length/2)].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					}else if (i==2) {
						if (cByN[j]!=null) {
							color=cByN[j].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
						if ((cByN.length)%2!=0&&j==(cByN.length/2)-1) {
							if (cByN[j+1]!=null) {
								color=cByN[j+1].getColorFicha();
								System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
							}else
								System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
						}
					}else
						System.out.print("   ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	public void mostrarTableroAjeno() {
		Color color;
		Ficha cOriginal[]=combinacionOriginal.getCombinacion();
		Ficha cPropuesta[];
		Ficha cByN[];
		System.out.println();
		for (Ronda r:rondas) {
			for (byte i=0;i<Constantes.ALTURA;i++) {
				cPropuesta=r.getCombinacionPropuesta().getCombinacion();
				cByN=r.getResultadoByN().getCombinacion();
				if (i==1)
					System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),(r.getContadorRonda()+1),Constantes.RESET);
				else
					System.out.print("          ");
				for (Ficha f:cPropuesta)
					System.out.printf("%s      %s  ",f.getColorFicha().getColorFondo(),Constantes.RESET);
				System.out.print(" ");
				for (byte j=0;j<(cByN.length)/2;j++) {
					if (rondas.getLast().getCombinacionPropuesta().equals(combinacionOriginal)||r.getContadorRonda()==rondas.getLast().getContadorRonda()&&i!=1) {
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
						if ((cByN.length)%2!=0&&j==(cByN.length/2)-1&&i==2)
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					}else if (i==0) {
						if ((cByN.length)%2!=0&&cByN[j+(cByN.length/2)+1]!=null) {
							color=cByN[j+(cByN.length/2)+1].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else if ((cByN.length)%2==0&&cByN[j+(cByN.length/2)]!=null) {
							color=cByN[j+(cByN.length/2)].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					}else if (i==2) {
						if (cByN[j]!=null) {
							color=cByN[j].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
						if ((cByN.length)%2!=0&&j==(cByN.length/2)-1) {
							if (cByN[j+1]!=null) {
								color=cByN[j+1].getColorFicha();
								System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
							}else
								System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
						}
					}else
						System.out.print("   ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.printf(" ");
		for (byte i=0;i<(cOriginal.length+2)*3+(3*(cOriginal.length)/2)+1;i++)
			if (i%2==0)
				System.out.printf("%s  %s",Color.BLANCO.getColorFondo(),Constantes.RESET);
			else
				System.out.printf("%s  %s",Color.NEGRO.getColorFondo(),Constantes.RESET);
		System.out.println("\n");
		for (byte i=0;i<Constantes.ALTURA;i++) {
			if (i==1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),maxRondas,Constantes.RESET);
			else
				System.out.print("          ");
			for (Ficha fco:cOriginal) {
				color=fco.getColorFicha();
				System.out.printf("%s      %s  ",color.getColorFondo(),Constantes.RESET);
			}
			System.out.println();
		}
		System.out.println();
	}
	public void mostrarTableroFinal() {
		Color color;
		Ficha cOriginal[]=combinacionOriginal.getCombinacion();
		Ficha cPropuesta[];
		Ficha cByN[];
		System.out.println();
		for (byte i=0;i<Constantes.ALTURA;i++) {
			if (i==1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),maxRondas,Constantes.RESET);
			else
				System.out.print("          ");
			for (Ficha fco:cOriginal) {
				color=fco.getColorFicha();
				System.out.printf("%s      %s  ",color.getColorFondo(),Constantes.RESET);
			}
			System.out.println();
		}
		System.out.printf("\n ");
		for (byte i=0;i<(cOriginal.length+2)*3+(3*(cOriginal.length)/2)+1;i++)
			if (i%2==0)
				System.out.printf("%s  %s",Color.BLANCO.getColorFondo(),Constantes.RESET);
			else
				System.out.printf("%s  %s",Color.NEGRO.getColorFondo(),Constantes.RESET);
		System.out.println("\n");
		for (byte i=0;i<Constantes.ALTURA;i++) {
			cPropuesta=rondas.getLast().getCombinacionPropuesta().getCombinacion();
			cByN=rondas.getLast().getResultadoByN().getCombinacion();
			if (i==1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),(rondas.getLast().getContadorRonda()+1),Constantes.RESET);
			else
				System.out.print("          ");
			for (Ficha f:cPropuesta)
				System.out.printf("%s      %s  ",f.getColorFicha().getColorFondo(),Constantes.RESET);
			System.out.print(" ");
			for (byte j=0;j<(cByN.length)/2;j++) {
				if (i==0) {
					if ((cByN.length)%2!=0&&cByN[j+(cByN.length/2)+1]!=null) {
						color=cByN[j+(cByN.length/2)+1].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else if ((cByN.length)%2==0&&cByN[j+(cByN.length/2)]!=null) {
						color=cByN[j+(cByN.length/2)].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
				}else if (i==2) {
					if (cByN[j]!=null) {
						color=cByN[j].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					if ((cByN.length)%2!=0&&j==(cByN.length/2)-1) {
						if (cByN[j+1]!=null) {
							color=cByN[j+1].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					}
				}else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
