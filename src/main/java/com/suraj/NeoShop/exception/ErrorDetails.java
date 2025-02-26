package com.suraj.NeoShop.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String errorCode, String details) {
}
