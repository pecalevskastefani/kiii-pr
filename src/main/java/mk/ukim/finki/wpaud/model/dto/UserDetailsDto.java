package mk.ukim.finki.wpaud.model.dto;

import lombok.Data;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.Role;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    //vrz baza na user klasata ni vrakja nov userDt
    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}

