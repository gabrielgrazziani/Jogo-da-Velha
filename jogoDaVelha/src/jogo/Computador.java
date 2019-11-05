package jogo;

import java.util.Random;

public class Computador {
	private Tabuleiro tabuleiro;
	private int[][] tab;
	private int maquina;
	private int oponente;
	private int x;
	private int y;
	public boolean jogar(int x,int y) {
		if(tabuleiro.jogar(x, y)) {
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}
	public boolean jogarMaquina(Tabuleiro x) {
		arumar(x);
	 	return jogadaObivia() || lateral() || aleatotio();
	}

	private void arumar(Tabuleiro tab){
		this.maquina = tab.getJogadorDaVez();
		if(this.maquina == 2) {
			this.oponente = 1;
			this.maquina = -1;
		}
		else {
			this.oponente = -1;
			this.maquina = 1;
		}
		this.tab = tab.getTabuleiro();
		this.tabuleiro = tab;
	}
	private boolean jogadaObivia() {
		
		return horisontal(maquina) || vertical(maquina) || diagonal(maquina)
				|| horisontal(oponente) || vertical(oponente) || diagonal(oponente);
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
		for (int i = 0; i < 2;i += 2) {
			for (int j = 0; j < 2; j += 2) {
				if(tab[i][j] == maquina) {
					if(tab[j][i] == 0) {
						return tabuleiro.jogar(j, i);
					}
					else if(tab[i][i] == 0) {
						return tabuleiro.jogar(i, i);
					}
					else {
						return tabuleiro.jogar(j, j);
					}
				}
			}
		}
		for (int i = 0; i < 2;i += 2) {
			for (int j = 0; j < 2; j += 2) {
				if(tab[i][j] == 0) {
					return tabuleiro.jogar(i, j);
				}
			}
		}
		return false;
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
					return tabuleiro.jogar(y, x);
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
			for (int i = 0; i < 3; i++) {
				for (int j = 2; j >= 0; j--) {
					if(tab[i][j] == 0) {
						return tabuleiro.jogar(j, i);
					}
				}
			}
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
