package com.odin.odinbff.repository;

import com.odin.odinbff.model.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
