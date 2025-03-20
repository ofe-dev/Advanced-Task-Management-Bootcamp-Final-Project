package com.omerfarukerol.service.impl;


import com.omerfarukerol.entities.*;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.CreateUserRequestModel;
import com.omerfarukerol.models.CreateUserResponseModel;
import com.omerfarukerol.repository.UserRepository;
import com.omerfarukerol.service.IUserOperationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserOperationsService implements IUserOperationsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponseModel createUser(CreateUserRequestModel requestModel) {
        CreateUserResponseModel createUserResponseModel = new CreateUserResponseModel();

        User user = switch (requestModel.getRole()) {
            case ROLE_ADMIN -> new Admin();
            case ROLE_PROJECT_MANAGER -> new ProjectManager();
            case ROLE_TEAM_LEADER -> new TeamLeader();
            case ROLE_TEAM_MEMBER -> new TeamMember();
        };

        user.setUsername(requestModel.getUsername());
        user.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        user.setRole(requestModel.getRole());

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser,createUserResponseModel);
        return createUserResponseModel;
    }

}
