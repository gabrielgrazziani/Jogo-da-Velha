package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tela2 extends JFrame{
	
	Tabuleiro tabuleiro = new Tabuleiro();
	JPanel painel = new JPanel(new GridLayout(3,3,10,10));
	JLabel texto = new JLabel("jogador " + tabuleiro.getJogadorDaVez() + "° (X)");
	Botao btnMatriz[][] = new Botao[3][3];
	
	public Tela2() {
		arumarJanela();
		arumarTela();
		setVisible(true);
		
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
		tabuleiro.setJogadorDaVez(2);
		trocar();
	}
	
	public void joga(int x,int y){
		int jogador = tabuleiro.getJogadorDaVez();
		jogaJogador(x,y);
		if(!verificarVenceu(jogador)) {
			jogaMaquina();
			verificarVenceu(jogador);
		}
	}
	
	public boolean jogaJogador(int x,int y){
		if(tabuleiro.jogar(x, y)) {		// joga e ja verifica se a jogada ocorreu
			atualizar();
			return true;
		}
		return false;
	}
	public void jogaMaquina() {
		Computador com = new Computador();
		if(com.jogarMaquina(tabuleiro)) {		// joga e ja verifica se a jogada ocorreu
			atualizar();
		}
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
				mensagem = "Jogador " + jogador + " (" + letra +") Venceu!";
			}
			else {
				mensagem = "O jogo deu velha!";
			}
			int opcao = JOptionPane.showConfirmDialog(null,mensagem + "\n\nJogar Novamente", "", JOptionPane.YES_NO_OPTION);
			if(opcao == 0) {
				limparTela();
				return true;
			}
			else {
				System.exit(0);
			}
		}
		trocar();
		return false;
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
		if(tabuleiro.getJogadorDaVez() == 2) {
			btnMatriz[y][x].setForeground(Color.green);
		}
		else {
			btnMatriz[y][x].setForeground(Color.red);
		}
		btnMatriz[y][x].setText("" + mat[y][x]);
	}
	public void atualizar(){
		int jogador = tabuleiro.getJogadorDaVez();
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
		Tela2 tela = new Tela2();
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
