package com.igor.springmvc.service;

import com.igor.springmvc.DTO.DeskRequest;
import com.igor.springmvc.mapper.MapperUtils;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.repository.DeskRepository;
import com.igor.springmvc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class DeskService {

    private DeskRepository deskRepository;
    private UserRepository userRepository;
    private MapperUtils mapperUtils;
    @Autowired
    public void setMapperUtils(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setDeskRepository(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @Transactional(readOnly = true)
    public List<Desk> readAll(String username) {
        return userRepository.findByUsername(username).orElseThrow().getDesks().stream().toList();
    }

    public void updateDesk(DeskRequest deskRequest) {
        Desk desk = deskRepository.findById(deskRequest.id()).orElseThrow();
        if(!desk.getName().equals(deskRequest.name()))
            desk.setName(deskRequest.name());
        if(!desk.getDescr().equals(deskRequest.descr()))
            desk.setDescr(deskRequest.descr());
    }

    public Integer saveDesk(DeskRequest deskRequest)
    {
        Desk desk = mapperUtils.toDesk(deskRequest, userRepository);
        deskRepository.save(desk);
        return desk.getId();
    }

    public void delete(int id) {
        deskRepository.delete(deskRepository.findById(id).orElseThrow());
    }
}
