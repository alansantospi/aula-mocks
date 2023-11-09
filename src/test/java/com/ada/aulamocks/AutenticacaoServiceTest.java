package com.ada.aulamocks;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AutenticacaoServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private AutenticacaoService service;

    @Test
    public void testAutenticar_quandoUsuarioExisteELoginCorreto(){

        User lucas = new User();
        lucas.setLogin("Lucas");
        lucas.setSenha("123");

        User amanda = new User();
        amanda.setLogin("Amanda");
        amanda.setSenha("123");

        when(userRepositoryMock.findByLogin("Lucas")).thenReturn(lucas);
        when(userRepositoryMock.findByLogin("Amanda")).thenReturn(amanda);

        boolean result = service.autenticar("Lucas", "123");
        assertTrue(result);

        result = service.autenticar("Amanda", "123");
        assertTrue(result);

        verify(userRepositoryMock, times(2)).findByLogin(any());

        InOrder inOrder = inOrder(userRepositoryMock);
        inOrder.verify(userRepositoryMock).findByLogin("Lucas");
        inOrder.verify(userRepositoryMock).findByLogin("Amanda");


    }

}