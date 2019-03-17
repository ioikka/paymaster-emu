package ru.paymaster.emu.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class InitReceivedEvent extends ApplicationEvent {
    private Map<String,String> message;

    public InitReceivedEvent(Object source, Map<String,String> message) {
        super(source);
        this.message = message;
    }
}
