package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import org.springframework.http.ResponseEntity;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorDTO;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import java.time.LocalDateTime;
import java.util.List;

public interface CondutorService {

	/**
	 * 
	 * @param condutorDTO
	 */
	boolean editarPerfil(CondutorEdit condutorDTO) throws Exception;

	List<Reserva> listarMinhasReservas() throws Exception;

	/**
	 * @param id_parque
	 */
	Reserva fazerReservaInstantanea(int id_parque) throws Exception;

	/**
	 * @param id_parque
	 * @param tipo_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva fazerReservaAgendada(int id_parque, String tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	float calculaCustoReservaInstantanea(int id_reserva) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva) throws Exception;

	void logout() throws Exception;

	void setCondutor(Condutor condutor);

	Condutor getCondutorInfo() throws Exception;
}