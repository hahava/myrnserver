package me.hahajava.rnserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import me.hahajava.rnserver.model.request.RegisterRequestDTO;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class UserAccount {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@JsonProperty(access = READ_ONLY)
	@Column(name = "no")
	private Long no;

	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private String pw;

	@JsonIgnore
	@OneToOne(mappedBy = "userAccount", cascade = ALL, fetch = LAZY)
	private Profile profile;

	@Enumerated(value = EnumType.STRING)
	private AuthLevel authLevel;

	public static UserAccount newInstanceForRegister(RegisterRequestDTO registerRequestDTO) {
		UserAccount account = new UserAccount();
		account.setId(registerRequestDTO.getId());
		account.setPw(registerRequestDTO.getPw());
		return account;
	}
}
