package com.example.customerservice.controller;

import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.mapper.CustomerMapper;
import com.example.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        var list = customerService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerMapper.getDto(list));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CustomerDto>> findAllPage(Pageable pageable) {
        var list = customerService.findAllPage(pageable);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list.map(customerMapper::getDto));
    }

    @GetMapping("/number_id/{number_id}")
    public ResponseEntity<CustomerDto> findAllPage(@PathVariable("number_id") String numberId) {
        var customerOpt = customerService.findNumberId(numberId);
        if(!customerOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerMapper.getDto(customerOpt.get()));
    }

    @GetMapping("/{customer-uuid}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("customer-uuid") UUID uuid) {
        try {
            var customer = customerService.findById(uuid);
            return ResponseEntity.ok(customerMapper.getDto(customer));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/region/{region-ui}")
    public ResponseEntity<List<CustomerDto>> findByRegion(@PathVariable("region-ui") UUID region) {
        try {
            var customer = customerService.findAllByRegion(region);
            return ResponseEntity.ok(customerMapper.getDto(customer));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerDto customerDto) {
        try {
            var customer = customerService.save(customerMapper.getEntity(customerDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.getDto(customer));
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{customer-uuid}")
    public ResponseEntity<CustomerDto> save(@PathVariable("customer-uuid") UUID customerUuid,
                                            @RequestBody @Valid CustomerDto customerDto) {
        try {
            var customer = customerService.update(customerUuid, customerMapper.getEntity(customerDto));
            return ResponseEntity.ok(customerMapper.getDto(customer));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{customer-uuid}")
    public ResponseEntity delete(@PathVariable("customer-uuid") UUID customerUuid) {
        try {
            customerService.delete(customerUuid);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
