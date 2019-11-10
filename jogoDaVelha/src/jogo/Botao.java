package jogo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Botao extends JButton{
	public Botao(){
		setBackground(Color.white);
		setText(null);
		setFont(new Font("Arial",Font.BOLD,100));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		if(text != null && text.equals("X")) {
			setForeground(Color.green);
		}
		else {
			setForeground(Color.red);
		}
	}
}
