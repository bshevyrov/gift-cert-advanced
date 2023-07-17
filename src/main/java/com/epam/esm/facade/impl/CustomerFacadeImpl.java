package com.epam.esm.facade.impl;

import com.epam.esm.entity.Customer;
import com.epam.esm.facade.CustomerFacade;
import com.epam.esm.mapper.CustomerListMapper;
import com.epam.esm.mapper.CustomerMapper;
import com.epam.esm.service.CustomerService;
import com.epam.esm.veiw.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {
    private final CustomerService customerService;
    private final CustomerListMapper customerListMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerFacadeImpl(CustomerService customerService, CustomerListMapper customerListMapper, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerListMapper = customerListMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO create(CustomerDTO entity) {
        return null;
    }

    @Override
    public CustomerDTO findById(long id) {
        return customerMapper.toDTO(customerService.findById(id));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return null;
    }

    @Override
    public void update(CustomerDTO entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> all = customerService.findAll(pageable);
        return new PageImpl<>(customerListMapper.toDTOList(all.getContent()),pageable,all.getTotalElements());
    }
}