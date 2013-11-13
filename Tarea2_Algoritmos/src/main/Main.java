package main;

import java.util.ArrayList;
import java.util.Collections;

import scheduler.*;

public class Main {
	
	static final int MACHINES_NUM = 3;
	
	public static void main(String[] args) {
	
		Scheduler sched = new Scheduler(MACHINES_NUM);
		ArrayList<Job> jobs = new ArrayList<Job>();
		
		System.out.println("--TENEMOS " + MACHINES_NUM + " MAQUINAS--");
		
		System.out.println("\n--:--");
		//final int fib[] = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
		//final int sum[] = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
		final int troll[] = {10, 5, 10, 6, 3, 2, 1, 2, 4, 5};

		for (int i = 0; i < 10; i++) {
			System.out.println("- El trabajo n�mero: " + (i + 1) + " tendr� duraci�n: " + troll[i] + ".");
			//System.out.println("- El trabajo n�mero: " + (i + 1) + " tendr� duraci�n: " + fib[i] + ".");
			//System.out.println("- El trabajo n�mero: " + (i + 1) + " tendr� duraci�n: " + sum[i] + ".");
			Job job = new Job(troll[i]);
			//Job job = new Job(fib[i]);
			//Job job = new Job(sum[i]);
			jobs.add(job);
		}
		System.out.println("--:--");
		
		System.out.println("\n-> Ahora insertaremos los 10 trabajos con el Algoritmo Optimo Offline");
		System.out.println("-> Este algoritmo se puede demorar un poco... espere por favor");
		
		sched.addOfflineBrute(jobs);
		int stressOpt = sched.getMostStressedMachine().getStress();
		
		System.out.println("\n--RESULTADOS DEL OPTIMO--");
		System.out.println(sched);
		
		System.out.println("-> Ahora compararemos la apr�x del �ptimo con el algoritmo online");
		System.out.println("-> Para esto consideraremos permutaciones aleatorias de los trabajos ya mencionados\n");
		
		sched.clean();
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(jobs);
			System.out.println("-Intento n�mero: " + (i + 1));
			
			sched.addOffline(jobs);
			int stressOffline = sched.getMostStressedMachine().getStress();
			System.out.println("M�quina m�s cargada offline-mente: " + stressOffline);
			sched.clean();
			
			sched.addOnline(jobs);
			int stressOnline = sched.getMostStressedMachine().getStress();
			System.out.println("M�quina m�s cargada online-mente: " + stressOnline);
			sched.clean();
			
			if (stressOnline > 2*stressOpt) {
				System.out.println("ERROR: FALL� 2-COMPETITIVIDAD, SE PROCEDE A SALIR DE LA EJECUCION");
				System.exit(1);
			} else {
				System.out.println("Cumpli� la competitividad! :-)");
			}
			
			System.out.println("");
		}
	}
}
