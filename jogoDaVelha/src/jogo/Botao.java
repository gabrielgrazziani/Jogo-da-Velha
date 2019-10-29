package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Botao extends JButton{
	private int posicaoX;
	private int posicaoY;
	public Botao(Tabuleiro tabuleiro,int x,int y,JLabel text){
		setBackground(Color.white);
		this.posicaoX = x;
		this.posicaoY = y;
		setText("" + tabuleiro.XO()[y][x]);
		setFont(new Font("Arial",Font.BOLD,100));
		setForeground(Color.BLACK);
		setHorizontalAlignment(SwingConstants.CENTER);
		addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				int jogador = tabuleiro.getJogadorDaVez();
				if(tabuleiro.jogar(x, y)) {		// joga e ja verifica se a jogada ocorreu
					colocarSimbolo();
					if(tabuleiro.verificar(jogador)) {
						char y;
						if(tabuleiro.getJogadorDaVez() == 1) {
							y = 'X';
						}
						else {
							y = 'O';
						}
						JOptionPane.showMessageDialog(null, "Jogador " + jogador + " (" + y +") Venceu!");
						//int opcao = JOptionPane.showConfirmDialog(null, "Jogar Novamente", "", JOptionPane.YES_NO_OPTION);
						System.exit(0);
						
					}
					else if(tabuleiro.velha()) {
						JOptionPane.showMessageDialog(null, "O jogo deu velha!");
						System.exit(0);
					}
					trocar();
				}
			}
			private void trocar(){
				tabuleiro.trocarJogador();
				String texto = "jogador " + tabuleiro.getJogadorDaVez() + " ";
				if(tabuleiro.getJogadorDaVez() == 1) {
					text.setForeground(Color.green);
					texto += "(X)";
				}
				else {
					text.setForeground(Color.red);
					texto += "(O)";
				}
				text.setText(texto);
			}
			private void colocarSimbolo(){
				if(tabuleiro.getJogadorDaVez() == 1) {
					setForeground(Color.green);
				}
				else {
					setForeground(Color.red);
				}
				setText("" + tabuleiro.XO()[y][x]);
			}
		});
	}
}
