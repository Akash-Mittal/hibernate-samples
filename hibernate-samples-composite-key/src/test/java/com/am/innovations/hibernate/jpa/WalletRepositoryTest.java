package com.am.innovations.hibernate.jpa;

import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void contextLoads() throws Exception {
        WalletPK walletPK = new WalletPK(123L, CURRENCY.EUR);
        Wallet wallet = new Wallet(walletPK, BigDecimal.TEN);
        walletRepository.save(wallet);

        Optional<Wallet> optionalWallet = walletRepository
                .findById(new WalletPK.Builder().currency(CURRENCY.EUR).userID(123L).build());
        Assert.assertNotNull(optionalWallet.get());
        Assert.assertThat(optionalWallet.get().getBalance().intValue(), is(BigDecimal.TEN.intValue()));
    }
}
