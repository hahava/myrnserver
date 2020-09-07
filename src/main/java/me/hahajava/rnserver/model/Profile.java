package me.hahajava.rnserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotNull
	private String userName;

	@NotNull
	private String phoneNo;

	private String selfIntroduce;

	@OneToOne
	@MapsId
	@JsonUnwrapped
	@JsonProperty(access = WRITE_ONLY)
	private UserAccount userAccount;

	public static Profile newInstanceAsProfile(Profile newProfile){
		newProfile.setNo(null);
		return newProfile;
	}

	public static void updateInstanceAsProfile(Profile oldProfile, Profile newProfile) {
		Optional.ofNullable(newProfile.getPhoneNo()).ifPresent(oldProfile::setPhoneNo);
		Optional.ofNullable(newProfile.getUserName()).ifPresent(oldProfile::setUserName);
		Optional.ofNullable(newProfile.getSelfIntroduce()).ifPresent(oldProfile::setSelfIntroduce);
	}

    @Builder
	Profile(String userName, String phoneNo, String selfIntroduce) {
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.selfIntroduce = selfIntroduce;
    }
}
