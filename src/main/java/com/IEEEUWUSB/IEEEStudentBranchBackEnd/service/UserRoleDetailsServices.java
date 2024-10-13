package com.IEEEUWUSB.IEEEStudentBranchBackEnd.service;


import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.*;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.repo.UserRoleDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserRoleDetailsServices {
    @Autowired
    UserRoleDetailsRepo userRoleDetailsRepo;

    public UserRoleDetails createUserRoleDetails(UserRoleDetails userRoleDetails) {
        return userRoleDetailsRepo.save(userRoleDetails);
    }


    public List<UserRoleDetails> getuserRoleDetails(User user,boolean isActive, String type) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByUserAndIsActive(user,isActive);
        return optionalRole.orElse(null);
    }

    public List<UserRoleDetails> getuserroledetailsbyuserandproject(User user, boolean isActive, Project project) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByUserAndIsActiveAndProject(user,isActive,project);
        return optionalRole.orElse(null);
    }

    public List<UserRoleDetails> getuserroledetailsbyroleandproject(Role role, boolean isActive, Project project) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByRoleAndIsActiveAndProject(role,isActive,project);
        return optionalRole.orElse(null);
    }


    public List<UserRoleDetails> getuserRoleDetailsByProject(User user, boolean isActive, String type, Integer projectID) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByUserAndIsActiveAndTypeAndProject_ProjectID(user,isActive,type,projectID);
        return optionalRole.orElse(null);
    }

    public List<UserRoleDetails> getAlluserRoleDetailsByProject(boolean isActive, String type, Integer projectID) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByIsActiveAndTypeAndProject_ProjectID(isActive,type,projectID);
        return optionalRole.orElse(null);
    }

    public UserRoleDetails getuserRoleDetailsByProjectSingleObject(User user, boolean isActive, String type, Integer projectID) {
        Optional<UserRoleDetails> optionalRole = userRoleDetailsRepo.findUserRoleDetailsByUserAndIsActiveAndTypeAndProject_ProjectID(user,isActive,type,projectID);
        return optionalRole.orElse(null);
    }


    public UserRoleDetails findByUserAndIsActiveAndType(User user,boolean isActive, String type) {
        Optional<UserRoleDetails> optionalRole = userRoleDetailsRepo.findByUserAndIsActiveAndType(user,isActive,type);
        return optionalRole.orElse(null);
    }

    public List<UserRoleDetails> getuserRoleDetailsExom(User user,boolean isActive, String type,String type2) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByUserAndIsActiveAndTypeExom(user,isActive,type,type2);
        return optionalRole.orElse(null);
    }




    public List<UserRoleDetails> getuserRoleDetailsExomByUserRole(Role role,boolean isActive, String type) {
        Optional<List<UserRoleDetails>> optionalRole = userRoleDetailsRepo.findByUserRoleAndIsActiveAndTypeExom(role,isActive,type);
        return optionalRole.orElse(null);
    }




    public Page<UserRoleDetails> getAllExcomUserDetails(Integer page, String search, Integer ouid,Integer termyearId){
        Pageable pageable = PageRequest.of(page, 15);
        return userRoleDetailsRepo.findAllExcomList(search, ouid,termyearId, pageable);
    }


    public boolean isPolicyAvailable(List<UserRoleDetails> userDataArray, String policyCode) {
        if (userDataArray != null) {
            for (UserRoleDetails userData : userDataArray) {
                if (userData != null && userData.getRole() != null) {
                    Role role = userData.getRole();  // Assuming there's only one role
                    if (role.getPolicies() != null) {
                        for (Policy policy : role.getPolicies()) {
                            if (policyCode.equals(policy.getPolicyCode())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
