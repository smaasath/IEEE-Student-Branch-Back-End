package com.IEEEUWUSB.IEEEStudentBranchBackEnd.service;

import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.*;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.repo.ServiceLetterRequestRepo;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.repo.UserRepo;
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
public class ServiceLetterRequestService {
    @Autowired
    ServiceLetterRequestRepo serviceLetterRequestRepo;

    @Autowired
    UserRepo userRepo;

    public ServiceLetterRequest saveServiceLetterRequest(ServiceLetterRequest serviceLetterRequest) {
        return serviceLetterRequestRepo.save(serviceLetterRequest);
    }

    public Page<ServiceLetterRequest> getSreviceLetterRequestByUser(Integer page, User user) {
        Pageable pageable = PageRequest.of(page, 5);
        return serviceLetterRequestRepo.findServiceRequestByUser(user, pageable);
    }
    public Page<ServiceLetterRequest> getAllServiceLetterRequests(Integer page, String search, String status) {
        Pageable pageable = PageRequest.of(page, 15);
        return serviceLetterRequestRepo.findAllServiceRequests(search,status,pageable);
    }
    public ServiceLetterRequest getServiceLetterRequestById(Integer id) {
        Optional<ServiceLetterRequest> serviceLetterRequest = serviceLetterRequestRepo.findById(id);
        return serviceLetterRequest.orElse(null);
    }
    public String deleteServiceLetterRequestById(Integer id) {
        if (serviceLetterRequestRepo.existsById(id)) {
            serviceLetterRequestRepo.deleteById(id);
            return "Service Letter Request Deleted Successfully";
        }else {
            return "Service Letter Request Not Found";
        }
    }
//    public ServiceLetterRequest updateServiceLetterRequestStatus(ServiceLetterRequest serviceLetterRequest) {
//        return serviceLetterRequestRepo.save()
//    }
}
