package com.lillogon.profinance.exception;

import java.time.OffsetDateTime;

public record ErrorResponse(
        String message,
        int status,
        OffsetDateTime timestamp
) {}
