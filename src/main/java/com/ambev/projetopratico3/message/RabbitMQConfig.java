package com.ambev.projetopratico3.message;
import java.util.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("cadastroProduto");}
    }
