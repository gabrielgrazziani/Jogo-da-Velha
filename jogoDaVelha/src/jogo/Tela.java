package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tela extends JFrame{
	
	Tabuleiro tabuleiro = new Tabuleiro();
	JPanel painel = new JPanel(new GridLayout(3,3,10,10));
	JLabel texto = new JLabel("jogador " + tabuleiro.getJogadorDaVez() + "° (X)");
	Botao btnMatriz[][] = new Botao[3][3];
	
	public Tela() {
		arumarTela();
		arumarJanela();
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
		tabuleiro.setJogadorDaVez(1);
		trocar();
	}
	
	public void joga(int x,int y){
		int jogador = tabuleiro.getJogadorDaVez();
		if(tabuleiro.jogar(x, y)) {		// joga e ja verifica se a jogada ocorreu
			colocarSimbolo(x,y);
			verificarVenceu(jogador);
			trocar();
		}	
	}
	private void verificarVenceu(int jogador){
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
				mensagem = "Jogador " + jogador + " (" + letra +") Venceu!";
			}
			else {
				mensagem = "O jogo deu velha!";
			}
			int opcao = JOptionPane.showConfirmDialog(null,mensagem + "\n\nJogar Novamente", "", JOptionPane.YES_NO_OPTION);
			if(opcao == 0) {
				limparTela();
			}
			else {
				System.exit(0);
			}
		}	
	}
	
	private void trocar(){
			int[][] mat = tabuleiro.getTabuleiro();
			tabuleiro.trocarJogador();
			String fraze = "jogador " + tabuleiro.getJogadorDaVez() + " ";
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
	private void colocarSimbolo(int x,int y){
		char[][] mat = tabuleiro.XO();
		if(tabuleiro.getJogadorDaVez() == 1) {
			btnMatriz[y][x].setForeground(Color.green);
		}
		else {
			btnMatriz[y][x].setForeground(Color.red);
		}
		btnMatriz[y][x].setText("" + mat[y][x]);
	}
	public void atualizar(){
		int[][] mat = tabuleiro.getTabuleiro();
		for (int x = 0; x < this.btnMatriz.length; x++) {
			for (int y = 0; y < btnMatriz.length; y++) {
				btnMatriz[y][x].setText("" + mat[y][x]);
			}
		}
	}
	
	public void limpar(){
		for (int x = 0; x < this.btnMatriz.length; x++) {
			for (int y = 0; y < btnMatriz.length; y++) {
				btnMatriz[y][x].setText(null);
			}
		}
	}
	
	public static void main(String[] args) {
		Tela tela = new Tela();
	}
	
	public void arumarTela() {
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
				this.btnMatriz[y][x] = btn;
				painel.add(btn);
			}
		}
		add(BorderLayout.NORTH,texto);
		add(BorderLayout.CENTER,painel);
	}
}
