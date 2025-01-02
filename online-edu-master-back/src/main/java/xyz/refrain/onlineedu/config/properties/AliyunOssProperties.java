package xyz.refrain.onlineedu.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Aliyun Oss Properties
 */

@Setter
@Validated
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssProperties {

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * 地域访问域名，请不要添加http[s]://前缀
	 */

	@Getter
	private String endpoint;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setAccessDomain(String accessDomain) {
		this.accessDomain = accessDomain;
	}

	/**
	 * oss Bucket 名称
	 */
	@Getter
	private String bucketName;

	/**
	 * 访问域名域名，默认根据bucketName与endpoint设置
	 */
	private String accessDomain;

	public String getAccessDomain() {
		return accessDomain == null
				? "https://" + getBucketName() + "." + getEndpoint()
				: accessDomain;
	}

}
