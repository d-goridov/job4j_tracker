package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StabOutput();
        Input in = new StabInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenOneValidInput() {
        Output out = new StabOutput();
        Input in = new StabInput(
                new String[] {"2"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(2));
    }

    @Test
    public void whenManyValidInput() {
        Output out = new StabOutput();
        Input in = new StabInput(
                new String[] {"1", "3", "4"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected1 = input.askInt("Enter menu:");
        assertThat(selected1, is(1));
        int selected2 = input.askInt("Enter menu:");
        assertThat(selected2, is(3));
        int selected3 = input.askInt("Enter menu:");
        assertThat(selected3, is(4));
    }

    @Test
    public void whenNegativeInput() {
        Output out = new StabOutput();
        Input in = new StabInput(
                new String[] {"-1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-1));
    }
}