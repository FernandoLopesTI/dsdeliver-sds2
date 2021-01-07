package br.com.flsoftware.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.flsoftware.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
