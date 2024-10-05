package com.muawiya.landonhotel.data.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "rooms")
@Data
@ToString
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    @Column(name = "name")
    private String name;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "bed_info")
    private String bedInfo;

}
