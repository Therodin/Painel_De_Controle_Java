import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

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
import javax.swing.ImageIcon;
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
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;

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
		springLayout.putConstraint(SpringLayout.NORTH, senhatxt, 118, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, senhatxt, 163, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, senhatxt, -150, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, senhatxt, -154, SpringLayout.EAST, frame.getContentPane());
		senhatxt.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(senhatxt);
		
		JLabel cdg = new JLabel("Insira a senha");
		springLayout.putConstraint(SpringLayout.NORTH, cdg, 65, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, cdg, -6, SpringLayout.NORTH, senhatxt);
		cdg.setFont(new Font("consolas", Font.ITALIC, 15));
		cdg.setForeground(Color.BLACK);
		cdg.setBackground(Color.WHITE);
		frame.getContentPane().add(cdg);
		
		JButton liberarcdg = new JButton("Liberar painel");
		springLayout.putConstraint(SpringLayout.NORTH, liberarcdg, 160, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, liberarcdg, -270, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, liberarcdg, 42, SpringLayout.SOUTH, senhatxt);
		springLayout.putConstraint(SpringLayout.EAST, liberarcdg, 0, SpringLayout.EAST, senhatxt);
		liberarcdg.addComponentListener(new ComponentAdapter() {
		});
		liberarcdg.setBackground(Color.LIGHT_GRAY);
		liberarcdg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checarSenha(new String(senhatxt.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Entrada permitida");
					if(e.getSource() == liberarcdg) {
						frame.dispose();
						try {
							novaJanela novajanela = new novaJanela();
						} catch (RPCException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
		
		JLabel lblMsc = new JLabel("MSC");
		springLayout.putConstraint(SpringLayout.WEST, cdg, 47, SpringLayout.EAST, lblMsc);
		springLayout.putConstraint(SpringLayout.EAST, cdg, 166, SpringLayout.EAST, lblMsc);
		springLayout.putConstraint(SpringLayout.NORTH, lblMsc, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMsc, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblMsc, -51, SpringLayout.NORTH, senhatxt);
		springLayout.putConstraint(SpringLayout.EAST, lblMsc, -403, SpringLayout.EAST, frame.getContentPane());
		ImageIcon icone = new ImageIcon(this.getClass().getResource("/foguete.png").getFile());
		lblMsc.setIcon(icone);
		frame.getContentPane().add(lblMsc);
		
		JLabel botaoicone = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, botaoicone, 0, SpringLayout.NORTH, senhatxt);
		springLayout.putConstraint(SpringLayout.WEST, botaoicone, 135, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, botaoicone, -6, SpringLayout.SOUTH, senhatxt);
		springLayout.putConstraint(SpringLayout.EAST, botaoicone, -6, SpringLayout.WEST, senhatxt);
		ImageIcon botic = new ImageIcon(this.getClass().getResource("/botaoicone.png").getFile());
		botaoicone.setIcon(botic);
		frame.getContentPane().add(botaoicone);
	
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

