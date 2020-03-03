package me.hahajava.rnserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotNull
	private String userName;

	@NotNull
	private String phoneNo;

	@OneToOne
	@MapsId
	@JsonIgnore
	private User user;
}
