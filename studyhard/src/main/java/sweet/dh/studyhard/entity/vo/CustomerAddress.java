package sweet.dh.studyhard.entity.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"city", "street", "zipcode"})
@Getter
@ToString
public class CustomerAddress {
    private String city;
    private String street;
    private String zipcode;

    public CustomerAddress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
