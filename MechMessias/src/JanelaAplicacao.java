import java.awt.EventQueue;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class JanelaAplicacao {
	static Connection conexao;
	static SpaceCenter centroEspacial;
	static Vessel naveAtual;
	ReferenceFrame parametroDinamico;
	private JFrame frame;
	private JPasswordField senhatxt;

	/**
	 * Lançar a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaAplicacao window = new JanelaAplicacao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criar a aplicação.
	 */
	public JanelaAplicacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 613, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		senhatxt = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, senhatxt, 163, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, senhatxt, -150, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, senhatxt, -154, SpringLayout.EAST, frame.getContentPane());
		senhatxt.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(senhatxt);
		
		JLabel cdg = new JLabel("Senha painel de controle");
		cdg.setForeground(Color.BLACK);
		cdg.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.EAST, cdg, -235, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, senhatxt, 6, SpringLayout.SOUTH, cdg);
		springLayout.putConstraint(SpringLayout.SOUTH, cdg, -186, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, cdg, 65, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(cdg);
		
		JButton liberarcdg = new JButton("Liberar painel");
		liberarcdg.addComponentListener(new ComponentAdapter() {
		});
		springLayout.putConstraint(SpringLayout.NORTH, liberarcdg, 12, SpringLayout.SOUTH, senhatxt);
		springLayout.putConstraint(SpringLayout.EAST, liberarcdg, -173, SpringLayout.EAST, frame.getContentPane());
		liberarcdg.setBackground(Color.LIGHT_GRAY);
		liberarcdg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checarSenha(new String(senhatxt.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Entrada permitida");
					if(e.getSource() == liberarcdg) {
						frame.dispose();
						novaJanela novajanela = new novaJanela();
					}
						
					
				} else {
					JOptionPane.showMessageDialog(null, "Entrada não autorizada!!");
					
				}
			}
		

		public boolean checarSenha(String cdg) {
			return cdg.equals("messiasfoda");
		}
		});
		frame.getContentPane().add(liberarcdg);
	
	}

	public static void entrada() throws IOException, RPCException {
		centroEspacial =  SpaceCenter.newInstance(conexao);
		naveAtual = centroEspacial.getActiveVessel();
		ReferenceFrame streaming = naveAtual.getOrbit().getBody().getReferenceFrame();
		System.out.println("Iniciando teste do jogo");
			while (true) {
				System.out.println(naveAtual.position(streaming));
		}
		
		
	}
	public void painelDeComando() {
	    painelDeComando frame = new painelDeComando();
	    frame.setVisible(true);
	    try {
	        frame.setSelected(true);
	    } catch (java.beans.PropertyVetoException e) {}
	}

	public static void add(painelDeComando pnlcmd) {
		// TODO Auto-generated method stub
		
	}

	
}

