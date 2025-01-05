package com.lullaby.springlibrary.event;

import com.lullaby.springlibrary.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStarted implements ApplicationListener<ApplicationStartedEvent> {

    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        
    }

}
