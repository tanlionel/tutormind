package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Transaction;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.model.Mapper.UserMapper;
import com.exe212.tutormind.model.transation.TransationDTO;
import com.exe212.tutormind.repository.TransactionRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.TransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransationServiceImplement implements TransationService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<TransationDTO> getPaginationTransactionByUserId (
            Integer pageIndex,
            Integer pageSize,
            Integer userId) throws Exception {
        if (!userRepository.existsById(userId))
            throw new UserDoesNotExistException();

        return transactionRepository
                .findAllByOwnerIdOrReceiverId(
                        PageRequest.of(
                                pageIndex,
                                pageSize,
                                Sort.by(Sort.Order.by("createDate")).descending()
                        )
                        , userId, userId)
                .map(
                        t -> TransationDTO.builder()
                                .id(t.getId())
                                .owner(
                                        (t.getOwner() != null) ? UserMapper.mapToUserResponseDTO(t.getOwner()) : null
                                )
                                .receiver(
                                        (t.getReceiver() != null) ? UserMapper.mapToUserResponseDTO(t.getReceiver()) : null
                                )
                                .description(t.getDescription())
                                .entityId(t.getEntityId())
                                .entityType(t.getEntityType())
                                .createBy(
                                        (t.getCreateBy() != null) ? UserMapper.mapToUserResponseDTO(t.getCreateBy()) : null
                                )
                                .createDate(t.getCreateDate())
                                .build()
                );

    }

    @Override
    public Integer createTransaction(TransationDTO transation) {
        Transaction record =
                Transaction.builder()
                        .transation_type(
                                (transation.getTransactionType() == null) ? null :
                                transation.getTransactionType().getId()
                        )
                        .createBy(
                                User.builder()
                                        .id(
                                                (transation.getCreateBy() == null) ? null :
                                                        transation.getCreateBy().getId()
                                        )
                                        .build()
                        )
                        .entityId(transation.getEntityId())
                        .entityType(transation.getEntityType())
                        .description(transation.getDescription())
                        .createDate(LocalDateTime.now())
                        .owner(
                                User.builder()
                                        .id(
                                                transation.getOwner() == null ? null : transation.getOwner().getId()
                                        )
                                        .build()
                        )
                        .receiver(
                                User.builder()
                                        .id(
                                                transation.getReceiver() == null ? null : transation.getReceiver().getId()
                                        )
                                        .build()
                        )
                        .build();

        return transactionRepository.saveAndFlush(record).getId();
    }
}
