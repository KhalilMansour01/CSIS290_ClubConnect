package com.example.campus_connect.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "membership_requests")
public class MembershipRequestsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private ClubsEntity club;

    @Column(name = "status",length = 20)
    private String status; // Pending, Approved, Rejected

    @Column(name = "request_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate requestDate = LocalDate.now();

    // Getters and setters...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public UsersEntity getUser() {
        return user;
    }
    public void setUser(UsersEntity user) {
        this.user = user;
    }
    public ClubsEntity getClub() {
        return club;
    }
    public void setClub(ClubsEntity club) {
        this.club = club;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
