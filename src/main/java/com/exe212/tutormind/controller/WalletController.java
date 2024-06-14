package com.exe212.tutormind.controller;

import com.exe212.tutormind.entity.Wallet;
import com.exe212.tutormind.model.wallet.WalletDTO;
import com.exe212.tutormind.service.service_interface.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
@CrossOrigin("*")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping("/{userId}")
    public ResponseEntity<WalletDTO> getWalletByUserId(
            @PathVariable Integer userId
    ) throws Exception {

        return ResponseEntity.ok(
                walletService.getWalletByUserId(userId)
        );

    }

    @PostMapping("/{userId}")
    public ResponseEntity<WalletDTO> createOrUpdateWallet(
            @PathVariable Integer userId,
            @RequestBody WalletDTO wallet
    ) throws Exception {

        return ResponseEntity.ok(
                walletService.createOrUpdateWalletBallance(userId, wallet.getBallance())
        );

    }

}
