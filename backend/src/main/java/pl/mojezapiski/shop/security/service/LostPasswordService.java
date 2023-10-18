package pl.mojezapiski.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mojezapiski.shop.common.mail.EmailClientService;
import pl.mojezapiski.shop.security.model.User;
import pl.mojezapiski.shop.security.model.dto.ChangePassword;
import pl.mojezapiski.shop.security.model.dto.EmailObject;
import pl.mojezapiski.shop.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LostPasswordService {

    private final UserRepository userRepository;
    private final EmailClientService emailClientService;
    @Value("${app.serviceAddress}")
    private String serviceAddress;

    @Transactional
    public void sendLostPasswordLink(EmailObject email) {
        User user = userRepository.findByUsername(email.getEmail())
                .orElseThrow(() -> new RuntimeException("Taki email nie istnieje"));

        String hash = generateHashForLostPassword(user);
        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());
        emailClientService.getInstance()
                .send(email.getEmail(), "Zresetuj hasło", createMessage(createLink(hash)));

    }

    private String createMessage(String hashLink) {
        return "Wygenerowaliśmy dla Ciebie link do zmiany hasła" +
                "\n\nKliknij link, żeby zresetować hasło: " +
                "\n" + hashLink +
                "\n\nDziękujemy.";
    }

    private String createLink(String hash) {
        return serviceAddress + "/lostPassword/" + hash;
    }

    private String generateHashForLostPassword(User user) {
        String toHash = user.getId() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }

    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if (!Objects.equals(changePassword.getPassword(), changePassword.getRepeatPassword())) {
            throw new RuntimeException("Hasła nie są takie same");
        }
        User user = userRepository.findByHash(changePassword.getHash())
                .orElseThrow(() -> new RuntimeException("Nieprawidłowy link"));
        if (user.getHashDate().plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(changePassword.getPassword()));
            user.setHash(null);
            user.setHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }

    }

}
