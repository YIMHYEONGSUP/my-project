package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comments {

    private String comments;

    public static Comments from(final String comments){
        return new Comments(comments);
    }

    @JsonValue
    public String comments(){
        return comments;
    }
}
