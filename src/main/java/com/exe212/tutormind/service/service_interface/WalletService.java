package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.entity.Wallet;
import com.exe212.tutormind.model.wallet.WalletDTO;

public interface WalletService {
    public WalletDTO getWalletByUserId(Integer userId) throws Exception;
    public WalletDTO createOrUpdateWalletBallance(Integer userId, Integer ballance) throws Exception;
}
