package com.example.invertech.infraestructure.adaptors;

import com.example.invertech.infraestructure.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCRUDRepositoryDB extends CrudRepository<UserEntity, Long> {
}
