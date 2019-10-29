package jogo;

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
		return jogadorDaVez;
	}
	public void setJogadorDaVez(int jogador){
		this.jogadorDaVez = jogador;
	}
	public void zerarTabuleiro() {
		for (int y = 0; y < tabuleiro.length; y++) {
			for (int x = 0; x < tabuleiro.length; x++) {
				tabuleiro[y][x] = 0;
			}
		}
	}
	public void trocarJogador() {
		if(this.jogadorDaVez == 2) {
			this.jogadorDaVez = 0;
		}
		this.jogadorDaVez++;
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
			if(this.tabuleiro[y][0] == jogador 
					&& this.tabuleiro[y][1] == jogador
					&& this.tabuleiro[y][2] == jogador) {
				return true;
			}
		}
		return false;
	}
	public boolean vertical(int jogador){ 
		for (int x = 0; x < tabuleiro.length; x++) {
			if(this.tabuleiro[0][x] == jogador 
					&& this.tabuleiro[1][x] == jogador
					&& this.tabuleiro[2][x] == jogador) {
				return true;
			}
		}
		return false;
	}
	public boolean diagonal(int jogador){
		if(this.tabuleiro[0][0] == jogador 
				&& this.tabuleiro[1][1] == jogador
				&& this.tabuleiro[2][2] == jogador) {
			return true;
		}
		if(this.tabuleiro[0][2] == jogador 
				&& this.tabuleiro[1][1] == jogador
				&& this.tabuleiro[2][0] == jogador) {
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
