package br.com.flsoftware.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flsoftware.dsdeliver.dto.OrderDTO;
import br.com.flsoftware.dsdeliver.entities. Order;
import br.com.flsoftware.dsdeliver.repositories. OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private  OrderRepository repo;
	
	@Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
    	List<Order> list = repo.findOrdersWithProducts();  	
    	return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

}
