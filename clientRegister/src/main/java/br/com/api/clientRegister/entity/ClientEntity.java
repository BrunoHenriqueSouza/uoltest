package br.com.api.clientRegister.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * @author brunosouza
 *
**/

@Entity
@Table(name="client")
@EntityListeners(AuditingEntityListener.class)
public class ClientEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="clientId")
	private Integer clientId;
	
	@NotBlank(message="O nome precisa ser preechido.")
	@Column(name="name")
	private String name;
	
	@Min(value = 1, message="A idade deve ser positiva e maior que 0.")
	@Column(name="age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="weatherId")
	private WeatherEntity weather;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public WeatherEntity getWeather() {
		return weather;
	}

	public void setWeather(WeatherEntity weather) {
		this.weather = weather;
	}
	
}
