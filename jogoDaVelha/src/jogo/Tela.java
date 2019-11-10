package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Tela extends JFrame {

	private JPanel contentPane;
	private static Aplicacao apli;
	private static Tela tela;
	private static Botao button2e2 = new Botao();
	private static Botao button1e2 = new Botao();
	private static Botao button0e2 = new Botao();
	private static Botao button2e1 = new Botao();
	private static Botao button1e1 = new Botao();
	private static Botao button0e1 = new Botao();
	private static Botao button2e0 = new Botao();
	private static Botao button1e0 = new Botao();
	private static Botao button0e0 = new Botao();
	private static JLabel lblNewLabel_2 = new JLabel("Modo:");
	private static JLabel lblMostraModo = new JLabel("");
	private static JLabel lblNewLabel_1 = new JLabel("Jogador");
	private static JButton btnReiniciar = new JButton("Reiniciar");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnMudarModo = new JMenu("Mudar modo");
	private final JLabel label = new JLabel("");
	private final JMenuItem mntmNewMenuItem = new JMenuItem("Jogador contra Jogador");
	private final JMenuItem mntmNewMenuItem_1 = new JMenuItem("Facil (Contra a maquina)");
	private final JMenuItem mntmNewMenuItem_2 = new JMenuItem("Medio (Contra a maquina)");
	private final JMenuItem mntmNewMenuItem_3 = new JMenuItem("Dificil (Contra a maquina)");
	private final JMenuItem mntmNewMenuItem_4 = new JMenuItem("Boa Sorte (Contra a maquina)");
	public static void main(String[] args) {
		try {
			tela = new Tela();
			tela.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}
	}
	
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
			lblMostraModo.setText("X1");
			break;
		case 1:
			lblMostraModo.setText("Facil");
			break;
		case 2:
			lblMostraModo.setText("Medio");
			break;
		case 3:
			lblMostraModo.setText("Dificil");
			break;
		case 4:
			lblMostraModo.setText("Boa Sorte");
			break;	
		default:
			lblMostraModo.setText("");
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
	

	/**
	 * Create the frame.
	 */

	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 430);
		
		setJMenuBar(menuBar);
		mnMudarModo.setHorizontalAlignment(SwingConstants.LEFT);
		
		menuBar.add(mnMudarModo);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.setModo(0);
			}
		});
		
		mnMudarModo.add(mntmNewMenuItem);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.setModo(1);
			}
		});
		
		mnMudarModo.add(mntmNewMenuItem_1);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.setModo(2);
			}
		});
		
		mnMudarModo.add(mntmNewMenuItem_2);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.setModo(3);
			}
		});
		
		mnMudarModo.add(mntmNewMenuItem_3);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apli.setModo(4);
			}
		});
		
		mnMudarModo.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		panel.add(lblNewLabel_1);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		
		panel.add(lblNewLabel_2);
		
		lblMostraModo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMostraModo);
		
		panel.add(label);
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
		
		apli = new Aplicacao(0);
		atualizar();
	}
	
}
