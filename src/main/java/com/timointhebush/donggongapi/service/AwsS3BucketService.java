package com.timointhebush.donggongapi.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AwsS3BucketService {
    private final String region;
    private final String bucket;
    private final String accessKey;
    private final String secretKey;

    public AwsS3BucketService(
            @Value(value = "${s3.region}") String region,
            @Value(value = "${s3.bucket}") String bucket,
            @Value(value = "${s3.access-key}") String accessKey,
            @Value(value = "${s3.secret-key}") String secretKey
    ) {
        this.region = region;
        this.bucket = bucket;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
