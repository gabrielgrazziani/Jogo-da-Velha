package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela2 extends JFrame {

	private JPanel contentPane;
	private static Aplicacao apli;
	private static Tela2 tela;
	static Botao2 button2e2 = new Botao2();
	static Botao2 button1e2 = new Botao2();
	static Botao2 button0e2 = new Botao2();
	static Botao2 button2e1 = new Botao2();
	static Botao2 button1e1 = new Botao2();
	static Botao2 button0e1 = new Botao2();
	static Botao2 button2e0 = new Botao2();
	static Botao2 button1e0 = new Botao2();
	static Botao2 button0e0 = new Botao2();
	static JButton btnMenu = new JButton("Menu");
	static JLabel lblNewLabel_2 = new JLabel("");
	static JLabel lblNewLabel = new JLabel("Modo:  ");
	static JLabel lblNewLabel_1 = new JLabel("Jogador");
	static JButton btnReiniciar = new JButton("Reiniciar");
	/**
	 * Launch the application.
	 */
	
	public static void atualizar() {
		button2e2.setText(apli.xo(2,2));
		button1e2.setText(apli.xo(1,2));
		button0e2.setText(apli.xo(0,2));
		button2e1.setText(apli.xo(2,1));
		button1e1.setText(apli.xo(1,1));
		button0e1.setText(apli.xo(0,1));
		button2e0.setText(apli.xo(2,0));
		button1e0.setText(apli.xo(1,0));
		button0e0.setText(apli.xo(0,0));
		switch (apli.getModo()) {
		case 0:
			lblNewLabel_2.setText("X1");
			break;
		case 1:
			lblNewLabel_2.setText("Facil");
			break;
		case 2:
			lblNewLabel_2.setText("Medio");
			break;
		case 3:
			lblNewLabel_2.setText("Dificil");
			break;
		case 4:
			lblNewLabel_2.setText("Imposs\u00EDvel");
			break;	
		default:
			lblNewLabel_2.setText("");
		}
		switch (apli.getJogador()) {
		case 1:
			lblNewLabel_1.setText("Jogador (X)");
			lblNewLabel_1.setForeground(Color.green);
			break;
		case -1:
			lblNewLabel_1.setText("Jogador (O)");
			lblNewLabel_1.setForeground(Color.red);
		}	
	}
	
	public static void menu() {
		tela.setVisible(false);
		Menu.main(null);
	}

	/**
	 * Create the frame.
	 */
	public Tela2(int x) {
		tela = new Tela2();
		apli = new Aplicacao(x);
		tela.setVisible(true);
		atualizar();
	}
	
	public Tela2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		panel.add(lblNewLabel_1);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel);
			
		panel.add(lblNewLabel_2);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu();
			}
		});
		
		panel.add(btnMenu);
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.reiniciar();
			}
		});
		
		btnReiniciar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnReiniciar.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnReiniciar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		button0e0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(0, 0);
				atualizar();
			}
		});
		panel_1.add(button0e0);
		
		button1e0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(1, 0);
				atualizar();
			}
		});
		panel_1.add(button1e0);
		
		button2e0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(2, 0);
				atualizar();
			}
		});
		panel_1.add(button2e0);
		
		button0e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(0, 1);
				atualizar();
			}
		});
		panel_1.add(button0e1);
		
		button1e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(1,1);
				atualizar();
			}
		});
		panel_1.add(button1e1);
		
		button2e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(2,1);
				atualizar();
			}
		});
		panel_1.add(button2e1);
		
		button0e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(0,2);
				atualizar();
			}
		});
		panel_1.add(button0e2);
		
		button1e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(1,2);
				atualizar();
			}
		});
		panel_1.add(button1e2);
		
		button2e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.joga(2,2);
				atualizar();
			}
		});
		panel_1.add(button2e2);
	}

}
