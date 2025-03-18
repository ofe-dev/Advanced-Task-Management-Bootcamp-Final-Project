package com.omerfarukerol.exception;

import com.omerfarukerol.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String additionalMessage;

    public String prepareErrorMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMessage());
        if (this.additionalMessage != null){
            builder.append(" : ").append(additionalMessage);
        }
        return builder.toString();
    }

}
