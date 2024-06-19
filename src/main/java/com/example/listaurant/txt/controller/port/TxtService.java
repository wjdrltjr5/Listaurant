package com.example.listaurant.txt.controller.port;

import com.example.listaurant.txt.controller.request.UpdateTxtRequest;
import com.example.listaurant.txt.controller.request.CommentRequest;
import com.example.listaurant.txt.domain.Txt;
import com.example.listaurant.txt.repository.TxtEntity;

import java.util.List;

public interface TxtService {
    void saveTxt(CommentRequest commentRequest);
    void updateTxt(UpdateTxtRequest updateTxtRequest);
    TxtEntity findMostRecent();
    TxtEntity findMostPopular();
    List<TxtEntity> findByRecent();
    List<TxtEntity> findByPopular();
    void deleteTxt(Long id);
}
