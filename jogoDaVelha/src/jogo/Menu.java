package jogo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;
	public static JFrame frame; 
	
	private void jogo(int modo) {
		frame.setVisible(false);
		new Tela2(modo);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Menu();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("Jogo Da Velha");
		contentPane.add(label);
		label.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JButton button = new JButton("Um contontra UM");
		button.setForeground(new Color(0, 0, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo(0);
			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("Contra a Maquina");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton button_1 = new JButton("Facil");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo(1);
			}
		});
		button_1.setBackground(Color.GREEN);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Medio");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo(2);
			}
		});
		button_2.setBackground(Color.YELLOW);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Imposs\u00EDvel");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo(4);
			}
		});
		
		JButton btnDificil = new JButton("Dificil");
		btnDificil.setBackground(Color.ORANGE);
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo(3);
			}
		});
		contentPane.add(btnDificil);
		button_3.setBackground(Color.RED);
		contentPane.add(button_3);
	}
}
