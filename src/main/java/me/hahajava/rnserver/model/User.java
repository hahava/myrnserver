package me.hahajava.rnserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotNull
	@Column(nullable = false, unique = true)
	private String id;

	@NotNull
	@Column(nullable = false)
	private String userPw;

	@NotNull
	private String token;

	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Profile profile;
}
