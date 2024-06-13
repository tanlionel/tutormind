package com.exe212.tutormind.controller;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.model.transation.TransationDTO;
import com.exe212.tutormind.service.service_interface.TransationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {
    @Autowired
    TransationService transationService;

    @GetMapping("/{userId}")
    public ResponseEntity<Page<TransationDTO>> getPaginationTransactionLogs(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = Common.DEFAULT_PAGE_SIZE) Integer pageSize,
            @PathVariable Integer userId
    ) throws Exception {

        return ResponseEntity.ok(
                transationService.getPaginationTransactionByUserId(pageIndex, pageSize, userId)
        );

    }

    @PostMapping
    public ResponseEntity<Integer> createTransaction(
            @RequestBody TransationDTO transaction
    ) throws Exception {

        return ResponseEntity.ok(
                transationService.createTransaction(transaction)
        );

    }

}
