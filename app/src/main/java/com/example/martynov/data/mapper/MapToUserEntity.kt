package com.example.martynov.data.mapper

import com.example.martynov.data.model.UserDTO
import com.example.martynov.domain.entity.UserEntity

class MapToUserEntity {

    companion object {

        operator fun invoke(userDTO: UserDTO): UserEntity =
            UserEntity(requireNotNull(userDTO.name), requireNotNull(userDTO.password))
    }
}