package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.ReportDtlRepository;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.repository.repositoryjpa.GroupRepository;
import com.example.itspower.repository.repositoryjpa.RestRepository;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.response.ListRest;
import com.example.itspower.response.ListTransfer;
import com.example.itspower.response.ReportDetailResponse;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final RestRepository restRepository;
    private final TransferJpaRepository transferJpaRepository;

    public ReportServiceImpl(ReportRepository reportRepository, ReportDtlRepository reportDtlRepository, GroupRepository groupRepository, @Qualifier("primaryEntityManager") EntityManager entityManager, RestRepository restRepository, TransferJpaRepository transferJpaRepository) {
        this.reportRepository = reportRepository;
        this.reportDtlRepository = reportDtlRepository;
        this.groupRepository = groupRepository;
        this.entityManager = entityManager;
        this.restRepository = restRepository;
        this.transferJpaRepository = transferJpaRepository;
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

    public Object detail(String orderDate, Integer userGroupId) {
        try {
            StringBuilder query = new StringBuilder();
            query.append("select r.id as id,demarcation as dinhBien,rest_number as soNghi , " +
                    "part_time_num as thoiVu,student_num as hocSinh,rice_number as soCom,r.total_productivity as laoDongNangSuat" +
                    " from  report r inner " +
                    "join reportdtl rdtl on r.id=rdtl.report_id " +
                    "  where order_date like '" + orderDate + "%'" + "and r.user_group_id =  " + userGroupId);
            Query queryResult = entityManager.createNativeQuery(query.toString());
            ReportDetailResponse response = (ReportDetailResponse) queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ReportDetailResponse.class)).getSingleResult();
            response.setTransferList(getListTranfer(response.getId()));
            response.setRestList(getListRest(response.getId()));
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ListTransfer> getListTranfer(Integer reportId) {
        StringBuilder query = new StringBuilder();
        query.append("select num_transfer as numTranfer ,transfer_type as typeTransfer" +
                ",user_group_id groupId from transfer where report_id = " + reportId);
        Query queryResult = entityManager.createNativeQuery(query.toString());
        return queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ListTransfer.class)).getResultList();
    }

    public List<ListRest> getListRest(Integer reportId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT r.name as ten, rs.reason_name as lydo FROM report_system.rest r \n" +
                "inner join reason rs on r.reason_id=rs.id where report_id =" + reportId);
        Query queryResult = entityManager.createNativeQuery(query.toString());
        return queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ListRest.class)).getResultList();
    }

    @Override
    public List<GroupEntity> getListGroup() {
        return groupRepository.findAll();
    }
}
