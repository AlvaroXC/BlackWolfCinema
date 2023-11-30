package com.uady.blackWolfCinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cinema_room")
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcinema_room")
    private int cinemaRoomId;
    @Column(name = "total_rows")
    private int rows;
    @Column(name = "total_columns")
    private int columns;
    @Column(name = "total_seats")
    private int totalSeats;
    @Column(name = "seat_price")
    private int seatPrice;

}
