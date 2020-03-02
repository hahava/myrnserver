package me.hahajava.rnserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotNull
	private String userId;
	@NotNull
	private String userPw;
	@NotNull
	private String userName;
	@NotNull
	private String phoneNo;
	@NotNull
	private String token;
}
