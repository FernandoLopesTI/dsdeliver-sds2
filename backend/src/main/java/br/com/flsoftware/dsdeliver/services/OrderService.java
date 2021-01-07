package br.com.flsoftware.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.flsoftware.dsdeliver.dto.OrderDTO;
import br.com.flsoftware.dsdeliver.dto.ProductDTO;
import br.com.flsoftware.dsdeliver.entities. Order;
import br.com.flsoftware.dsdeliver.entities.OrderStatus;
import br.com.flsoftware.dsdeliver.repositories. OrderRepository;
import br.com.flsoftware.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private  OrderRepository repo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
    	List<Order> list = repo.findOrdersWithProducts();  	
    	return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }
	
	@Transactional
    public OrderDTO insert(OrderDTO obj){
		Order order = new Order(null, obj.getAddress(), obj.getLatitude(), obj.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : obj.getProducts()) {
			order.getProducts().add(productRepo.getOne(p.getId()));
		}
		
		order = repo.save(order);	
		return new OrderDTO(order);
	}

}
