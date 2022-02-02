package rest;

import io.restassured.specification.RequestSpecification;

public interface IAuth {
    RequestSpecification auth();
}
