package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.model.statistic.StatisticDTO;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.enums.EnumConversationStatus;
import com.exe212.tutormind.enums.UserRole;
import com.exe212.tutormind.model.statistic.WeeklyStatistic;
import com.exe212.tutormind.repository.*;
import com.exe212.tutormind.service.service_interface.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private CourseRepository courseRepository;

    private List<WeeklyStatistic> getWeeklyStatisic() {
        List<WeeklyStatistic> weeklyStatisticList = new ArrayList<>();

        LocalDateTime mondayDate = LocalDate.now().atTime(0, 0).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime sundayDate = LocalDate.now().atTime(0, 0).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Monday date: " + mondayDate.toString());
        System.out.println("Sunday date: " + sundayDate.toString());

        LocalDateTime date = mondayDate;

        while (!date.isAfter(sundayDate)) {
            List<Conversation> conversationList = conversationRepository
                    .findConversationByRange(date, date.plusDays(1));

            weeklyStatisticList.add(
                    WeeklyStatistic.builder()
                            .dayOfWeek(date.getDayOfWeek().name())
                            .date(date.toLocalDate())
                            .revenue(invoiceUserRepository.findSumInvoiceByRange(date, date.plusDays(1)))
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
                            .build()
            );

            date = date.plusDays(1);
        }

        return weeklyStatisticList;
    }

    @Override
    public StatisticDTO getStatistic() {
        int month = LocalDate.now().getMonthValue();

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
                .numberOfCourse(courseRepository.countCourseByMonth(month))
                .weeklyStatisticList(getWeeklyStatisic())
                .build();
    }

    @Override
    public Long getRevenueByRange(LocalDate startDate, LocalDate endDate) {
        return invoiceUserRepository.findSumInvoiceByRange(startDate.atTime(0, 0),
                endDate.plusDays(1).atTime(0, 0));

    }


}
