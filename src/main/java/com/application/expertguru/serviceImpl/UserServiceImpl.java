package com.application.expertguru.serviceImpl;


import com.application.expertguru.DTO.ResponseCodeJson;
import com.application.expertguru.DTO.UniversalResponseDTO;
import com.application.expertguru.DTO.UserDTO;
import com.application.expertguru.DTO.UserUpdateDTO;
import com.application.expertguru.model.User;
import com.application.expertguru.repository.UserRepository;
import com.application.expertguru.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.application.expertguru.constant.Constants.DELETED;
import static com.application.expertguru.constant.Constants.NOT_DELETED;
import static com.application.expertguru.utils.IdGenerator.getUUId;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UniversalResponseDTO addUser(UserDTO userDTO) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        if(userDTO.getEmailId().equalsIgnoreCase("")){
            ur.setResponseCodeJson(new ResponseCodeJson("user not provided email", HttpStatus.NO_CONTENT.value()));
            return ur;
        }
        User user = new User();

        BeanUtils.copyProperties(userDTO,user);
        user.setUserId(getUUId());
        user.setIsDeleted(NOT_DELETED);
        user = userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("user not saved", HttpStatus.CONFLICT.value()));
            return ur;
        }

        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user created", HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO getUsersWithName(String username) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        List<User> users = userRepository.findByUsernameAndIsDeleted(username,NOT_DELETED);
        ur.setList(users);
        ur.setResponseCodeJson(new ResponseCodeJson("users successfully fetched.",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO getUserByEmailId(String emailId) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        User user  = userRepository.findByEmailIdAndIsDeleted(emailId,NOT_DELETED);
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user successfully fetched.",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO deleteUser(String userId) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        User user  = userRepository.findByUserIdAndIsDeleted(userId,NOT_DELETED);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no such user found",HttpStatus.CONFLICT.value()));
            return ur;
        }
        user.setIsDeleted(DELETED);
        user=userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson(" user not created",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user deleted",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO updateUser(UserUpdateDTO userUpdate) {
            UniversalResponseDTO ur=new UniversalResponseDTO();
        User user  = userRepository.findByUserIdAndIsDeleted(userUpdate.getUserId(),NOT_DELETED);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no such user found",HttpStatus.CONFLICT.value()));
            return ur;
        }
        BeanUtils.copyProperties(userUpdate,user,"userId");
        user=userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson(" user not created",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user updated",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public Boolean validateUser(String userId) {
        User user=userRepository.findByUserIdAndIsDeleted(userId,NOT_DELETED);
        if(user==null) return false;
        else return true;
    }
}
