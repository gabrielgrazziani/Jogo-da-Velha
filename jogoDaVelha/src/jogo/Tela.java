package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tela extends JFrame{
	
	Tabuleiro tabuleiro = new Tabuleiro();
	JPanel painel = new JPanel(new GridLayout(3,3,10,10));
	JLabel texto = new JLabel("jogador (X)");
	Botao btnMatriz[][] = new Botao[3][3];
	static JFrame tela;
	int jogadorComecou = 1;
	private int modo = 0;
	
	public void setModo(int modo) {
		this.modo = modo;
	}

	public Tela(int modo) {
		arumarJanela();
		arumarTela();
		setVisible(true);
		this.modo = modo;
		Random gerador = new Random();
		int x = gerador.nextInt(2);
		if(modo != 0 && 1 == 1) {
			this.jogadorComecou = -1;
			trocar(-1);
			jogaMaquina(modo);
			trocar(1);
		}
	}
	
	public void arumarJanela() {
		setTitle("jogo da velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	
	public void limparTela(){
		tabuleiro.zerarTabuleiro();
		limpar();
		if(this.jogadorComecou == tabuleiro.getJogadorDaVez()) {
			trocar(-1 * tabuleiro.getJogadorDaVez());
		}
		else {
			trocar(0);
		}
		this.jogadorComecou = tabuleiro.getJogadorDaVez();
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
			atualizar();
			return true;
		}
		return false;
	}
	public boolean jogaMaquina(int modo) {
		Computador com = new Computador();
		if(com.jogarMaquina(tabuleiro,modo)) {		// joga e ja verifica se a jogada ocorreu
			atualizar();
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
			int opcao = JOptionPane.showConfirmDialog(null,mensagem + "\n\nJogar Novamente", "", JOptionPane.YES_NO_OPTION);
			if(opcao == 0) {
				limparTela();
				if(modo != 0 && tabuleiro.getJogadorDaVez() == -1) {
					jogaMaquina(modo);
					trocar(0);
				}
				return true;
			}
			else {
				tela.setVisible(false);
				Menu.main(null);
			}
		}

		return false;
	}
	
	private void trocar(int t){
			if(t == 0) {
				tabuleiro.trocarJogador();
			}
			else {
				tabuleiro.setJogadorDaVez(t);
			}
			String fraze = "jogador ";
			if(tabuleiro.getJogadorDaVez() == 1) {
				texto.setForeground(Color.green);
				fraze += "(X)";
			}
			else {
				texto.setForeground(Color.red);
				fraze += "(O)";
			}
			texto.setText(fraze);
		}
	public void atualizar(){
		char[][] mat = tabuleiro.XO();
		for (int x = 0; x < this.btnMatriz.length; x++) {
			for (int y = 0; y < btnMatriz.length; y++) {
				btnMatriz[y][x].setText("" + mat[y][x]);
				if(mat[y][x] == 'X') {
					btnMatriz[y][x].setForeground(Color.green);
				}
				else {
					btnMatriz[y][x].setForeground(Color.red);
				}
			}
		}
		//return verificarVenceu(jogador);
	}
	
	public void limpar(){
		for (int x = 0; x < this.btnMatriz.length; x++) {
			for (int y = 0; y < btnMatriz.length; y++) {
				btnMatriz[y][x].setText(null);
			}
		}
	}
	
	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		tela = new Tela(x);
	}
	
	public void arumarTela() {
		add(BorderLayout.NORTH,texto);
		add(BorderLayout.CENTER,painel);
		painel.setBackground(Color.black);
		texto.setFont(new Font("Arial",Font.BOLD,35));
		texto.setForeground(Color.green);
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		for (int x = 0; x < this.btnMatriz.length; x++) {
			for (int y = 0; y < btnMatriz.length; y++) {
				Botao btn = new Botao(x,y);
				btn.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						joga(btn.getX(), btn.getY());				
					}
				});
				btnMatriz[y][x] = btn;
				painel.add(btn);
			}
		}
	}
}
