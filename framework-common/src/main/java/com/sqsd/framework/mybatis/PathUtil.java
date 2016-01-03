package com.sqsd.framework.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import com.alibaba.dubbo.common.utils.StringUtils;

public class PathUtil {
	private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);

	public static String getMultiResources(String[] paths) {
		if (paths == null) return null;
		
		String result = null;
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			for (String path : paths) {
				resourceList.addAll(Arrays.asList(resourcePatternResolver.getResources(path)));
			}
			
			HashMap<String,String> packages = new HashMap<String,String>();
			for(Resource resource : resourceList) {
		        if (resource.isReadable()) {
		            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
		            String className = metadataReader.getClassMetadata().getClassName();
		            String classPackage = className.substring(0,className.lastIndexOf("."));
		            if (classPackage.endsWith(".entity") && packages.get(classPackage) == null ){
		                packages.put(classPackage, classPackage);
		            }
		        }
		    }
			result = StringUtils.join(packages.keySet(), ",");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("mybatis package = " + result);
		return result;
	}
	
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		String[] aa = {"aa","b"};
		a.addAll(Arrays.asList(aa));
		String[] bb = {"bb","cc"};
		a.addAll(Arrays.asList(bb));
		String[] cc = a.toArray(new String[0]);
		for (String s : cc) {
			System.out.println(s);
		}
	}
}
