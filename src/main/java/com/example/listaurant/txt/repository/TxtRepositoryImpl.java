package com.example.listaurant.txt.repository;

import com.example.listaurant.txt.service.port.TxtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TxtRepositoryImpl implements TxtRepository {

    private final TxtMapper txtMapper;

    @Override
    public void saveTxt(TxtEntity txtEntity) { txtMapper.saveTxt(txtEntity); }

    @Override
    public TxtEntity findMostRecentTxt() {
        return txtMapper.findMostRecentTxt();
    }

    @Override
    public TxtEntity findMostPopularTxt() { return txtMapper.findMostPopularTxt();
    }

    @Override
    public List<TxtEntity> findByRecent() { return txtMapper.findByRecent(); }

    @Override
    public List<TxtEntity> findByPopular() { return txtMapper.findByPopular();    }

    @Override
    public void updateTxt(TxtEntity txtEntity) { txtMapper.updateTxt(txtEntity);
    }

    @Override
    public void deleteTxt(Long id) { txtMapper.deleteTxt(id); }
}
