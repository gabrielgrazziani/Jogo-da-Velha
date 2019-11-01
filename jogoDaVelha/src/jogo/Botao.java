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
	private int X;
	private int Y;
	public Botao(int x,int y){
		this.setX(x);
		this.setY(y);
		setBackground(Color.white);
		setText(null);
		setFont(new Font("Arial",Font.BOLD,100));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	public int getX() {
		return X;
	}
	public void setX(int X) {
		this.X = X;
	}
	public int getY() {
		return Y;
	}
	public void setY(int Y) {
		this.Y = Y;
	}
}
