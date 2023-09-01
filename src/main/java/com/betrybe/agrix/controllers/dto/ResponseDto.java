package com.betrybe.agrix.controllers.dto;

/**
 * Response DTO.
 */
public record ResponseDto<T>(String message, T data) {

}
