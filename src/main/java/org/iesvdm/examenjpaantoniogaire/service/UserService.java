package org.iesvdm.examenjpaantoniogaire.service;

import org.iesvdm.examenjpaantoniogaire.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){this.userRepository = userRepository;}
}
