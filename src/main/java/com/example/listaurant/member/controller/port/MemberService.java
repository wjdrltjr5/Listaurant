package com.example.listaurant.member.controller.port;

import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.repository.MemberEntity;

import java.util.Optional;

public interface MemberService {

    void save(SignUpRequest signUpRequest);

    boolean isDuplicationEmail(String email);

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findById(Long id);

    void update(UpdateRequest updateRequest);

    void delete(Long id);
}
