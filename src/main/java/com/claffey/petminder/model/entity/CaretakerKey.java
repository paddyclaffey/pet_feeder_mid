package com.claffey.petminder.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaretakerKey implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PET_ID")
    private Long petId;
}