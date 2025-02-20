package com.kalocs.internhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    private UUID studentId;
    private UUID jobId;
}
