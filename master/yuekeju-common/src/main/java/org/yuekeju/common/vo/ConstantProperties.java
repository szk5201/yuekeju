package org.yuekeju.common.vo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


/**
 * OOS 实体
 * @author szk  2020-7-15 23:17:23
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "oos")
@ConditionalOnProperty(name = "myDatasources.enabled", havingValue = "true")
public class ConstantProperties {
	private String endpoint;
	private String keyid;
	private String keysecret;
	private String bucketname;
	private String filehost;
}
