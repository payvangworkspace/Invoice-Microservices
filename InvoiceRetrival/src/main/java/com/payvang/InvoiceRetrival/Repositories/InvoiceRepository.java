package com.payvang.InvoiceRetrival.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payvang.InvoiceRetrival.Entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
}
