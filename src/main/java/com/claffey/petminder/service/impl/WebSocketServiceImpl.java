package com.claffey.petminder.service.impl;

import com.claffey.petminder.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    private List<String> userNames = new ArrayList<>();

    public void addUserName(String username) {
        userNames.add(username);
    }

    @Override
    public List<String> getUsers() {
        return this.userNames;
    }

    public void removeUserName(String username) {
        userNames.remove(username);
    }
}
