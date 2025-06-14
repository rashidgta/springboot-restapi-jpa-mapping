package com.springboot.restapi.jpa.mapping.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.ALL)
    @JsonManagedReference
//    @JoinColumn(name = "user_id_fk", referencedColumnName = "userId")
    private List<OrderDetail> orderDetail = new ArrayList<>();
}
