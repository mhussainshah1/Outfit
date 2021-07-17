package com.outfit.business.services;

import com.outfit.business.entities.InvalidPassword;
import com.outfit.business.entities.repositories.InvalidPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PasswordService {

    private final InvalidPasswordRepository invalidPasswordRepository;

    @Autowired
    public PasswordService(InvalidPasswordRepository studentRepository) {
        this.invalidPasswordRepository = studentRepository;
    }

    public List<InvalidPassword> getInvalidPassword() {
        return invalidPasswordRepository.findAll();
    }

    public void addNewInvalidPassword(InvalidPassword invalidPassword) {
        Optional<InvalidPassword> studentOptional = invalidPasswordRepository.findInvalidPasswordByValue(invalidPassword.getValue());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("invalid password taken");
        }
        invalidPasswordRepository.save(invalidPassword);
//        System.out.println(student);
    }

    public void deleteInvalidPassword(Long id) {
        boolean exists = invalidPasswordRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("invalid password with id " + id + " does not exists");
        }
        invalidPasswordRepository.deleteById(id);
    }

    @Transactional //use the setter to update the entity in database when possible
    //Entity go to managed state
    public void updateStudent(Long id, String value, String email) {

        InvalidPassword student = invalidPasswordRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("invalid password with id " + id + " does not exists"));

        if (value != null && value.length() > 0 && !Objects.equals(student.getValue(), value)) {
            student.setValue(value);

        }
    }
}