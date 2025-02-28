package com.suraj.NeoShop.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


@JsonInclude(JsonInclude.Include.NON_NULL) ///  It Excludes null fields from JSON response
public record SendResponse<T>(HttpStatus status, String message, T data) {

}
