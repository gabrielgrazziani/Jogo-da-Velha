package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tela extends JFrame{
	
	Tabuleiro tabuleiro = new Tabuleiro();
	JPanel painel = new JPanel(new GridLayout(3,3,10,10));
	JLabel texto = new JLabel("jogador " + tabuleiro.getJogadorDaVez() + "° (X)");
	
	Botao bot[][] = new Botao[3][3];
	
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
	
	public static void main(String[] args) {
		Tela tela = new Tela();
	}
	
	
	public void arumarTela() {
		add(BorderLayout.CENTER,painel);
		add(BorderLayout.NORTH,texto);
		painel.setBackground(Color.black);
		texto.setFont(new Font("Arial",Font.BOLD,35));
		texto.setForeground(Color.green);
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		for (int x = 0; x < this.bot.length; x++) {
			for (int y = 0; y < bot.length; y++) {
				Botao bot = new Botao(tabuleiro,x,y,texto);
				this.bot[y][x] = bot;
				painel.add(bot);
			}
			
		}
	}
}
