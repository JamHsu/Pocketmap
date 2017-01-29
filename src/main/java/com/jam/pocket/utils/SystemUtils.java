package com.jam.pocket.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemUtils {
	
	public static Properties getSysProperties() throws IOException{
    	
    	Properties props = new Properties();
        FileInputStream fis = null;
        try {
			fis = new FileInputStream("resource/sys.properties");
			props.load(fis);
		} finally {
        	fis.close();
		}

        return props;
    }

	
}
