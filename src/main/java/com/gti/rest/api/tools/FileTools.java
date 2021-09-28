package com.gti.rest.api.tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class FileTools {
	@Autowired
	ResourceLoader resourceLoader;
	public String readSource(String fileName) throws IOException{
		Resource resource = resourceLoader.getResource("classpath:"+fileName);
	    
	 
	    try(InputStream inputStream = resource.getInputStream();)
	    {
	        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
	        String data = new String(bdata, StandardCharsets.UTF_8);
	        return data;
	    
	    } 
	    
	}

}
