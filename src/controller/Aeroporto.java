package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread {

	private int idThread;
	private Semaphore semaforoN;
	private Semaphore semaforoS;
	Random rdm = new Random();
	int tempo = 0;

	public Aeroporto(int idThread, Semaphore semaforoN, Semaphore semaforoS) {
		this.idThread = idThread;
		this.semaforoN = semaforoN;
		this.semaforoS = semaforoS;

	}

	@Override
	public void run() {
		String[] pistas = { "Pista Norte", "Pista Sul" };

		Random rdmPista = new Random();
		int indice = rdmPista.nextInt(pistas.length);
		String pista = pistas[indice];
		if (indice == 0) {
			try {
				semaforoN.acquire();
				processoDecolagem(pista);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoN.release();
			}
		} else if (indice == 1) {
			try {
				semaforoS.acquire();
				processoDecolagem(pista);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoS.release();
			}
		}

	}

	private void processoDecolagem(String pista) {

			// tempo de manobra
			tempo = rdm.nextInt(4001) + 3000;
			System.out.println("Inicio de processo de decolagem #" + idThread+" pela " + pista);
			System.out.println("#" + idThread + " manobrando");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// tempo de taxiamento
			tempo = rdm.nextInt(5001) + 5000;
			System.out.println("#" + idThread + " taxiando");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// tempo de decolagem
			tempo = rdm.nextInt(3001) + 1000;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#" + idThread + " decolou");

			// tempo de afastamento
			tempo = rdm.nextInt(5001) + 3000;
			System.out.println("#" + idThread + " realizou afastamento da área" +
					"\nProcesso de decolagem #" + idThread + " pela " + pista + " finalizado");
			try {
			sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Processo de decolagem da " + pista + " finalizado");
	}
}