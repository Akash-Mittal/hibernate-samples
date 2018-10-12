package com.am.innovations.hibernate.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testName() throws Exception {
        for (int i = 0; i < 10; i++) {
            House house = new House("House Villa #" + i);
            Owner owner = new Owner("Owner#" + i);
            owner.getHouse().add(house);
            house.setOwner(owner);
            ownerRepository.save(owner);
        }
    }
}
