package ifntuog.eschool.service;

import ifntuog.eschool.dto.AddedUsersDTO;
import ifntuog.eschool.repository.UserRepository;
import ifntuog.eschool.security.CustomPasswordEncoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ifntuog.eschool.auxiliary.Utility.transform;

@Service
@RequiredArgsConstructor
public class PasswordDecodeService {
    @NonNull
    private CustomPasswordEncoder encoder;

    @NonNull
    private UserRepository userRepository;

    public List<AddedUsersDTO> decodemultiple(List<AddedUsersDTO> usersDTOList){
        return usersDTOList.stream().map(i-> AddedUsersDTO.builder()
                .firstname(i.getFirstname())
                .lastname(i.getLastname())
                .patronymic(i.getPatronymic())
                .login(i.getLogin())
                .password(encoder.decode(i.getPassword()))
                .role(i.getRole())
                .build()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<AddedUsersDTO> decodemultiple(){
        List<AddedUsersDTO> addedUsers = transform(userRepository.findAll());
        for (AddedUsersDTO addedUsersDTO : addedUsers)
            addedUsersDTO.setPassword(encoder.decode(addedUsersDTO.getPassword()));
        return addedUsers;
    }
}
