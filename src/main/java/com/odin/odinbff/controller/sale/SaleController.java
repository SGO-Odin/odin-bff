package com.odin.odinbff.controller.sale;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import com.odin.odinbff.repository.ServiceOrderRepository;
import com.odin.odinbff.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Api.Sale.SALE)
public class SaleController {

    private final SaleService saleService;

    private final ClientRepository clientRepository;

    private final ServiceOrderRepository serviceOrderRepository;

    private final ProductRepository productRepository;

    public SaleController(@Autowired final SaleService saleService,
                          @Autowired final ClientRepository clientRepository,
                          @Autowired final ServiceOrderRepository serviceOrderRepository,
                          @Autowired final ProductRepository productRepository) {
        this.saleService = saleService;
        this.clientRepository = clientRepository;
        this.serviceOrderRepository = serviceOrderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Void> sale(@Valid @RequestBody SaleFormRequest saleRequest,
                                     @Autowired UriComponentsBuilder uriComponent)
            throws NoSuchFieldException, IllegalAccessException {

        final var sale = saleRequest.toModel(clientRepository, serviceOrderRepository, productRepository);
        saleService.sale(sale);

        return ResponseEntity.created(
                        uriComponent.path(Api.PATH_PARAM_ID)
                                .buildAndExpand(sale.getId())
                                .toUri())
                .build();
    }
}
