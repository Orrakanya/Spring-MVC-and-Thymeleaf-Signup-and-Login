package ku.cs.kafe.service;

import ku.cs.kafe.entity.Member;
import ku.cs.kafe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private MemberRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }


    public void createUser(Member user) {
        Member record = new Member();
        record.setName(user.getName());
        record.setUsername(user.getUsername());
        record.setRole("USER"); //hardcode

        // password ต้อง encode ก่อน
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);
        // ฐานข้อมูลจะไม่เป็น plain text จะเป็น hashed

        repository.save(record);
    }


    public Member getUser(String username) {
        return repository.findByUsername(username);
    }

}
