package hyeong.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 4, max = 50)
    private String password;

    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @JsonIgnore
    @NotNull
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "NAME")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();


}
