package com.jcgon.quarkus.cucumber.test.step;

import io.cucumber.java8.En;

public class UCS001SaveMovieSteps implements En {

    public UCS001SaveMovieSteps() {
        Before("@UCS001-SaveMovie", () ->{
           
        });

        After("@UCS001-SaveMovie", () ->{
           
        });

        When("I put the information name:{string}, releaseDate:{string}, duration:{int}, description:{string}",
                (String string, String string2, Integer int1, String string3) -> {
                    // Write code here that turns the phrase above into concrete actions
                    throw new io.cucumber.java8.PendingException();
                });

        When("click on *Save* button", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Then("the data are saved", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Then("the message:{string} is presented", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });

    }
}
