package me.hahajava.rnserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no")
	private Long profileNo;

	@NotNull
	private String userName;

	@NotNull
	private String phoneNo;

	@OneToOne
	@MapsId
	@JsonUnwrapped
	private User user;
}
