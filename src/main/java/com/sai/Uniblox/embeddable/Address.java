package com.sai.Uniblox.embeddable;

import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String type;
    private String name;
    private String phoneNumber;
    private String locality;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
