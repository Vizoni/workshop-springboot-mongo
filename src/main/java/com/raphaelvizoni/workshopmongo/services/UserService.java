package com.raphaelvizoni.workshopmongo.services;

import com.raphaelvizoni.workshopmongo.domain.User;
import com.raphaelvizoni.workshopmongo.dto.UserDTO;
import com.raphaelvizoni.workshopmongo.repository.UserRepository;
import com.raphaelvizoni.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setEmail(obj.getEmail());
        newObj.setName(obj.getName());
    }


    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
