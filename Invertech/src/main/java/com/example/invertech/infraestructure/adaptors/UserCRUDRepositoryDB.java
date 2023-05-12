package com.example.invertech.infraestructure.adaptors;

import com.example.invertech.domain.models.User;
import com.example.invertech.infraestructure.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCRUDRepositoryDB extends CrudRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
}
