package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.model.statistic.StatisticDTO;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.enums.EnumConversationStatus;
import com.exe212.tutormind.enums.UserRole;
import com.exe212.tutormind.repository.ConversationRepository;
import com.exe212.tutormind.repository.InvoiceUserRepository;
import com.exe212.tutormind.repository.ProfileRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.ConversationService;
import com.exe212.tutormind.service.service_interface.InvoiceUserService;
import com.exe212.tutormind.service.service_interface.StatisticService;
import com.google.auto.value.AutoOneOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private InvoiceUserRepository invoiceUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public StatisticDTO getStatistic(int month) {
        List<Conversation> conversationList = conversationRepository.findConversation(month);

        return StatisticDTO.builder()
                .studentAmount(userRepository.countAllUserByRoleId(UserRole.STUDENT.ordinal() + 1))
                .tutorAmount(profileRepository.countAllProfile())
                .revenue(invoiceUserRepository.findInvoiceByDate(month))
                .approvedRequestAmount(
                        (conversationList == null) ? 0 : conversationList.stream()
                                .filter(c -> c.getConversationStatus().getId() == EnumConversationStatus.Approved.ordinal() + 1).count()
                )
                .rejectedRequestAmount(
                        (conversationList == null) ? 0 : conversationList.stream()
                                .filter(c -> c.getConversationStatus().getId() == EnumConversationStatus.Rejected.ordinal() + 1)
                                .count()
                )
                .otherRequestAmount(
                        (conversationList == null) ? 0 : conversationList.stream()
                                .filter(
                                        c -> c.getConversationStatus().getId() != (EnumConversationStatus.Approved.ordinal() + 1)
                                        && c.getConversationStatus().getId() != (EnumConversationStatus.Rejected.ordinal() + 1)
                                )
                                .count()
                )
                .build();
    }
}
