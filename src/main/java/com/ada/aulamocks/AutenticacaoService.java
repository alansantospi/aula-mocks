package com.ada.aulamocks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CotacaoDolarService dollarService;

    public void salvar(User user){
        userRepository.save(user);
    }

    public boolean autenticar(String username, String password) {
        User user = userRepository.findByLogin(username);
        if (user != null && user.getSenha().equals(password)) {
            return true;
        }
        return false;
    }

    public Double converter(Double valorEmReal){
        return null; //dollarService.getQuotation(new Date()) / valorEmReal;
    }

}
