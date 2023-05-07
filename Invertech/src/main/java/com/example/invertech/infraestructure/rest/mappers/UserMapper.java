package com.example.invertech.infraestructure.rest.mappers;

import com.example.invertech.domain.models.User;
import com.example.invertech.infraestructure.entities.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    User toUser(UserEntity userEntity);
    Iterable<User> toUsers(Iterable<UserEntity> usersEntity);
    @InheritInverseConfiguration
    UserEntity toUserEntity (User user);
}
