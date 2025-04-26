package com.ms.user.producers;

import com.ms.user.UserApplication;
import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {//vai ser feito o envio dessa mensagem

    final RabbitTemplate rabbitTemplate;
    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;//tem que ser o mesmo nome da fila

    public void publishMessageEmail(UserModel userModel){// método que publica as mensagens na fila
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! Agradecemos seu cadastro, aproveite todos os recursos da nossa plataforma!");


        rabbitTemplate.convertAndSend("", routingKey, emailDto);//precisa passar essas informações



    }
}
