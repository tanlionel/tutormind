package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.transation.TransationDTO;
import org.springframework.data.domain.Page;

public interface TransationService {
    Page<TransationDTO> getPaginationTransactionByUserId(
            Integer pageIndex, Integer pageSize, Integer userId) throws Exception;
    Integer createTransaction(TransationDTO transation) ;
}
