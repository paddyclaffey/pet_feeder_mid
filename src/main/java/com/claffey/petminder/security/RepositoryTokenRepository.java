package com.claffey.petminder.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}