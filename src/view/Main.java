package view;

import java.util.concurrent.Semaphore;

import controller.Aeroporto;

public class Main {

	public static void main(String[] args) {

		Semaphore semaforoN = new Semaphore(1);
		Semaphore semaforoS = new Semaphore(1);
		
		for (int idThread = 1; idThread < 13; idThread++) {
			Thread tAero = new Aeroporto(idThread, semaforoN, semaforoS);
			tAero.start();
		}
	}

}
