package jogo;

import java.util.Random;

import javax.swing.JOptionPane;

public class Computador {
	private Tabuleiro tabuleiro;
	private int[][] tab;
	private int maquina;
	private int oponente;
	public boolean jogarMaquina(Tabuleiro x,int modo) {
		arumar(x);
		boolean ok = false;
		if(modo >= 1 && jogadaObivia() ) {
			ok = true;
		}
		else if (modo >= 3 && contraMedida()){
			ok = true;
		}
		else if (modo >= 2 && lateral()){
			ok = true;
		}
		else if(modo >= 1 && aleatotio()) {
			ok = true;
		}
	 	return ok;
	}
	public static void main(String[] args) {
		new Tela(3);
	}
	
	private void arumar(Tabuleiro tab){
		this.maquina = tab.getJogadorDaVez();
		if(this.maquina == -1) {
			this.oponente = 1;
		}
		else {
			this.oponente = -1;
		}
		this.tab = tab.getTabuleiro();
		this.tabuleiro = tab;
	}
	private boolean jogadaObivia() {
		boolean ok = false;
		if(horisontal(maquina) || vertical(maquina) || diagonal(maquina)) {
			ok = true;
		}
		else if( horisontal(oponente) || vertical(oponente) || diagonal(oponente)) {
			ok = true;
		}
		return ok;
	}
	private boolean contraMedida() {
		int jogador = tabuleiro.getJogadorDaVez();
		if(lateralMarcada(jogador*-1) && !lateralMarcada(jogador) && !(tab[1][1] == jogador)) {
			if(tab[1][1] == 0) {
				return tabuleiro.jogar(1, 1);
			}
		}
		else if (tab[1][1] == jogador){
			if(tab[0][1] == 0) {
				return tabuleiro.jogar(1,0);
			}
			else if (tab[1][0] == 0) {
				return tabuleiro.jogar(0,1);
			}
			else if (tab[2][1] == 0) {
				return tabuleiro.jogar(1,2);			
			}
			else if (tab[1][2] == 0) {
				return tabuleiro.jogar(2,1);
			}
		}
		return false;
	}
	
	private boolean lateralMarcada(int jogador) {
		if(tab[0][0] + 
				tab[2][0] +
				tab[0][2] +
				tab[1][2]== 1*jogador) {
			return true;
		}
		return false;
	}
	private boolean horisontal(int jogador){
		for (int y = 0; y < tab.length; y++) {
			if(tab[y][0] + tab[y][1] + tab[y][2] == 2*jogador) {
				for (int i = 0; i < 3; i++) {
					if(tab[y][i] == 0) {
						return tabuleiro.jogar(i, y);
					}
				}
			}
		}
		return false;
	}
	private boolean lateral(){
		for (int x = 0; x < 3;x += 2) {
			for (int y = 0; y < 3; y += 2) {
				if(tab[y][x] == maquina) {
					if(tab[oposto(y)][oposto(x)] == 0) {
						return tabuleiro.jogar(oposto(x), oposto(y));
					}
					else if(tab[y][oposto(x)] == 0) {
						return tabuleiro.jogar(oposto(x), y);
					}
					else {
						return tabuleiro.jogar(x, oposto(y));
					}
				}
			}
		}
		for (int x = 0; x < 3;x += 2) {
			for (int y = 0; y < 3; y += 2) {
				if(tab[y][x] == 0) {
					return tabuleiro.jogar(x, y);
				}
			}
		}
		return false;
	}
	private int oposto(int x){
		x += 2;
		if(x > 2) {
			x = 0;
		}
		return x;
	}
	private boolean aleatotio(){
		Random gerador = new Random();
		final int X = gerador.nextInt(3);
		int x = X;
		final int Y = gerador.nextInt(3);
		int y = Y; 
		do {
			do {
				if(tab[y][x] == 0) {
					return tabuleiro.jogar(x, y);
				}
				y++;
				if(y >= 3) {
					y = 0;
				}
			}while(Y != y);
			x++;
			if(x >= 3) {
				x = 0;
			}
		}while(X != x);
		return false;
	}
	private boolean vertical(int jogador){ 
		for (int x = 0; x < tab.length; x++) {
			if(tab[0][x] +
					tab[1][x] +
					tab[2][x] == 2*jogador) {
				for (int i = 0; i < 3; i++) {
					if(tab[i][x] == 0) {
						return tabuleiro.jogar(x, i);
					}
				}
			}
		}
		return false;
	}
	private boolean diagonal(int jogador){
		if(tab[0][0] + 
				tab[1][1] +
				tab[2][2] == 2*jogador) {
			for (int i = 0; i < 3; i++) {
				if(tab[i][i] == 0) {
					return tabuleiro.jogar(i, i);
				}
			}
		}
		if(tab[0][2] + 
				tab[1][1] +
				tab[2][0] == 2*jogador) {
			int y = 0;
			for (int x = 2; x >= 0; x--) {
				if(tab[y][x] == 0) {
					return tabuleiro.jogar(x, y);
				}
				y++;
			}
		}
		return false;
	}	
}
