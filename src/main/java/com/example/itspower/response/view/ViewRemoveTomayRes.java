package com.example.itspower.response.view;

import com.example.itspower.model.resultset.GroupRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewRemoveTomayRes {
    private String label;
    List<ViewRemoveTomayRes> children;

   public ViewRemoveTomayRes( GroupRoleDto groups){
       this.label=groups.getLabel();
   }
}
