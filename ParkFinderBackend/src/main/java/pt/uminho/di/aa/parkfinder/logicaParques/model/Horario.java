package pt.uminho.di.aa.parkfinder.logicaParques.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Horario")
@Getter
@Setter
@AllArgsConstructor
public class Horario implements Serializable {
	public Horario() {}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@OneToMany(targetEntity= HorarioPeriodo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="HorarioID", nullable=false)
	@JsonProperty("periodos")
	private Set<HorarioPeriodo> periodos = new java.util.HashSet<>();

	public boolean estaAberto(DayOfWeek dayOfWeek, LocalTime data_inicio, LocalTime data_fim) {
		int dia = dayOfWeek.getValue();
		return periodos.stream().anyMatch(p -> p.getDia_semana() == dia
								  			&& (p.getHora_inicio().isBefore(data_inicio) || p.getHora_inicio().equals(data_inicio))
								  			&& (p.getHora_fim().isAfter(data_fim) || p.getHora_fim().equals(data_fim)));
	}

	public boolean estaAberto(){
		LocalDateTime agora = LocalDateTime.now();
		DayOfWeek diaDaSemana = agora.getDayOfWeek();
		LocalTime agoraTime = agora.toLocalTime();
		return estaAberto(diaDaSemana, agoraTime, agoraTime);
	}

	public void addPeriodos(Collection<HorarioPeriodo> colecaoPeriodos){
		colecaoPeriodos.stream().filter(Objects::nonNull).forEach(p -> periodos.add(p));
	}

	@Override
	public String toString() {
		return "Horario{" +
				"id=" + id +
				", periodos=" + periodos +
				'}';
	}
}
