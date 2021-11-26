package com.api.crud.event.listener;

import com.api.crud.cache.LoggedOutJwtTokenCache;
import com.api.crud.event.OnUserLogoutSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    private final LoggedOutJwtTokenCache tokenCache;
    private static Logger logger = LoggerFactory.getLogger(OnUserLogoutSuccessEventListener.class);

    @Autowired
    public OnUserLogoutSuccessEventListener(LoggedOutJwtTokenCache tokenCache){
        this.tokenCache=tokenCache;
    }

    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
        if (event != null){
            String username = event.getLogOutRequest().getUserName();
            logger.info(String.format("Log out success event received for user [%s]", username));
            tokenCache.markLogoutEventForToken(event);
        }
    }
}
