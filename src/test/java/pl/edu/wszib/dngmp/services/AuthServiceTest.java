package pl.edu.wszib.dngmp.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.dngmp.TestUtils;
import pl.edu.wszib.dngmp.configuration.TestConfiguration;
import pl.edu.wszib.dngmp.db.IAuctionDAO;
import pl.edu.wszib.dngmp.db.IMessageDAO;
import pl.edu.wszib.dngmp.db.IUserDAO;
import pl.edu.wszib.dngmp.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class AuthServiceTest {
    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IAuctionDAO auctionDAO;

    @MockBean
    IMessageDAO messageDAO;

    @Test
    public void successfulLoginTest(){
        Mockito.when(userDAO.getByLogin("user")).thenReturn(TestUtils.createTestUser());
        String log = "user";
        String pass = "user";
        authenticationService.authenticate(log, pass);
        Assert.assertTrue(sessionObject.isLogged());
    }

    @Test
    public void failedLoginTest(){
        Mockito.when(userDAO.getByLogin("user")).thenReturn(TestUtils.createTestUser());
        String log = "user";
        String pass = "sers";
        authenticationService.authenticate(log, pass);
        Assert.assertFalse(sessionObject.isLogged());
    }


}
