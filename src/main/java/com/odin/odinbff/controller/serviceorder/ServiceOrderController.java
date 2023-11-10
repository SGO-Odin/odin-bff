package com.odin.odinbff.controller.serviceorder;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.controller.serviceorder.request.ServiceOrderFormRequest;
import com.odin.odinbff.controller.serviceorder.response.ServiceOrderResponse;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import com.odin.odinbff.repository.ServiceOrderRepository;
import com.odin.odinbff.service.ServiceOrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(Api.ServiceOrder.SERVICE_ORDER_RESOURCE)
public class ServiceOrderController  {

    public final ServiceOrderRepository serviceOrderRepository;

    public final ClientRepository clientRepository;

    public final ProductRepository productRepository;

    public final ServiceOrderService serviceOrderService;

    public ServiceOrderController(@Autowired final ServiceOrderRepository serviceOrderRepository,
                                  @Autowired final ClientRepository clientRepository,
                                  @Autowired final ProductRepository productRepository,
                                  @Autowired final ServiceOrderService serviceOrderService) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.serviceOrderService = serviceOrderService;
    }

    @GetMapping
    @Transactional
    public List<ServiceOrderResponse> getAllOpen(@RequestParam Long clientId) {
        return serviceOrderRepository.findByStatusAndClient_id(ServiceOrder.StatusType.OPENED, clientId)
                .stream().map(ServiceOrderResponse::new)
                .toList();
    }

    @GetMapping(Api.PATH_PARAM_ID)
    public ServiceOrderResponse get(@PathVariable final Long id) {
        return serviceOrderRepository.findById(id)
                .map(ServiceOrderResponse::new)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Não ec"));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody final ServiceOrderFormRequest serviceOrderRequest,
                                           @Autowired final UriComponentsBuilder uriBuilder) throws NoSuchFieldException, IllegalAccessException {
        final ServiceOrder newServiceOrder = serviceOrderRequest.toModel(clientRepository, productRepository);
        serviceOrderService.save(newServiceOrder);

        return ResponseEntity.created(
                uriBuilder.path(Api.ServiceOrder.SERVICE_ORDER_READ_BY_ID)
                        .buildAndExpand(newServiceOrder.getId())
                        .toUri())
                .build();
    }
}
