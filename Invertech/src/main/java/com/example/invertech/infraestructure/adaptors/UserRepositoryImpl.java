package com.example.invertech.infraestructure.adaptors;

import com.example.invertech.domain.models.User;
import com.example.invertech.domain.puertos.IUserRepository;
import com.example.invertech.infraestructure.entities.UserEntity;
import com.example.invertech.infraestructure.rest.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserCRUDRepositoryDB userRepositoryDB;

    @Autowired
    private UserMapper userMapper;
    @Override
    public User saveUser(User user) {
        //UserEntity userEntity = UserEntity.builder().name(user.getName()).email(user.getEmail()).password(user.getPassword()).build();
        return userMapper.toUser(userRepositoryDB.save(userMapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        UserEntity user = this.userRepositoryDB.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario no existe")
        );
        return Optional.of(userMapper.toUser(user));
    }

    @Override
    public Iterable<User> getAllUsers() {
        Iterable<User> users = userMapper.toUsers(userRepositoryDB.findAll());
        return users;
    }

    @Override
    public User updateUser(User user) {
        Optional<UserEntity> optionalUser = userRepositoryDB.findById(user.getId());
        if (optionalUser.isPresent()){
            UserEntity userUpdated = optionalUser.get();
            userUpdated.setName(user.getName());
            userUpdated.setEmail(user.getEmail());
            userUpdated.setPassword(user.getPassword());
            userRepositoryDB.save(userUpdated);
            return userMapper.toUser(userUpdated);
        }else{
            new RuntimeException("Usuario no existe");
            return user;
        }
    }

    @Override
    public boolean deleateUser(Long id) {
        /*Optional<UserEntity> userEntity = userRepositoryDB.findById(id);
        if(userEntity.isPresent()){
            userRepositoryDB.delete(userEntity.get());
            return true;
        }else {
            return false;
        }*/
        return true;
    }
}
