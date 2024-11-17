package com.shipan.ecommerce.entity.response;

import java.util.Map;

public record ErrorResponse(
        // argument, message
        Map<String, String> error
) {}
