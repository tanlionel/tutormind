package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.InvoiceUser;
import com.exe212.tutormind.model.projection.InvoiceUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceUserRepository extends JpaRepository<InvoiceUser,Integer> {
    InvoiceUserProjection findProjectedById(Integer id);
    Page<InvoiceUserProjection> findAllProjectedByStudent_Id(Integer id, Pageable pageable);

    Page<InvoiceUserProjection> findAllProjectedByTutor_Id(Integer id,Pageable pageable);


}
