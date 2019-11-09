package jogo;


import java.util.Random;
import javax.swing.JOptionPane;


public class Aplicacao {
	Tabuleiro tabuleiro = new Tabuleiro();
	private int jogadorComecou = 1;
	
	public int getModo() {
		return modo;
	}
	
	public int getJogador() {
		return tabuleiro.getJogadorDaVez();
	}

	private int modo = 0;
	
	public void setModo(int modo) {
		this.modo = modo;
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
	
	public void zerarTabuleiro(){
		tabuleiro.zerarTabuleiro();
		if(this.jogadorComecou == tabuleiro.getJogadorDaVez()) {
			trocar(-1 * tabuleiro.getJogadorDaVez());
		}
		else {
			trocar(0);
		}
		this.jogadorComecou = tabuleiro.getJogadorDaVez();
		Tela2.atualizar();
	}
	
	public void joga(int x,int y){
		boolean ok = false;
		if(modo == 0) {
			ok = jogandorContraJogador(x, y);
		}
		else {
			ok = jogandorContraMaquina(x, y, this.modo);
		}
	}
	
	public Boolean jogandorContraJogador(int x,int y) {
		boolean ok = jogaJogador(x,y);
		if(ok && !(verificarVenceu(tabuleiro.getJogadorDaVez()))) {
			trocar(0);
		}
		return ok;
	}
	
	public boolean jogandorContraMaquina(int x,int y,int modo) {
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
	
	public boolean jogaJogador(int x,int y){
		if(tabuleiro.jogar(x, y)) {		// joga e ja verifica se a jogada ocorreu
			return true;
		}
		return false;
	}
	public boolean jogaMaquina(int modo) {
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
			Tela2.atualizar();
			int opcao = JOptionPane.showConfirmDialog(null,mensagem + "\n\nJogar Novamente", "", JOptionPane.YES_NO_OPTION);
			if(opcao == 0) {
				reiniciar();
				return true;
			}
			else {
				zerarTabuleiro();
				Tela2.menu();
				//Menu.main(null);
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
		Tela2.atualizar();
	}
	
	public void trocar(int t){
			if(t == 0) {
				tabuleiro.trocarJogador();
			}
			else {
				tabuleiro.setJogadorDaVez(t);
			}
		}
}
