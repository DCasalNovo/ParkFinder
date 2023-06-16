package pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores;

import java.util.List;

public interface UtilizadorService {

	/**
	 * 
	 * @param email
	 */
	Utilizador getUtilizador(String email);

	/**
	 * 
	 * @param email
	 * @param password
	 */
	Utilizador login(String email, String password);

	/**
	 * 
	 * @param utilizador
	 */
	Utilizador criarUtilizador(Utilizador utilizador);

	/**
	 * 
	 * @param id_user
	 */
	boolean removerUtilizador(int id_user);

	/**
	 * 
	 * @param utilizador
	 */
	boolean atualizarUtilizador(Utilizador utilizador);

	/**
	 * 
	 * @param nome
	 * @param descriminador
	 */
	List<Utilizador> procurarUtilizador(String nome, String descriminador);
}