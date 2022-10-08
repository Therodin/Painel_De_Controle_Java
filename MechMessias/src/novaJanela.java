import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import krpc.client.Connection;


public class novaJanela {
	static Connection conexao;
	static SpaceCenter centroEspacial;
	static Vessel naveAtual;
	ReferenceFrame parametroDinamico;
	
	JFrame frame = new JFrame();
	JButton botao1 = new JButton("Altura");
	JButton btln = new JButton("Conectar");
	JTextField prntaltr = new JTextField();
	
	novaJanela(){
		
		
		prntaltr.setBounds(0, 100, 1000, 1000);
		prntaltr.setSize(1000, 35);
		frame.add(prntaltr);
		
		botao1.setBounds(0, 0, 200, 100);
		botao1.setSize(100, 80);
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btln.setBounds(100, 0, 200, 100);
		btln.setSize(100, 80);
		btln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexao = Connection.newInstance("Controladores");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Não foi possível conectar-se à base");
				}
			}
		});
		
		
		frame.add(btln);
		frame.add(botao1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		
	}

}
