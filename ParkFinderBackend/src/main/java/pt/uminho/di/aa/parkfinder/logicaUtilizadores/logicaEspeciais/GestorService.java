package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;

import java.util.List;

public interface GestorService {

	/**
	 * 
	 * @param id_parque
	 */
	Estatisticas verEstatisticasParque(int id_parque);

	/**
	 * 
	 * @param id_parque
	 * @param tipoLugar
	 * @param precario
	 */
	void adicionarPrecario(int id_parque, TipoLugarEstacionamento tipoLugar, Precario precario);

	List<Parque> listarMeusParques();

	/**
	 * 
	 * @param id_parque
	 * @param tipoLugar
	 */
	void removerPrecario(int id_parque, TipoLugarEstacionamento tipoLugar);

	List<Administrador> listarMeusAdministradores();

	/**
	 * 
	 * @param a
	 * @param ids_parques
	 */
	void criarAdmin(Administrador a, List<Integer> ids_parques);

	/**
	 * 
	 * @param id_admin
	 */
	void removerAdmin(int id_admin);

	/**
	 * 
	 * @param id_admin
	 * @param ids_parques
	 */
	void removerPermissaoAdminSobreParques(int id_admin, List<Integer> ids_parques);

	/**
	 * 
	 * @param id_parque
	 * @param newInfo
	 */
	boolean alterarInformacoesParque(int id_parque, Parque newInfo);

	/**
	 * 
	 * @param id_parque
	 * @param disponivel
	 */
	void alterarEstadoDisponivelDeParque(int id_parque, boolean disponivel);

	/**
	 * 
	 * @param ids_parques
	 * @param id_admin
	 */
	void adicionarParquesAAdmin(List<Integer> ids_parques, int id_admin);

	void logout();

	/**
	 * 
	 * @param id_parque
	 * @param horario
	 */
	boolean criarOuAtualizarHorario(int id_parque, Horario horario);
}