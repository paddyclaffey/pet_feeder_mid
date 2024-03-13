package com.claffey.petminder.service;

import java.util.List;

public interface WebSocketService {

    void addUserName(String username);
    List<String> getUsers();

    void removeUserName(String username);
}
