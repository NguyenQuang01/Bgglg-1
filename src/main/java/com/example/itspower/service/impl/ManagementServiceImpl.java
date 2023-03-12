package com.example.itspower.service.impl;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.repositoryjpa.GroupRepository;
import com.example.itspower.response.ListRootChildResponse;
import com.example.itspower.response.RootResponse;
import com.example.itspower.response.RootResponseCompare;
import com.example.itspower.response.TotalEmployee;
import com.example.itspower.service.ManagementService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    private final GroupRepository groupRepository;
    private final EntityManager entityManager;

    public ManagementServiceImpl(GroupRepository groupRepository,@Qualifier("primaryEntityManager") EntityManager entityManager) {
        this.groupRepository = groupRepository;
        this.entityManager = entityManager;
    }
    @Override
    public RootResponse getListRoot(String date) {
        List<ListRootChildResponse> childs = new ArrayList<>();
        date += " 00:00:00.000000";
        RootResponse responses = new RootResponse() ;
        List<GroupEntity> root=groupRepository.getRootId();
        for(GroupEntity groups:root){
            TotalEmployee studentAndPart =  getDetailEmployeeNum(date,groups.getId()).get(0);
            ListRootChildResponse response = new ListRootChildResponse();
            Integer totalEmployee = groupRepository.totalEmployee(groups.getId(),date);
            Integer totalEmpProductivity = groupRepository.totalEmpProductivity(groups.getId(),date);
            if(totalEmpProductivity ==null)totalEmpProductivity=0;
            if(totalEmployee!=0) {
                float ratio =(float) (Double.valueOf(totalEmpProductivity) /Double.valueOf(totalEmployee));
                response.setRatio(ratio);
            }
            response.setGroupName(groups.getName());
            response.setTotalEmp(totalEmployee);
            response.setTotalProductEmp(totalEmpProductivity);
            response.setTotalPartTime(studentAndPart.getPartTime());
            response.setTotalStudent(studentAndPart.getStudent());
            childs.add(response);
        }
        Integer totalWork =childs.stream().map(i -> i.getTotalEmp()).reduce(0, (a, b) -> a + b);
        Integer totalWorkProduct = childs.stream().map(i -> i.getTotalProductEmp()).reduce(0, (a, b) -> a + b);
        Float totalTheValue = childs.stream().map(i -> i.getRatio()).reduce(0f, (a, b) -> a + b);
        responses.setChild(childs);
        responses.setTotalTheValue(totalTheValue);
        responses.setTotalActualWorking(totalWork);
        responses.setTotalLaborReporting(totalWorkProduct);
        return responses;
    }

    @Override
    public RootResponseCompare getRoot(String date) throws ParseException {
        RootResponseCompare  response= new RootResponseCompare();
        RootResponse today = getListRoot(date);
        Date yourDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yourDate);
        calendar.add(Calendar.DATE, -1);
        String newDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        RootResponse yesterday = getListRoot(newDate);
        response.setToday(today);
        response.setYesterday(yesterday);
        return response;
    }


    public List<TotalEmployee> getDetailEmployeeNum(String date,Integer parentId){
        List<TotalEmployee> reponse = new ArrayList<>();
        try{
            StringBuilder query = new StringBuilder();
            query.append("select part_time_num as partTime,student_num as student from reportdtl dtl " +
                    "inner join report r on r.id=dtl.report_id where" +
                    " groupid in (select id from group_name where parent_id = "+parentId+" " +
                    "and r.create_at like  '"+date+" %')");
            Query queryResult = entityManager.createNativeQuery(query.toString());
            reponse = queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(TotalEmployee.class)).getResultList();
            if(reponse.size()==0) reponse.add(new TotalEmployee(0,0));
            return reponse;
        }catch (Exception e){
            reponse.add(new TotalEmployee(0,0));
            return reponse;
        }
    }

}
