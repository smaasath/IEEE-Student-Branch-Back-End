package com.IEEEUWUSB.IEEEStudentBranchBackEnd.service;

import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.OU;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.Project;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.Task;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.entity.User;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.repo.TaskRepo;
import com.IEEEUWUSB.IEEEStudentBranchBackEnd.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class TaksService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;

    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    public Task findTaskById(int id) {
        return taskRepo.findById(id).get();
    }

    public Page<Task> findAllTasksByOU(String taskname, OU ou, String status, User user, User createdby, Integer page) {
        Pageable pageable = PageRequest.of(page, 15);
        return taskRepo.findByOuAndUsers(taskname, ou, status,user,createdby,pageable);
    }

    public Page<Task> findAllTasksByProject(String taskname, Project project, String status, User user, User createdby, Integer page) {
        Pageable pageable = PageRequest.of(page, 15);
        return taskRepo.findByProjectAndUsers(taskname, project, status,user,createdby,pageable);
    }

    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    public String assign(Integer taskId, Integer[] usersIds) {
        try {
            Task task = findTaskById(taskId);
            Set<User> existingUsers = new HashSet<>(task.getUsers());
            Set<Integer> userIdSet = new HashSet<>(Arrays.asList(usersIds));
            for (User user : existingUsers) {
                if (!userIdSet.contains(user.getUserID())) {
                    task.removeuser(user);
                }
            }

            for (Integer userId : usersIds) {
                User user = userRepo.findById(userId).get();
                if (user != null && !task.getUsers().contains(user)) {
                    task.adduser(user);
                }
            }
            var savedtask = taskRepo.save(task);
            return "Assigned user successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }


    public Task getTaskById(int taskId) {
        return taskRepo.findById(taskId).orElse(null);
    }
}
