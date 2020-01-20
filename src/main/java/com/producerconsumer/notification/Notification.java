package com.producerconsumer.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Notification {

    private String API;

    private String message;

}
