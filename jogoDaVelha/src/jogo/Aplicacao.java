package jogo;


import java.util.Random;
import javax.swing.JOptionPane;


public class Aplicacao {
	private Tabuleiro tabuleiro = new Tabuleiro();
	private int jogadorComecou = 1;
	private int modo = 0;
	
	public int getModo() {
		return modo;
	}
	
	public int getJogador() {
		return tabuleiro.getJogadorDaVez();
	}

	
	
	public void setModo(int modo) {
		this.modo = modo;
		reiniciar();
	}
	

	public Aplicacao(int modo) {
		this.modo = modo;
		Random gerador = new Random();
		int x = gerador.nextInt(2);
		if(modo != 0 && 1 == x) {
			this.jogadorComecou = -1;
			trocar(-1);
			jogaMaquina(modo);
			trocar(1);
		}
	}
	public String xo(int x,int y) {
		return "" + tabuleiro.XO()[y][x];
	}
	
	private void zerarTabuleiro(){
		tabuleiro.zerarTabuleiro();
		if(this.jogadorComecou == tabuleiro.getJogadorDaVez()) {
			trocar(-1 * tabuleiro.getJogadorDaVez());
		}
		else {
			trocar(0);
		}
		this.jogadorComecou = tabuleiro.getJogadorDaVez();
		Tela.atualizar();
	}
	
	public void joga(int x,int y){
		if(modo == 0) {
			jogandorContraJogador(x, y);
		}
		else {
			jogandorContraMaquina(x, y, this.modo);
		}
	}
	
	private Boolean jogandorContraJogador(int x,int y) {
		boolean ok = jogaJogador(x,y);
		if(ok && !(verificarVenceu(tabuleiro.getJogadorDaVez()))) {
			trocar(0);
		}
		return ok;
	}
	
	private boolean jogandorContraMaquina(int x,int y,int modo) {
		boolean ok = jogaJogador(x,y);
		if(!verificarVenceu(tabuleiro.getJogadorDaVez()) && ok) {
			trocar(0);
			ok = jogaMaquina(modo);
			if(!verificarVenceu(tabuleiro.getJogadorDaVez()) && ok) {
				trocar(0);
			}
			return true;
		}
		return false;
	}
	
	private boolean jogaJogador(int x,int y){
		if(tabuleiro.jogar(x, y)) {		// joga e ja verifica se a jogada ocorreu
			return true;
		}
		return false;
	}
	private boolean jogaMaquina(int modo) {
		Computador com = new Computador();
		if(com.jogarMaquina(tabuleiro,modo)) {		// joga e ja verifica se a jogada ocorreu
			return true;
		}
		return false;
	}
	private boolean verificarVenceu(int jogador){
		if(tabuleiro.verificar(jogador) || tabuleiro.velha()) {
			String mensagem;
			if(tabuleiro.verificar(jogador)) {
				char letra;
				if(tabuleiro.getJogadorDaVez() == 1) {
					letra = 'X';
				}
				else {
					letra = 'O';
				}
				mensagem = "Jogador (" + letra +") Venceu!";
			}
			else {
				mensagem = "O jogo deu velha!";
			}
			Tela.atualizar();
			int opcao = JOptionPane.showConfirmDialog(null,mensagem + "\n\nJogar Novamente", "", JOptionPane.YES_NO_OPTION);
			if(opcao == 0) {
				reiniciar();
				return true;
			}
			else {
				System.exit(0);
			}
		}
		return false;
	}
	public void reiniciar(){
		zerarTabuleiro();
		if(modo != 0 && tabuleiro.getJogadorDaVez() == -1) {
			jogaMaquina(modo);
			trocar(0);
		}
		Tela.atualizar();
	}
	
	private void trocar(int t){
			if(t == 0) {
				tabuleiro.trocarJogador();
			}
			else {
				tabuleiro.setJogadorDaVez(t);
			}
		}
}
