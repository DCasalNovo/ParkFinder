package pt.uminho.di.aa.parkfinder.logicaParques;


import org.springframework.data.domain.Page;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ParqueService {

	List<Parque> listarParques();

	Page<Parque> listarParques(int pagina, int nrResultadosPorPagina);

	/**
	 * 
	 * @param ids
	 */
	List<Parque> listarParques(List<Integer> ids);

	List<Parque> getParquesDoGestor(int id_gestor);

	List<Parque> getParquesDoAdministrador(int id_admin);

	/**
	 * 
	 * @param nome
	 */
	List<Parque> procurarParque(String nome);

	/**
	 * 
	 * @param id_parque
	 */
	Parque procurarParque(int id_parque);

	/**
	 * 
	 * @param p
	 */
	Parque criarParque(Parque p)  throws Exception ;

	/**
	 * 
	 * @param id_parque
	 */
	void removerParque(int id_parque);

	/**
	 * 
	 * @param id_parque
	 * @param p
	 */
	void criarPrecario(int id_parque, Precario p) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param tipoPrecario
	 */
	void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	List<Precario> getPrecarios(int id_parque) throws Exception;

	Precario getPrecarioByParqueIdAndTipoLugar(int id_parque, String tipoLugar) throws Exception;

	float simularCusto(int id_parque, TipoLugarEstacionamento tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	float calcularCusto(int id_parque, Integer id_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	Estatisticas getEstatisticasParque(int id_parque) throws Exception;

	List<Estatisticas> getEstatisticasGeral();

	Estatisticas getEstatisticasGeralAgregado();

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 * @param n
	 */
	void addLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 * @param n
	 */
	void removerLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	void removerLugar(int id_parque, int id_lugar) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void addLugaresInstantaneos(int id_parque, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void removeLugaresInstantaneos(int id_parque, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param h
	 */
	void setHorario(int id_parque, Horario h) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	Horario getHorario(int id_parque) throws Exception;


	/**
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Set<Integer> procurarLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim);

	/**
	 *
	 * @param id_parque
	 * @param disponivel
	 */
	void setDisponivel(int id_parque, boolean disponivel) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param nome
	 * @param descricao
	 * @param latitude
	 * @param longitude
	 * @param disponivel
	 * @param caminho_foto
	 * @return
	 */
	boolean setAll(int id_parque, Optional<String> nome, Optional<String> descricao, Optional<String> morada, Optional<Float> latitude, Optional<Float> longitude, Optional<Boolean> disponivel, Optional<String> caminho_foto) throws Exception;

	LugarEstacionamento getLugarById(int id_lugar);

	void incrementaVolume_E_aumentaFaturacao(int parqueID, float custo) throws Exception;

	void incLugaresInstantaneos(int id_parque, int n) throws Exception;
	void decLugaresInstantaneos(int id_parque, int n) throws Exception;
}