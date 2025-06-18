package com.springboot.restapi.jpa.mapping.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userDetail", orphanRemoval = true)
//    @JsonManagedReference
//    @JoinColumn(name = "user_id_fk", referencedColumnName = "userId")
    @JsonProperty("orderDetails")
    private List<OrderDetail> orderDetail = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
}
