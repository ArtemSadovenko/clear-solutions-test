package com.septemarch.test.service.impl;

import com.septemarch.test.dto.SearchDTO;
import com.septemarch.test.entitis.User;
import com.septemarch.test.exceptions.InvalidData;
import com.septemarch.test.properties.AgeProperties;
import com.septemarch.test.repository.UserRepository;
import com.septemarch.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private final AgeProperties ageProperties;

    private void validate(User user){
        LocalDate now = LocalDate.now();
        if(user.getBirth_date().isAfter(now.minusYears(ageProperties.getRequiredAge()))){
            throw new InvalidData("User must be 18 years old or older", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void createUser(User user) {
        validate(user);
        repository.save(user);
    }

    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    public User getUser(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> searchUsers(SearchDTO searchDTO) {
        if (searchDTO.getFrom().isAfter(searchDTO.getTo())
            || searchDTO.getFrom().isEqual(searchDTO.getTo())){
            throw new InvalidData("The From data is after To date", HttpStatus.BAD_REQUEST);
        }

        return getAllUsers().stream()
                .filter(e ->
                        (e.getBirth_date().isAfter(searchDTO.getFrom()) && e.getBirth_date().isBefore(searchDTO.getTo()))
                )
                .collect(Collectors.toList());
    }
}
