package com.xxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String username;
    private String itemId;
    private String title;
    private String houseType;
    private Long price;
    private String rentType;
    private Byte commentState;
    private String img;
}
