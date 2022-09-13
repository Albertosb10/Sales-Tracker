package com.asolorzano.salesTracker.service.Impl;

import com.asolorzano.salesTracker.model.Category;
import com.asolorzano.salesTracker.model.Sale;
import com.asolorzano.salesTracker.model.SaleDetail;
import com.asolorzano.salesTracker.repository.IGenericRepository;
import com.asolorzano.salesTracker.repository.ISaleRepository;
import com.asolorzano.salesTracker.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    @Autowired
    private ISaleRepository repo;



    @Transactional
    @Override
    public Sale saveTransactional(Sale sale, List<SaleDetail> details) {
        //interaccion para ver cuantos productos hay
        details.forEach(d -> d.setSale(sale));
        sale.setDetails(details);
        return repo.save(sale);
    }

    @Override
    protected IGenericRepository<Sale, Integer> getRepo() {
        return repo;
    }

    /*
    @Override
    protected IGenericRepository<Sale, Integer> getRepo() {
        return repo;
    }

     */




}
