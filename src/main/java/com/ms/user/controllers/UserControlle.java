package com.ms.user.controllers;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//fluxo do post da API
@RestController
public class UserControlle {

    final UserService userService;

    public UserControlle(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){

        var userModel = new UserModel();//inicia uma instância
        BeanUtils.copyProperties(userRecordDto, userModel);//faz a converção de Dto para Model

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
        //poderia fazer as verificações de usuários
    }
}
