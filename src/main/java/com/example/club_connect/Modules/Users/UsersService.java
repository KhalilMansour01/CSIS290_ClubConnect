package com.example.club_connect.Modules.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper usersMapper;

    public List<UserResponseDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(usersMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserResponseDTO> getUserById(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(usersMapper.toDto(user));
    }

    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userDTO) {
        UsersEntity user = usersMapper.toEntity(userDTO);
        System.out.println("User: " + user.toString());
        UsersEntity createdUser = usersRepository.save(user);
        UserResponseDTO userResponseDTO = usersMapper.toDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    public ResponseEntity<UsersEntity> createUser(UsersEntity user) {
        UsersEntity createdUser = usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    public ResponseEntity<UsersEntity> updateUser(String id, UsersEntity user) {
        UsersEntity existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        // if(user.getEmail() != null) {
        // existingUser.setEmail(user.getEmail());
        // }
        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }

        final UsersEntity updatedUser = usersRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<UsersEntity> deleteUser(String id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        usersRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
