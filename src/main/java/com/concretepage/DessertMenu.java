package com.concretepage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import io.pivotal.spring.cloud.service.config.PlainTextConfigClient;

@Service
public class DessertMenu {

	private PlainTextConfigClient configClient;

	public DessertMenu(@Autowired(required = false) PlainTextConfigClient configClient) {
		this.configClient = configClient;
	}

	public String fetchMenu() throws IOException {
		if (configClient == null) {
			return "none";
		}
		InputStream input = configClient.getConfigFile("cloud", "master", "dessert.json").getInputStream();
		return StreamUtils.copyToString(input, Charset.defaultCharset());
	}

}
