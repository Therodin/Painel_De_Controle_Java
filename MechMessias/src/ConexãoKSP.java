import java.io.IOException;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;


public class ConexãoKSP {
	static Connection conexao;
	static SpaceCenter centroEspacial;
	static Vessel naveAtual;
	ReferenceFrame parametroDinamico;
	
	public static void main(String[] args) throws IOException, RPCException {
		conexao = Connection.newInstance("Conexão concedida");
		centroEspacial =  SpaceCenter.newInstance(conexao);
		naveAtual = centroEspacial.getActiveVessel();
		ReferenceFrame streaming = naveAtual.getOrbit().getBody().getReferenceFrame();
		System.out.println("Iniciando teste do jogo");
			while (true) {
				System.out.println(naveAtual.position(streaming));
		}
		
	}

}
