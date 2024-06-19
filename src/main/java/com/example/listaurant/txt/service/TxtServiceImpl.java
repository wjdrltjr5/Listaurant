package com.example.listaurant.txt.service;

import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.txt.controller.port.TxtService;
import com.example.listaurant.txt.controller.request.CommentRequest;
import com.example.listaurant.txt.controller.request.UpdateTxtRequest;
import com.example.listaurant.txt.repository.TxtEntity;
import com.example.listaurant.txt.service.port.TxtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TxtServiceImpl implements TxtService {
    private final TxtRepository txtRepository;

    @Transactional
    @Override
    public void saveTxt(CommentRequest commentRequest) {
        TxtEntity txt = TxtEntity.builder()
                .txtId(CommentRequest.getTxtId())
                .pno(CommentRequest.getPno())
                .passwd(CommentRequest.encode(signUpRequest.getPasswd()))
                .build();
        txtRepository.saveTxt(txt);
    }

    @Override
    public void updateTxt(UpdateTxtRequest updateTxtRequest) {

    }

    @Override
    public TxtEntity findMostRecent() {
        return null;
    }

    @Override
    public TxtEntity findMostPopular() {
        return null;
    }

    @Override
    public List<TxtEntity> findByRecent() {
        return List.of();
    }

    @Override
    public List<TxtEntity> findByPopular() {
        return List.of();
    }

    @Override
    public void deleteTxt(Long id) {

    }
}
