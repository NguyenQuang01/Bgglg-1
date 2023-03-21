package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.response.GroupRoleResponse;
import com.example.itspower.response.GroupRoleResponseChildren;
import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupRoleServiceImpl implements GroupRoleService {
    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Override
    public List<GroupEntity> searchAll() {
        List<GroupEntity> groupEntityList= groupRoleRepository.searchAll();
        getSubList(groupEntityList);
        return null;
    }

    public void getSubList(List<GroupEntity> list){
        List<GroupRoleResponse> groupRoleResponseList = new ArrayList<>();
        Stack<GroupRoleResponse> groupEntityStack = new Stack<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId() > 0 ){
                break;
            }else {
                GroupRoleResponse groupRoleResponse = new GroupRoleResponse(list.get(i));
                groupRoleResponseList.add(groupRoleResponse);
                groupEntityStack.add(groupRoleResponse);
            }
        }

        for (Iterator<GroupEntity> iterator = list.iterator(); iterator.hasNext();) {
            GroupEntity groupEntity = iterator.next();
            if (groupEntity.getParentId()==0) {
                iterator.remove();
            }
        }


        List<GroupRoleResponseChildren> groupRoleResponseChildren = new ArrayList<>();
    }
    public static void main( String[] args ) {
        List<ResponseData> responseDataList = Arrays.asList(
                new ResponseData( 1, -1 ),  // changed null to -1 as null can't be a map key
                new ResponseData( 2, 1 ),
                new ResponseData( 3, 1 ),
                new ResponseData( 4, 1 ),
                new ResponseData( 5, 2 ),
                new ResponseData( 6, 2 ),
                new ResponseData( 7, 3 ),
                new ResponseData( 8, 3 ),
                new ResponseData( 9, 4 ),
                new ResponseData( 10, 4 ),
                new ResponseData( 11, 5 ),
                new ResponseData( 12, -1 ),
                new ResponseData( 13, 12 )
        );
        final Map<Integer, List<ResponseData>> map = responseDataList.stream()
                .collect( Collectors.groupingBy(o -> getLevel( responseDataList, o, 0 ) ) );
        System.out.println( map );
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        // To convert the Map to a List of Lists:
        System.out.println( new ArrayList<>( map.values() ));
    }

    private static int getLevel(List<ResponseData> nodes, ResponseData responseData, int level) {
        if( responseData.parent == -1 ) {
            return level;
        } else {
            return getLevel( nodes, nodes.get( responseData.parent - 1 ), level + 1 );  // -1 to adjust index
        }
    }

    private static final class ResponseData {
        public int id;
        public int parent;

        public ResponseData( int id, int parent ) {
            this.id = id;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return String.format( "{id: %d, parent: %d}", id, parent );
        }
    }
}
