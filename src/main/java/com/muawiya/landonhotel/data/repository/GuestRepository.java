package com.muawiya.landonhotel.data.repository;

import com.muawiya.landonhotel.data.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
