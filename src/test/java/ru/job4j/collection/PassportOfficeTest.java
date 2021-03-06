package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

public class PassportOfficeTest {

    @Test
    public void whenNormalAdd() {
        Citizen citizen = new Citizen("qwe#123", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void whenAddDuplicate() {
        Citizen citizen = new Citizen("qwe#123", "Petr Arsentev");
        Citizen citizen1 = new Citizen("qwe#123", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertFalse(office.add(citizen1));
    }
}