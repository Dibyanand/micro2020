package com.way2learnonline.portfolio.repository;


import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.way2learnonline.portfolio.domain.Order;

public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByAccountId(String accountId);
}
