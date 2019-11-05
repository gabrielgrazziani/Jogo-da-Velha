package jogo;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Tabuleiro {
	private int[][] tabuleiro = new int[3][3];
	private int jogadorDaVez;
	public int[][] getTabuleiro() {
		return tabuleiro;
	}
	public Tabuleiro() {
		zerarTabuleiro();
		this.jogadorDaVez = 1;
		
	}
	public int getJogadorDaVez() {
		if(this.jogadorDaVez == 1) {
			return 1;
		}
		else {
			return -1;
		}
	}
	public void setJogadorDaVez(int jogador){
		if(jogador == 1) {
			this.jogadorDaVez = 1;
		}
		else if(jogador == 2) {
			this.jogadorDaVez = -1;
		}
	}
	public void zerarTabuleiro() {
		for (int y = 0; y < tabuleiro.length; y++) {
			for (int x = 0; x < tabuleiro.length; x++) {
				tabuleiro[y][x] = 0;
			}
		}
	}
	public void trocarJogador() {
		//setJogadorDaVez(jogador);
		if(this.jogadorDaVez == -1) {
			this.jogadorDaVez = 1;
		} 
		else {
			this.jogadorDaVez = -1;
		}	
	}
	public boolean jogar(int x,int y) {
		if(this.tabuleiro[y][x] == 0) {
			this.tabuleiro[y][x] = this.jogadorDaVez;
			return true;
		}
		return false;
	}
	public boolean horisontal(int jogador){
		for (int y = 0; y < tabuleiro.length; y++) {
			if(this.tabuleiro[y][0] + 
					this.tabuleiro[y][1] +
					this.tabuleiro[y][2] == 3*jogador) {
				return true;
			}
		}
		return false;
	}
	public boolean vertical(int jogador){ 
		for (int x = 0; x < tabuleiro.length; x++) {
			if(this.tabuleiro[0][x] + 
					this.tabuleiro[1][x] +
					this.tabuleiro[2][x] == 3*jogador) {
				return true;
			}
		}
		return false;
	}
	public boolean diagonal(int jogador){
		if(this.tabuleiro[0][0] + 
				this.tabuleiro[1][1] +
				this.tabuleiro[2][2] == 3*jogador) {
			return true;
		}
		if(this.tabuleiro[0][2] + 
				this.tabuleiro[1][1] +
				this.tabuleiro[2][0] == 3*jogador) {
			return true;
		}
		return false;
	}
	public boolean velha() {
		if(verificar(1) || verificar(2)) {
			return false;
		}
		for (int y = 0; y < tabuleiro.length; y++) {
			for (int x = 0; x < tabuleiro.length; x++) {
				if(this.tabuleiro[y][x] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean verificar(int jogador) { //verifica se o jogado que recebel ganhol 
		return (diagonal(jogador) || vertical(jogador) || horisontal(jogador));
	}
	public char[][] XO() { // retorna o tabuleiro com 'x' E 'O'
		char[][] tabuleiro = new char[3][3];
		for (int y = 0; y < tabuleiro.length; y++) {
			for (int x = 0; x < tabuleiro.length; x++) {
				if(this.tabuleiro[y][x] == 0) {
					tabuleiro[y][x] = ' ';
				}
				else if(this.tabuleiro[y][x] == 1) {
					tabuleiro[y][x] = 'X';
				}
				else {
					tabuleiro[y][x] = 'O';
				}
			}
		}
		return tabuleiro;
	}
}
