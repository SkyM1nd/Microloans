package com.example.martynov.data.mapper

import com.example.martynov.data.model.UserDTO
import com.example.martynov.domain.entity.UserEntity

class MapToUserDTO {

    companion object {

        operator fun invoke(userEntity: UserEntity): UserDTO =
            UserDTO(userEntity.name, userEntity.password)
    }
}