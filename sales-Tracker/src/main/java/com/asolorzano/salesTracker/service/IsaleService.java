package com.asolorzano.salesTracker.service;

import com.asolorzano.salesTracker.model.Sale;
import com.asolorzano.salesTracker.model.SaleDetail;

import java.util.List;

public interface ISaleService extends ICRUD<Sale, Integer> {

    //debido a que es una relacion muchos a muchos
    Sale saveTransactional(Sale sale, List<SaleDetail> details);
}
