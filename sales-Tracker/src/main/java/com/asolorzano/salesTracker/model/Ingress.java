package com.asolorzano.salesTracker.model;


import lombok.Data;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Ingress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngress;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(5,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(5,2)", nullable = false)
    private double tax;

    @Column(length = 10, nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private boolean enabled;





}
