package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.ReportDtlRepository;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.repository.repositoryjpa.GroupRepository;
import com.example.itspower.response.ReportDetailResponse;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportDtlRepository reportDtlRepository;
    private final GroupRepository groupRepository;
    private final EntityManager entityManager;

    public ReportServiceImpl(ReportRepository reportRepository, ReportDtlRepository reportDtlRepository, GroupRepository groupRepository, EntityManager entityManager) {
        this.reportRepository = reportRepository;
        this.reportDtlRepository = reportDtlRepository;
        this.groupRepository = groupRepository;
        this.entityManager = entityManager;
    }


    @Override
    public ReportResponse add(ReportRequest request) {
        try {
            ReportEntity reportEntity = reportRepository.save(request);
            ReportDtlEntity dtlEntities = reportDtlRepository.saveDtls(request.getReportDtlRequest(), reportEntity.getId());
            return new ReportResponse(reportEntity, dtlEntities);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ReportDetailResponse detail(String orderDate, Integer userGroupId) {
        reportRepository.isCheckOrderDate(orderDate);
        try {
            StringBuilder query = new StringBuilder();
            query.append("select r.demarcation ,r2.rest_number as restNum ,r2.part_time_num as partTimeNum ,r2.student_num as studentNum ,t.num_transfer as transferNum, \n" +
                    "t1.num_transfer as supportNum,r2.rice_number as riceNum,r.total_productivity as totalProductivity from report r join reportdtl r2 on r.id =r2.report_id \n" +
                    "join transfer t on t.report_id = r.id  and t.transfer_type ='TRANSFER_NUM' \n" +
                    "join transfer t1 on t1.report_id = r.id  and t1.transfer_type ='SUPPORT_NUM' \n" +
                    "join employee_rest er on er.report_id = r.id where DATE_FORMAT(r.order_date , '%Y%m%d') = DATE_FORMAT(" + orderDate + ", '%Y%m%d') and r.user_group_id = " + userGroupId + " ");
            Query queryResult = entityManager.createNativeQuery(query.toString());
            return (ReportDetailResponse) queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ReportDetailResponse.class)).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<GroupEntity> getListGroup() {
        return groupRepository.findAll();
    }
}
