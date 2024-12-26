package sweet.dh.studyhard.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sweet.dh.studyhard.order.entity.vo.CustomerAddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @ElementCollection
    @CollectionTable(
            name = "customer_addresses",
            joinColumns = @JoinColumn(name = "customer_id")
    )
    private List<CustomerAddress> addresses = new ArrayList<>();

    @Builder
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void addAddress(String city, String street, String zipcode) {
        removeAddress(city, street, zipcode);
        this.addresses.add(new CustomerAddress(city, street, zipcode));
    }

    public void removeAddress(String city, String street, String zipcode) {
        var address = new CustomerAddress(city, street, zipcode);
        this.addresses.remove(address);
    }

    public List<CustomerAddress> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void clearAddresses() {
        this.addresses.clear();
    }
}
