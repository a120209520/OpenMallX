package org.ppl.mall.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.ppl.mall.util.FastDFSClient;

public class FastDfsTest{
	//@Test
	public void testUpload() throws Exception {
		ClientGlobal.init("G:/OneDrive/Study/Java/Project/OpenMallX/Web/omWeb/src/main/resources/conf/fast-dfs.properties");
		TrackerClient tClient = new TrackerClient();
		TrackerServer tServer = tClient.getConnection();
		StorageServer sServer = null;
		StorageClient sClient = new StorageClient(tServer, sServer);
		String[] result = sClient.upload_file("C:/Users/Smith/Pictures/Saved Pictures/小黄人.jpg", "jpg", null);
		for(String str:result) {
			System.out.println(str);
		}
	}
	
	//@Test
	public void testUploadUtils() throws Exception {
		FastDFSClient client = new FastDFSClient("G:/OneDrive/Study/Java/Project/OpenMallX/Web/omWeb/src/main/resources/conf/fast-dfs.properties");
		String result = client.uploadFile("C:/Users/Smith/Pictures/Saved Pictures/小黄人.jpg");
		System.out.println(result);
	}
}
