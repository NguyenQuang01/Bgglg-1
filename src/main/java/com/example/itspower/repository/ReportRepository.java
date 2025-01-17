package com.example.itspower.repository;

import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.request.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Component
public class ReportRepository {
    @Autowired
    private ReportJpaRepository reportJpaRepository;

    @Autowired
    private GroupJpaRepository groupJpaRepository;

    public ReportDto reportDto(String reportDate, int groupId) {
        return reportJpaRepository.findByReport(reportDate, groupId);
    }

    public ReportEntity saveReport(ReportRequest request, int groupId) {
        ReportEntity reportEntity = new ReportEntity();
        Optional<GroupEntity> groupEntity = groupJpaRepository.findById(groupId);
        reportEntity.setDemarcation(request.getDemarcation());
        reportEntity.setGroupId(groupId);
        reportEntity.setRestNum(request.getRestNum());
        reportEntity.setStudentNum(request.getStudentNum());
        reportEntity.setLaborProductivity(request.getLaborProductivity());
        reportEntity.setPartTimeNum(request.getPartTimeNum());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // yourDate là thời gian hiện tại của bạn
        calendar.add(Calendar.HOUR_OF_DAY, 7); // thêm 7 giờ vào thời gian hiện tại
        Date newDate = calendar.getTime();

        reportEntity.setReportDate(newDate);
        return reportJpaRepository.save(reportEntity);
    }

    @Transactional
    public ReportEntity updateReport(ReportRequest request, int groupId) {
        ReportEntity reportEntity = new ReportEntity();
        if (request.getId() == 0) {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "is not exits", HttpStatus.BAD_REQUEST.name());
        }
        reportEntity.setId(request.getId());
        reportEntity.setDemarcation(request.getDemarcation());
        reportEntity.setGroupId(groupId);
        reportEntity.setRestNum(request.getRestNum());
        reportEntity.setStudentNum(request.getStudentNum());
        reportEntity.setLaborProductivity(request.getLaborProductivity());
        reportEntity.setPartTimeNum(request.getPartTimeNum());
        reportEntity.setReportDate(new Date());
        return reportJpaRepository.save(reportEntity);
    }

    public Optional<ReportEntity> findByReportDate(String reportDate) {
        return reportJpaRepository.findByReportDate(reportDate);
    }

    public Optional<ReportEntity> findByReportDateAndGroupId(String reportDate, int groupId) {
        return reportJpaRepository.findByReportDateAndGroupId(reportDate, groupId);
    }

    public Optional<ReportEntity> findByIdAndGroupId(int id, int groupId) {
        return reportJpaRepository.findByIdAndGroupId(id, groupId);
    }

    public Optional<ReportEntity> findByGroupId(int groupId) {
        return reportJpaRepository.findByGroupId(groupId);
    }


    public void deleteByGroupId(Integer groupId) {
        reportJpaRepository.deleteByGroupId(groupId);
    }
}
