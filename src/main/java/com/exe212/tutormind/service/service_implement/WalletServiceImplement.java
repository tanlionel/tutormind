package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.entity.Wallet;
import com.exe212.tutormind.exception.InvalidDataException;
import com.exe212.tutormind.exception.InvalidateException;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.model.Mapper.UserMapper;
import com.exe212.tutormind.model.wallet.WalletDTO;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.repository.WalletRepository;
import com.exe212.tutormind.service.service_interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletServiceImplement implements WalletService {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public WalletDTO getWalletByUserId(Integer userId) throws Exception {
        Wallet wallet = walletRepository.findByUserId(userId);

        if (wallet == null)
            throw new NotFoundException();

        return WalletDTO.builder()
                .id(wallet.getId())
                .createDate(wallet.getCreateDate() == null ? LocalDateTime.now() : wallet.getCreateDate())
                .updateDate(LocalDateTime.now())
                .user(
                        UserMapper
                                .mapToUserResponseDTO(wallet.getUser())
                )
                .ballance(wallet.getBalance())
                .build();
    }

    @Override
    public WalletDTO createOrUpdateWalletBallance(Integer userId, Integer ballance) throws Exception{
        if (userId == null || ballance == null || ballance < 0)
            throw new InvalidDataException();

        if (userRepository.findById(userId).isEmpty())
            throw new UserDoesNotExistException();

        Wallet wallet = walletRepository.findByUserId(userId);

        wallet = (wallet == null) ? (new Wallet()) : wallet;
        wallet.setBalance(ballance);

        wallet.setUpdateDate(LocalDateTime.now());

        wallet.setUser(
                User.builder()
                        .id(userId)
                        .build()
        );

        walletRepository.saveAndFlush(wallet);

        return getWalletByUserId(userId);
    }
}
