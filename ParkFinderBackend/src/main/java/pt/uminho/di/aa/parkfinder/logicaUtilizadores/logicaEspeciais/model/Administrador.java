package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Administrador")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("Admin")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
@Getter
@Setter
@AllArgsConstructor
public class Administrador extends Utilizador implements Serializable {
	public Administrador() {}

	@ManyToOne(targetEntity= Gestor.class, fetch=FetchType.LAZY)
	@JoinColumn(name="GestorID", referencedColumnName="UtilizadorID", nullable=false)
	private Gestor gestor;
	
	@ManyToMany(targetEntity= Parque.class, fetch= FetchType.LAZY)
	@JoinTable(name="Parque_Administrador", joinColumns={ @JoinColumn(name="AdminID") }, inverseJoinColumns={ @JoinColumn(name="ParqueID") })
	private Set<Parque> parques = new HashSet<>();
	
	public String toString() {
		return super.toString();
	}
	
}